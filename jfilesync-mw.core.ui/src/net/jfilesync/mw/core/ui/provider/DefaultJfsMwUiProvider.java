package net.jfilesync.mw.core.ui.provider;

import javafx.stage.Stage;
import net.jfilesync.mw.core.ui.api.JavaFXRootStageProvider;
import net.jfilesync.mw.core.ui.api.JfsMwUiProvider;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;

import java.util.Map;

@Component(service = JfsMwUiProvider.class)
public class DefaultJfsMwUiProvider implements JfsMwUiProvider {

  @Reference
  private JavaFXRootStageProvider mainStage;

  @Activate
  public void activate(ComponentContext c, BundleContext b, Map<String, ?> properties) {
    System.out.println("Activated");
  }

  @Deactivate
  public void deactivate(ComponentContext c, BundleContext b, Map<String, ?> properties) {
    System.out.println("DeActivated");
  }

  @Override
  public Stage getMainStage() {
    return mainStage.getStage();
  }

  @Override
  public void hide() {
    getMainStage().hide();
  }

  @Modified
  public void modified(ComponentContext c, BundleContext b, Map<String, ?> properties) {
    System.out.println("Modified");
  }

  @Override
  public void show() {
    getMainStage().show();
  }
}
