package net.jfilesync.mw.core.ui.impl.events;

import net.jfilesync.mw.core.ui.api.events.EventConsts;
import net.workingdeveloper.osgiuifx.core.events.Event;
import net.workingdeveloper.osgiuifx.core.events.EventHandler;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@Component(service = EventHandler.class, property = {"event=" + EventConsts.APPLICATION_EXIT})
public class ApplicationExitListener implements EventHandler {

  @Override
  public void handleEvent(final Event event) {
    try {
      System.out.println("### Stopping Application ###");
      getContext().getBundle(0).stop();
    } catch (BundleException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private BundleContext getContext() {
    return FrameworkUtil.getBundle(this.getClass()).getBundleContext();
  }
}
