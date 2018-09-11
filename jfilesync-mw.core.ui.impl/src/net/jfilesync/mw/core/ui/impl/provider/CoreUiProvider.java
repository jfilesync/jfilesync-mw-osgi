package net.jfilesync.mw.core.ui.impl.provider;

import javafx.application.Platform;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import net.jfilesync.mw.core.ui.api.JfsMwUiProvider;
import net.jfilesync.mw.core.ui.impl.service.UiCreator;
import net.workingdeveloper.osgiuifx.core.common.ServiceLifeCycle;
import net.workingdeveloper.osgiuifx.core.menu.MenuManager;
import net.workingdeveloper.osgiuifx.core.provider.JavaFXRootStageProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.osgi.util.tracker.ServiceTracker;

import java.util.Map;

@Component(service = JfsMwUiProvider.class, immediate = true, scope = ServiceScope.SINGLETON)
public class CoreUiProvider implements JfsMwUiProvider, ServiceLifeCycle {

  private BundleContext                                                    bundleContext;
  private JavaFXRootStageProvider                                          mainStage;
  @Reference
  private MenuManager                                                      menuManager;
  private ServiceTracker<JavaFXRootStageProvider, JavaFXRootStageProvider> rootStageTracker;

  @Override
  @Activate
  public void activate(ComponentContext componentContext, BundleContext bundleContext, Map<String, ?> properties) {
    System.out.println("JfsMwUiProvider Activated");
    this.bundleContext = bundleContext;
    rootStageTracker =
        new ServiceTracker<JavaFXRootStageProvider, JavaFXRootStageProvider>(
            bundleContext, JavaFXRootStageProvider.class, null)
        {
          @Override
          public JavaFXRootStageProvider addingService(final ServiceReference<JavaFXRootStageProvider> reference) {
            System.out.print("RootStage added");
            final JavaFXRootStageProvider javaFXRootStageProvider = super.addingService(reference);
            mainStage = javaFXRootStageProvider;
            createUI();
            show();
            return javaFXRootStageProvider;
          }

          @Override
          public void removedService(
              final ServiceReference<JavaFXRootStageProvider> reference, final JavaFXRootStageProvider service
          ) {
            System.out.print("RootStage removed");
            super.removedService(reference, service);
          }
        };
    rootStageTracker.open();
  }

  @Override
  @Deactivate
  public void deactivate(ComponentContext componentContext, BundleContext bundleContext, Map<String, ?> properties) {
    System.out.println("JfsMwUiProvider DeActivated");
    hide();
    mainStage = null;
    rootStageTracker.close();
  }

  @Override
  public Stage getMainStage() {
    if (mainStage != null) {
      return mainStage.getStage();
    }
    return null;
  }

  @Override
  public void hide() {
    if (mainStage != null) {
      Platform.runLater(() -> getMainStage().hide());
    }
  }

  @Override
  @Modified
  public void modified(ComponentContext componentContext, BundleContext bundleContext, Map<String, ?> properties) {
    System.out.println("Modified");
  }

  @Override
  public void show() {
    Platform.runLater(() -> getMainStage().show());
  }

  private void createUI() {
    final ServiceReference<UiCreator> serviceReference = bundleContext.getServiceReference(UiCreator.class);

    UiCreator uiCreator = bundleContext.getService(serviceReference);
    uiCreator.create(mainStage);
    menuManager.setMainMenuBar((MenuBar) mainStage.getStage().getScene().lookup("#" + UiConsts.MAIN_MENUBAR));
    menuManager.createMainMenus();
    bundleContext.ungetService(serviceReference);
  }
}
