package net.workingdeveloper.osgiuifx.core.events;

import java.util.EventObject;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
public class CoreEvent extends EventObject implements Event {
  private final String eventId;

  /**
   * Constructs a prototypical Event.
   *
   * @param eventId
   * @param source  The object on which the Event initially occurred.
   *
   * @throws IllegalArgumentException if source is null.
   */
  public CoreEvent(final String eventId, final Object source) {
    super(source);
    this.eventId = eventId;
  }

  public String getEventId() {
    return eventId;
  }
}
