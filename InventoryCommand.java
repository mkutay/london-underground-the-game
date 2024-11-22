public class InventoryCommand implements CommandAction {
  private static final int commandLength1 = 1;
  private static final int commandLength2 = 1;

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