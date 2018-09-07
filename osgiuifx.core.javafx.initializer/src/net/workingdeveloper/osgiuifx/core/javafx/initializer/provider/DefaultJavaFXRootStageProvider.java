package net.workingdeveloper.osgiuifx.core.javafx.initializer.provider;

import javafx.stage.Stage;
import net.workingdeveloper.osgiuifx.core.provider.JavaFXRootStageProvider;

public class DefaultJavaFXRootStageProvider implements JavaFXRootStageProvider {

	private final Stage primaryStage;

	public DefaultJavaFXRootStageProvider(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public Stage getStage() {
		return primaryStage;
	}

}
