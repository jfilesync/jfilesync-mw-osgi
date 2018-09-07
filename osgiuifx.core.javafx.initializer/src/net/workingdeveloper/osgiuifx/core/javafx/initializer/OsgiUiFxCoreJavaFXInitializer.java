package net.workingdeveloper.osgiuifx.core.javafx.initializer;

import javafx.application.Platform;
import net.workingdeveloper.osgiuifx.core.javafx.initializer.provider.JfsMwCoreUiJfxApplication;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.concurrent.Executors;

public class OsgiUiFxCoreJavaFXInitializer implements BundleActivator {
  private static boolean started = false;

  @Override
  public void start(BundleContext context) throws Exception {
    if (!started) {
      Executors.defaultThreadFactory().newThread(() -> {
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        System.out.println("Starting JavaFX Application thread");
        JfsMwCoreUiJfxApplication.launch(JfsMwCoreUiJfxApplication.class);
      }).start();
      started = true;
    }

    System.out.println("OsgiUiFxCore JavaFX initialization Bundle started");
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    Platform.exit();
    System.out.println("JavaFX Platform stopped");
    System.out.println("OsgiUiFxCore JavaFX initialization Bundle stopped");
  }
}
