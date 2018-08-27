package net.jfilesync.mw.core.ui.api;

import org.osgi.annotation.versioning.ProviderType;

import javafx.stage.Stage;

@ProviderType
public interface JfsMwUiProvider {
	void show();

	void hide();

	Stage getMainStage();
}
