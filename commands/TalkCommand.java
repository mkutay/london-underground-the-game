public class TalkCommand implements CommandAction {
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
    String returnString = character.getDescription();

    if (givenItem != null) {
      processor.getPlayer().addItem(givenItem);
      returnString += "\n\nYou have received " + givenItem.getName() + " from " + character.getName() + ".";
    }

    return returnString;
  }
}