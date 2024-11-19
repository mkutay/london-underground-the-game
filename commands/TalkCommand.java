public class TalkCommand implements CommandAction {

  /**
   * Execute the "talk" command, allowing the player to talk with a character.
   * @param command Command to be executed.
   * @return String to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return processor.incorrectFormat();
    }

    String characterName = command.getWord(1);

    Character character = processor.getCharacters().getCharacter(characterName.toLowerCase());
    if (character == null) {
      return "There is no character with the name " + characterName + ".";
    }

    Item givenItem = character.talkWith();
    String returnString = character.getDialogue();

    if (givenItem != null) {
      // Add the item to the player's inventory if the character straight up gives an item to the player.
      processor.getPlayer().addItem(givenItem);
      returnString += "\n\nYou have received " + givenItem.getName() + " from " + character.getName() + ".";
    }

    return returnString;
  }
}