public class GiveCommand implements CommandAction {
  private static final int commandLength1 = 3;
  private static final int commandLength2 = 3;

  /**
   * @param commandLength The length of the command to be verified.
   * @return If the given commandLength is valid.
   */
  public boolean verifyCommandLength(int commandLength) {
    return commandLength == commandLength1 || commandLength == commandLength2;
  }

  /**
   * Execute the "give" command, allowing the player to give an item from
   * their inventory to a character.
   * @param command The command to be executed.
   * @return String to be outputed to System.out
   */
  public String execute(Command command, Processor processor) {
    String characterName = command.getWord(1);
    String itemName = command.getWord(2);

    Character character = processor.getTube().getCharacter(characterName.toLowerCase()); // the character with name characterName.
    if (character == null) {
      return "There is no character with the name " + characterName + ".";
    }

    Item item = processor.getPlayer().getInventory().getItem(itemName);
    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    Item exchangedItem = character.exchangeItem(item);
    if (exchangedItem == null) {
      return "The character does not want " + item.getName() + ".";
    }

    processor.getPlayer().getInventory().removeItem(item);
    processor.getPlayer().getInventory().addItem(exchangedItem);

    return "You have given " + item.getName() + " and received " + exchangedItem.getName() + " in exchange.";
  }
}