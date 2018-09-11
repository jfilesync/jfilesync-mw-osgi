package net.workingdeveloper.osgiuifx.core.events;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class JavaFxOsgiEventPropagator implements Runnable {

  private final Event  event;
  private final String id;

  public JavaFxOsgiEventPropagator(final String id, final Event event) {
    this.id = id;
    this.event = event;
  }

  public JavaFxOsgiEventPropagator(final String id, final javafx.event.Event event) {
    this.id = id;
    this.event = new JavaFxEvent(id, event);
  }

  public void run() {
    final Thread thread = new Thread(() -> {
      final BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
      try {
        ServiceReference<?>[] serviceReference = bundleContext.getAllServiceReferences(
            EventHandler.class.getName(),
            "(|(event=" + id + ")(event=))"
        );
        if (serviceReference != null) {
          for (final ServiceReference<?> reference: serviceReference) {
            EventHandler eventHandler = (EventHandler) bundleContext.getService(reference);
            eventHandler.handleEvent(event);
            bundleContext.ungetService(reference);
          }
        }
      } catch (InvalidSyntaxException e) {
        throw new IllegalArgumentException(e);
      }
    });
    thread.start();
  }
}
