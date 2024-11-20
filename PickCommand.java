public class PickCommand implements CommandAction {
  private int commandLength1;
  private int commandLength2;
  
  /**
   * @param commandLength1 The first valid command length.
   * @param commandLength2 The second valid command length.
   * @note The commandLength1 and commandLength2 can be equal if there is only one valid command length.
   */
  public PickCommand(int commandLength1, int commandLength2) {
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
   * Execute the "pick" command, allowing the player to pick up an item from the station.
   * @param command The command to be executed.
   * @return String to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {
    String itemName = command.getWord(1);

    Item item = processor.getPlayer().getCurrentStation().getItems().getItem(itemName);
    if (item == null) {
      return "There is no " + itemName + " in this station.";
    }

    // addItem returns true if the item was picked up (that is if it was light enough)
    if (processor.getPlayer().getInventory().addItem(item)) {
      processor.getPlayer().getCurrentStation().getItems().removeItem(item);
      return "You have picked up " + item.getName() + ".";
    }

    return "You cannot pick up " + item.getName() + " because it is too heavy.";
  }
}