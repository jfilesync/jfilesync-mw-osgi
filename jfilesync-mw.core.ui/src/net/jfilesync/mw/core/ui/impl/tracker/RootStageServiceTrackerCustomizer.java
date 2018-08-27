package net.jfilesync.mw.core.ui.impl.tracker;

import net.jfilesync.mw.core.ui.impl.service.UiCreator;
import net.jfilesync.mw.core.ui.api.JavaFXRootStageProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class RootStageServiceTrackerCustomizer
    implements ServiceTrackerCustomizer<JavaFXRootStageProvider, JavaFXRootStageProvider>
{

  private final BundleContext context;

  public RootStageServiceTrackerCustomizer(final BundleContext context) {
    this.context = context;
  }

  @Override
  public JavaFXRootStageProvider addingService(
      ServiceReference<JavaFXRootStageProvider> reference
  ) {
    System.out.println("service published");
    JavaFXRootStageProvider           service          = context.getService(reference);
    final ServiceReference<UiCreator> serviceReference = context.getServiceReference(UiCreator.class);

    UiCreator uiCreator = context.getService(serviceReference);
    uiCreator.create(service);

    return service;
  }

  @Override
  public void modifiedService(
      ServiceReference<JavaFXRootStageProvider> reference,
      JavaFXRootStageProvider service
  ) {
    System.out.println("should never happen - modified");
  }

  @Override
  public void removedService(
      ServiceReference<JavaFXRootStageProvider> reference,
      JavaFXRootStageProvider service
  ) {
    System.out.println("service UNpublished");
  }
}
