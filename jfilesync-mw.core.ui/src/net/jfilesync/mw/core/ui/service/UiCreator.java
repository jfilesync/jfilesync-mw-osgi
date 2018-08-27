package net.jfilesync.mw.core.ui.service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.jfilesync.mw.core.ui.api.JavaFXRootStageProvider;
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
    primaryStage.setTitle("Tabs example!");

    try {
      final URL resource = getContext().getBundle().getResource("fxml/main-frame.fxml");
      if (null == resource) {
        throw new IllegalStateException("no FXML");
      }
      BorderPane content = (BorderPane) FXMLLoader.load(
          resource
      );
      final MenuBar menuBar = (MenuBar) content.getTop().lookup("main.menu");

      javafx.scene.control.Button button = new Button("Exit");
      content.setBottom(button);
      button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          System.out.println("Hello World!");
          try {
            getContext().getBundle(0).stop();
          } catch (BundleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
      primaryStage.setScene(new Scene(content));
      primaryStage.show();
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
