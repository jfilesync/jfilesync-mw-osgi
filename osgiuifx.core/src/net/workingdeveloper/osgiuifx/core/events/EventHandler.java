package net.workingdeveloper.osgiuifx.core.events;

import org.osgi.annotation.versioning.ConsumerType;

import java.util.EventListener;

/**
 * @author Christoph Graupner <ch.graupner@workingdeveloper.net>
 */
@ConsumerType
@FunctionalInterface
public interface EventHandler extends EventListener {
  void handleEvent(Event event);
}
