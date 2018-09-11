package net.workingdeveloper.osgiuifx.core.menu;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.osgi.annotation.versioning.ProviderType;

import java.util.List;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@ProviderType
public interface MenuManager {
  void addMenus(MenuItem... menus);

  void createMainMenus();

  List<MenuItem> getMainMenuEntries();

  void setMainMenuBar(final MenuBar mainMenuBar);
}
