public class DropCommand implements CommandAction {
  private int commandLength1;
  private int commandLength2;
  
  /**
   * @param commandLength1 The first valid command length.
   * @param commandLength2 The second valid command length.
   */
  public DropCommand(int commandLength1, int commandLength2) {
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
   * Execute the "drop" command, allowing the player to drop an item from
   * their inventory into the station.
   * @param command Command object representing the user input.
   * @return String to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {

    String itemName = command.getWord(1);
    Item item = processor.getPlayer().getInventory().getItem(itemName);

    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    processor.getPlayer().getInventory().removeItem(item);
    processor.getPlayer().getCurrentStation().getItems().addItem(item);

    return "You have dropped " + item.getName() + " in your location.";
  }
}