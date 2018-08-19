package net.workingdevelopers.jfilesync.mw.core.ui.javafx.initializer.provider;

import javafx.stage.Stage;
import jfilesync_mw.core.ui.javafx.api.JavaFXRootStageProvider;

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
