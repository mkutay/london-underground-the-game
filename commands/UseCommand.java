import java.util.Map.Entry;

public class UseCommand implements CommandAction {
  public String execute(Command command, Processor processor) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return processor.incorrectFormat();
    }

    Player player = processor.getPlayer();
    String itemName = command.getWord(1);

    Item item = player.getItem(itemName);
    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    Entry<Boolean, String> used = player.getCurrentStation().useItem(item);
    if (used.getKey()) {
      player.removeItem(item);
    }

    if (used.getValue().contains("won the game")) {
      return null; // Return null to signal game end
    }

    return used.getValue();
  }
}