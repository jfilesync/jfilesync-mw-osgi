package net.workingdeveloper.osgiuifx.core.impl;

import net.workingdeveloper.osgiuifx.core.provider.JavaFXRootStageProvider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class OsgiUiFxActivator implements BundleActivator {
  public static boolean started = false;
  ServiceTracker<JavaFXRootStageProvider, JavaFXRootStageProvider> mainStageTracker;

  @Override
  public void start(BundleContext context) throws Exception {
    System.out.println("OsgiUiFx Core Implementation Bundle started");
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    System.out.println("OsgiUiFx Core Implementation Bundle stopped");
  }
}
