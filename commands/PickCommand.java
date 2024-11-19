public class PickCommand implements CommandAction {

  /**
   * Execute the "pick" command, allowing the player to pick up an item from the station.
   * @param command The command to be executed.
   * @return String to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return processor.incorrectFormat();
    }

    Player player = processor.getPlayer();
    String itemName = command.getWord(1);

    Item item = player.getCurrentStation().getItem(itemName);
    if (item == null) {
      return "There is no " + itemName + " in this station.";
    }

    // addItem returns true if the item was picked up (that is if it was light enough)
    if (player.addItem(item)) {
      player.getCurrentStation().removeItem(item);
      return "You have picked up " + item.getName() + ".";
    }

    return "You cannot pick up " + item.getName() + " because it is too heavy.";
  }
}