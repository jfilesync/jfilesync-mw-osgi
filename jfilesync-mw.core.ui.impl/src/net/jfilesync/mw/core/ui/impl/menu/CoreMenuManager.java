package net.jfilesync.mw.core.ui.impl.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import net.workingdeveloper.osgiuifx.core.common.ServiceLifeCycle;
import net.workingdeveloper.osgiuifx.core.events.JavaFxOsgiEventPropagator;
import net.workingdeveloper.osgiuifx.core.menu.MenuManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@Component(service = MenuManager.class, scope = ServiceScope.SINGLETON)
public class CoreMenuManager implements MenuManager, ServiceLifeCycle {
  private MenuBar               mainMenuBar;
  private Map<String, MenuItem> menuMap = new HashMap<>();

  @Override
  @Activate
  public void activate(
      final ComponentContext componentContext, final BundleContext bundleContext, final Map<String, ?> properties
  ) {
  }

  public void addMenus(final MenuItem... menus) {
    menus[0].getId();
  }

  @Override
  public void createMainMenus() {
//    getMainMenuBar().getMenus();
  }

  @Override
  @Deactivate
  public void deactivate(
      final ComponentContext componentContext, final BundleContext bundleContext, final Map<String, ?> properties
  ) {
  }

  public List<MenuItem> getMainMenuEntries() {
    return null;
  }

  @Override
  @Modified
  public void modified(
      final ComponentContext componentContext, final BundleContext bundleContext, final Map<String, ?> properties
  ) {

  }

  private void analyse(final List<? extends MenuItem> menus) {
    if (menus == null) {
      throw new IllegalArgumentException("menus should never be <null>.");
    }
    menus.forEach(
        menuItem -> {
          if (menuItem instanceof SeparatorMenuItem) {
            return;
          }
          final String id = menuItem.getId();
//          System.out.println("menuItem.getId() = " + id);
          if (menuMap.containsKey(id)) {
            throw new IllegalStateException(String.format("Menu with ID %s already registered.", id));
          }
          menuMap.put(id, menuItem);
          if (menuItem instanceof Menu) {
            Menu menu = (Menu) menuItem;
            if (!menu.getItems().isEmpty()) {
              analyse(menu.getItems());
            }
          }
          menuItem.setOnAction(event -> {
            new JavaFxOsgiEventPropagator(menuItem.getId(), event).run();
          });
        }
    );
  }

  private MenuBar getMainMenuBar() {
    return mainMenuBar;
  }

  public void setMainMenuBar(final MenuBar mainMenuBar) {
    this.mainMenuBar = mainMenuBar;
    if (mainMenuBar != null) {
      analyse(mainMenuBar.getMenus());
    }
  }
}
