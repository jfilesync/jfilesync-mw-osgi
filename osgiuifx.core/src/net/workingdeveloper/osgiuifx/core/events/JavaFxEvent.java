package net.workingdeveloper.osgiuifx.core.events;

import javafx.event.Event;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class JavaFxEvent extends CoreEvent {

  /**
   * Constructs a prototypical Event.
   *
   * @param eventId
   * @param source  The object on which the Event initially occurred.
   *
   * @throws IllegalArgumentException if source is null.
   */
  public JavaFxEvent(final String eventId, final Event source) {
    super(eventId, source);
  }
}
