public class TakeCommand implements CommandAction {
  private static final int commandLength1 = 2;
  private static final int commandLength2 = 3;

  /**
   * @param commandLength The length of the command to be verified.
   * @return If the given commandLength is valid.
   */
  public boolean verifyCommandLength(int commandLength) {
    return commandLength == commandLength1 || commandLength == commandLength2;
  }
  
  /**
   * Execute the "take" command, allowing the player to take a specific
   * line/direction to the next station.
   * @param command Command object representing the command to be executed.
   * @return String to be outputed.
   */
  public String execute(Command command, Processor processor) {
    String directionName = command.getWord(1);
    String lineName = command.getWord(2);

    Station nextStation;

    if (lineName == null) {
      nextStation = processor.getPlayer().getCurrentStation().getExit(directionName);
      if (nextStation == null) {
        return "There is more than one possible exit. Please be more specific.";
      }
    } else {
      nextStation = processor.getPlayer().getCurrentStation().getExit(directionName, lineName);
      if (nextStation == null) {
        return "You cannot take " + directionName + " " + lineName + " line.";
      }
    }

    String returnString = "";

    // If the player is at the "Random" station, get an actual random station while informing the user.
    if (nextStation.getName().equals("Random")) {
      nextStation = processor.getTube().getRandomStation();

      returnString += "You have taken the Waterloo&City line and will arrive at a random station.\n\n";
    }

    processor.getPlayer().goStation(nextStation);
    processor.getTube().moveCharacters(); // Move characters in random directions after the player moves.
    returnString += processor.getDescription(); // Get the description of the new station.

    return returnString;
  }
}