import java.util.Map.Entry;

/**
 * This interface is the UseEffect interface of the "London Underground" application.
 * "London Underground" is a simple, text-based adventure game.
 * 
 * Represents an effect of an item when it is used.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public interface UseEffect {
  /**
   * Use the item at the given station.
   * @param station The station where the item is used.
   * @return Boolean: True if the game finishes, false otherwise.
   *         String: The message to be displayed to the user.
   */
  Entry<Boolean, String> use(Station station);
}
