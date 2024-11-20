public class UseCommand implements CommandAction {
  private int commandLength1;
  private int commandLength2;
  
  /**
   * @param commandLength1 The first valid command length.
   * @param commandLength2 The second valid command length.
   */
  public UseCommand(int commandLength1, int commandLength2) {
    this.commandLength1 = commandLength1;
    this.commandLength2 = commandLength2;
  }

  /**
   * @param commandLength The length of the command to be verified.
   * @return If the given commandLength is valid.
   */
  public boolean verifyCommandLength(int commandLength) {
    return commandLength == commandLength1 || commandLength == commandLength2;
  }

  /**
   * Execute the "use" command, allowing the player to use an item from
   * their inventory.
   * @return The message to be outputed to the user.
   */
  public String execute(Command command, Processor processor) {
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