public class GiveCommand implements CommandAction {
  public String execute(Command command, Processor processor) {
    if (!command.hasIndex(1) || !command.hasIndex(2)) {
      return processor.incorrectFormat();
    }

    Player player = processor.getPlayer();

    String characterName = command.getWord(1);
    String itemName = command.getWord(2);

    Character character = processor.getCharacters().getCharacter(characterName.toLowerCase());
    if (character == null) {
      return "There is no character with the name " + characterName + ".";
    }

    Item item = player.getItem(itemName);
    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    Item exchangedItem = character.exchangeItem(item);
    if (exchangedItem == null) {
      return "The character does not want " + item.getName() + ".";
    }

    player.removeItem(item);
    player.addItem(exchangedItem);

    return "You have given " + item.getName() + " and received " + exchangedItem.getName() + " in exchange.";
  }
}