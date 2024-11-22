public class TalkCommand implements CommandAction {
  private static final int commandLength1 = 2;
  private static final int commandLength2 = 2;

  /**
   * @param commandLength The length of the command to be verified.
   * @return If the given commandLength is valid.
   * @note The commandLength1 and commandLength2 can be equal if there is only one valid command length.
   */
  public boolean verifyCommandLength(int commandLength) {
    return commandLength == commandLength1 || commandLength == commandLength2;
  }

  /**
   * Execute the "talk" command, allowing the player to talk with a character.
   * @param command Command to be executed.
   * @return String to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {
    String characterName = command.getWord(1);

    Character character = processor.getTube().getCharacter(characterName.toLowerCase());
    if (character == null) {
      return "There is no character with the name " + characterName + ".";
    }

    Item givenItem = character.exchangeItem(null);
    String returnString = character.getDialogue();

    if (givenItem != null) {
      // Add the item to the player's inventory if the character straight up gives an item to the player.
      processor.getPlayer().getInventory().addItem(givenItem);
      returnString += "\n\nYou have received " + givenItem.getName() + " from " + character.getName() + ".";
    }

    return returnString;
  }
}