package net.workingdevelopers.jfilesync.mw.core.ui.javafx.initializer.provider;

import javafx.stage.Stage;
import net.workingdeveloper.jfilesync.mw.core.ui.api.JavaFXRootStageProvider;

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
