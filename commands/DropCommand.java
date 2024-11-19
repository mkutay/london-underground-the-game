public class DropCommand implements CommandAction {

  /**
   * Execute the "drop" command, allowing the player to drop an item from
   * their inventory into the station.
   * @param command Command object representing the user input.
   * @return String to be outputed to System.out.
   */
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

    player.removeItem(item);
    player.getCurrentStation().addItem(item);

    return "You have dropped " + item.getName() + " in your location.";
  }
}