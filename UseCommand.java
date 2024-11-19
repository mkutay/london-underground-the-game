public class UseCommand implements CommandAction {

  /**
   * Execute the "use" command, allowing the player to use an item from
   * their inventory.
   * @return The message to be outputed to the user.
   */
  public String execute(Command command, Processor processor) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return processor.incorrectFormat();
    }

    String itemName = command.getWord(1);

    Item item = processor.getPlayer().getInventory().getItem(itemName);
    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    if (!item.isUseable()) {
      return "You cannot use " + itemName + ".";
    }

    String dialogue = item.useItem();
    processor.getPlayer().getInventory().removeItem(item);

    return dialogue;
  }
}