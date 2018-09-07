package net.jfilesync.mw.core.ui.impl.provider;

import javafx.application.Platform;
import javafx.stage.Stage;
import net.jfilesync.mw.core.common.ServiceLifeCycle;
import net.jfilesync.mw.core.ui.api.JfsMwUiProvider;
import net.jfilesync.mw.core.ui.impl.service.UiCreator;
import net.workingdeveloper.osgiuifx.core.provider.JavaFXRootStageProvider;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.util.tracker.ServiceTracker;

import java.util.Map;

@Component(service = JfsMwUiProvider.class)
public class DefaultJfsMwUiProvider implements JfsMwUiProvider {

  private BundleContext                                                    bundleContext;
  private JavaFXRootStageProvider                                          mainStage;
  private ServiceTracker<JavaFXRootStageProvider, JavaFXRootStageProvider> rootStageTracker;

  @Activate
  public void activate(ComponentContext c, BundleContext b, Map<String, ?> properties) {
    System.out.println("JfsMwUiProvider Activated");
    bundleContext = b;
    rootStageTracker =
        new ServiceTracker<JavaFXRootStageProvider, JavaFXRootStageProvider>(b, JavaFXRootStageProvider.class, null) {
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

  @Deactivate
  public void deactivate(ComponentContext c, BundleContext b, Map<String, ?> properties) {
    System.out.println("JfsMwUiProvider DeActivated");
    hide();
    mainStage = null;
    rootStageTracker.close();
  }

  @Override
  public Stage getMainStage() {
    return mainStage.getStage();
  }

  @Override
  public void hide() {
    Platform.runLater(() -> getMainStage().hide());
  }

  @Modified
  public void modified(ComponentContext c, BundleContext b, Map<String, ?> properties) {
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
  }
}
