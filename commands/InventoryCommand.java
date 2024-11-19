public class InventoryCommand implements CommandAction {

  /**
   * Execute the "inventory" command, allowing the player to see the items in their inventory.
   * @param command The command to be executed.
   * @return String to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {
    if (command.hasIndex(1)) {
      return processor.incorrectFormat();
    }

    return processor.getPlayer().getInventory();
  }
}