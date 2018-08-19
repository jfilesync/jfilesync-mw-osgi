package net.workingdevelopers.jfilesync.mw.core.ui.provider;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;

import javafx.stage.Stage;
import jfilesync_mw.core.ui.javafx.api.JavaFXRootStageProvider;
import jfilesync_mw.core.ui.javafx.api.JfsMwUiProvider;

@Component(service = JfsMwUiProvider.class)
public class DefaultJfsMwUiProvider implements JfsMwUiProvider {

	@Reference
	private JavaFXRootStageProvider mainStage;

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Activate
	public void activate(ComponentContext c, BundleContext b, Map<String, ?> properties) {
		System.out.println("Activated");
	}

	@Modified
	public void modified(ComponentContext c, BundleContext b, Map<String, ?> properties) {
		System.out.println("Modified");
	}

	@Deactivate
	public void deactivate(ComponentContext c, BundleContext b, Map<String, ?> properties) {
		System.out.println("DeActivated");
	}

	@Override
	public Stage getMainStage() {
		return mainStage.getStage();
	}
}
