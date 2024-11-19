import java.util.Map.Entry;

public class UseCommand implements CommandAction {

  /**
   * Execute the "use" command, allowing the player to use an item from
   * their inventory.
   * @return String: message to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return processor.incorrectFormat();
    }

    Player player = processor.getPlayer();
    String itemName = command.getWord(1);

    Item item = player.getInventory().getItem(itemName);
    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    Entry<Boolean, String> used = item.use(player.getCurrentStation().getName());
    if (used.getKey()) { // the key is true if the item was used
      player.getInventory().removeItem(item);
    }

    // Check if the game has ended after using the item (that is if the player has won by using the Oyster card)
    if (used.getValue().contains("won the game")) {
      return null; // Return null to signal game end
    }

    return used.getValue();
  }
}