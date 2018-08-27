package net.jfilesync.mw.core.ui.javafx.initializer;

import javafx.application.Platform;
import net.jfilesync.mw.core.ui.javafx.initializer.provider.JfsMwCoreUiJfxApplication;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.concurrent.Executors;

public class JfsMwCoreUiJavaFXInitializer implements BundleActivator {
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

    System.out.println("JfsCoreUI JavaFX initialization Bundle started");
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    Platform.exit();
    System.out.println("JavaFX Platform stopped");
    System.out.println("JfsCoreUI JavaFX initialization Bundle stopped");
  }
}
