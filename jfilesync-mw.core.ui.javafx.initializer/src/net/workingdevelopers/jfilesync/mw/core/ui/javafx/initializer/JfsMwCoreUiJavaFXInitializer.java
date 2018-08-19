package net.workingdevelopers.jfilesync.mw.core.ui.javafx.initializer;

import java.util.concurrent.Executors;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javafx.application.Platform;
import net.workingdevelopers.jfilesync.mw.core.ui.javafx.initializer.provider.JfsMwCoreUiJfxApplication;

public class JfsMwCoreUiJavaFXInitializer implements BundleActivator {
	public static boolean started = false;

	@Override
	public void start(BundleContext context) throws Exception {
		if (false == started) {
			Executors.defaultThreadFactory().newThread(() -> {
				Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
				System.out.println("Starting JavaFX Application thread");
				JfsMwCoreUiJfxApplication.launch(JfsMwCoreUiJfxApplication.class);
			}).start();
		}

		System.out.println("JavaFX initialization Bundle started");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		Platform.exit();
		System.out.println("JavaFX Platform stopped");
	}
}
