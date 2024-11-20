public class InventoryCommand implements CommandAction {
  private int commandLength1;
  private int commandLength2;
  
  /**
   * @param commandLength1 The first valid command length.
   * @param commandLength2 The second valid command length.
   */
  public InventoryCommand(int commandLength1, int commandLength2) {
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
   * Execute the "inventory" command, allowing the player to see the items in their inventory.
   * @param command The command to be executed.
   * @return String to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {
    return "Inventory:" + processor.getPlayer().getInventory().toString();
  }
}