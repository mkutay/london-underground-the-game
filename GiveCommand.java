public class GiveCommand implements CommandAction {

  /**
   * Execute the "give" command, allowing the player to give an item from
   * their inventory to a character.
   * @param command The command to be executed.
   * @return String to be outputed to System.out
   */
  public String execute(Command command, Processor processor) {
    if (!command.hasIndex(1) || !command.hasIndex(2)) {
      return processor.incorrectFormat();
    }

    Player player = processor.getPlayer();
    String characterName = command.getWord(1);
    String itemName = command.getWord(2);

    Character character = processor.getCharacter(characterName.toLowerCase()); // the character with name characterName.
    if (character == null) {
      return "There is no character with the name " + characterName + ".";
    }

    Item item = player.getInventory().getItem(itemName);
    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    Item exchangedItem = character.exchangeItem(item);
    if (exchangedItem == null) {
      return "The character does not want " + item.getName() + ".";
    }

    player.getInventory().removeItem(item);
    player.getInventory().addItem(exchangedItem);

    return "You have given " + item.getName() + " and received " + exchangedItem.getName() + " in exchange.";
  }
}