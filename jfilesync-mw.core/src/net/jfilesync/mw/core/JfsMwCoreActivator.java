package net.jfilesync.mw.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class JfsMwCoreActivator implements BundleActivator {
  @Override
  public void start(final BundleContext context) throws Exception {
    System.out.println("JFileSync MW Core Bundle started");
  }

  @Override
  public void stop(final BundleContext context) throws Exception {
    System.out.println("JFileSync MW Core Bundle stopped");
  }
}
