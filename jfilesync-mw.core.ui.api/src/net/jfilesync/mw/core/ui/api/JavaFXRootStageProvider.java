package net.jfilesync.mw.core.ui.api;

import org.osgi.annotation.versioning.ProviderType;

import javafx.stage.Stage;

/**
 * <p>
 * This is an example of an interface that is expected to be implemented by
 * Providers of the API. Adding methods to this interface is a minor change,
 * because only Providers will be affected.
 * </p>
 *
 * @see ProviderType
 * @since 1.0
 */
@ProviderType
public interface JavaFXRootStageProvider {

	Stage getStage();

}
