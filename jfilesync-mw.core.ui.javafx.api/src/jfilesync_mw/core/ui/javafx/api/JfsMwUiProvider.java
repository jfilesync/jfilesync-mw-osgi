package jfilesync_mw.core.ui.javafx.api;

import org.osgi.annotation.versioning.ProviderType;

import javafx.stage.Stage;

@ProviderType
public interface JfsMwUiProvider {
	void show();

	void hide();

	Stage getMainStage();
}
