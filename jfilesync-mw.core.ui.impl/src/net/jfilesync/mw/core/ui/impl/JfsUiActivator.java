package net.jfilesync.mw.core.ui.impl;

import net.jfilesync.mw.core.ui.api.JfsMwUiProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class JfsUiActivator implements BundleActivator {
  public static boolean started = false;
  private ServiceTracker<JfsMwUiProvider, JfsMwUiProvider> jfsMwUiProviderTracker;

  @Override
  public void start(BundleContext context) throws Exception {

    jfsMwUiProviderTracker =
        new ServiceTracker<JfsMwUiProvider, JfsMwUiProvider>(context, JfsMwUiProvider.class, null) {
          @Override
          public JfsMwUiProvider addingService(final ServiceReference<JfsMwUiProvider> reference) {
            System.out.println("JfsMwUiProvider service added");
            return super.addingService(reference);
          }

          @Override
          public void remove(final ServiceReference<JfsMwUiProvider> reference) {
            System.out.println("JfsMwUiProvider service removed");
            super.remove(reference);
          }
        };
    jfsMwUiProviderTracker.open();
    System.out.println("JfsCoreUI Implementation Bundle started");
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    jfsMwUiProviderTracker.close();
    System.out.println("JfsCoreUI Implementation Bundle stopped");
  }
}
