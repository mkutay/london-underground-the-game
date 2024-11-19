public class InventoryCommand implements CommandAction {
  public String execute(Command command, Processor processor) {
    if (command.hasIndex(1)) {
      return processor.incorrectFormat();
    }

    return processor.getPlayer().getInventory();
  }
}