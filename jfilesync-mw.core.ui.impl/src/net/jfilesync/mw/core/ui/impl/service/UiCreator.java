package net.jfilesync.mw.core.ui.impl.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.jfilesync.mw.core.ui.impl.provider.UiConsts;
import net.workingdeveloper.osgiuifx.core.provider.JavaFXRootStageProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.net.URL;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@Component(service = UiCreator.class)
public class UiCreator {

  public void create(JavaFXRootStageProvider service) {
    System.out.println("START UI creation");
    Stage primaryStage = service.getStage();
    primaryStage.setTitle(UiConsts.TITLE_MAIN);

    try {
      final URL resource = getContext().getBundle().getResource("fxml/main-frame.fxml");
      if (null == resource) {
        throw new IllegalStateException("no FXML: fxml/main-frame.fxml");
      }
      BorderPane content = FXMLLoader.load(resource);
      primaryStage.setScene(new Scene(content));
    } catch (IOException e) {
//              logger.error(LogMarker.EXCEPTION, e.getLocalizedMessage(), e);
      e.printStackTrace();

      try {
        getContext().getBundle(0).stop();
      } catch (BundleException e2) {
        e2.printStackTrace();
        System.exit(-1);
      }
    }

    System.out.println("DONE UI creation");
  }

  private BundleContext getContext() {
    return FrameworkUtil.getBundle(this.getClass()).getBundleContext();
  }
}
