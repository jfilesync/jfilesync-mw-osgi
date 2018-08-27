package net.jfilesync.mw.core.ui.api.listener;

import javafx.event.Event;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@FunctionalInterface
public interface UiActionListener<T extends Event> {
  void handle(T event);
}
