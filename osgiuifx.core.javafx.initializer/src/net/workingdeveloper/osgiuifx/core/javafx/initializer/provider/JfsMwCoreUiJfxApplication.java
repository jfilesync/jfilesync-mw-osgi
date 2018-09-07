package net.workingdeveloper.osgiuifx.core.javafx.initializer.provider;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import javafx.application.Application;
import javafx.stage.Stage;
import net.workingdeveloper.osgiuifx.core.provider.JavaFXRootStageProvider;

public class JfsMwCoreUiJfxApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		bc.registerService(JavaFXRootStageProvider.class, new DefaultJavaFXRootStageProvider(primaryStage), null);
		bc.registerService(Application.class, this, null);
		System.out.println("JavaFX Application registered");
	}

}
