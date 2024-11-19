public class TakeCommand implements CommandAction {
  /**
   * Execute the "take" command, allowing the player to take a specific
   * line/direction to the next station.
   * @param command Command object representing the command to be executed.
   * @return String to be outputed.
   */
  public String execute(Command command, Processor processor) {
    if (!command.hasIndex(1)) {
      return processor.incorrectFormat();
    }

    Player player = processor.getPlayer();
    String directionName = command.getWord(1);
    String lineName = command.getWord(2);

    Station nextStation;

    if (lineName == null) {
      nextStation = player.getCurrentStation().getExit(directionName);
      if (nextStation == null) {
        return "There is more than one possible exit. Please be more specific.";
      }
    } else {
      nextStation = player.getCurrentStation().getExit(directionName, lineName);
      if (nextStation == null) {
        return "You cannot take " + directionName + " " + lineName + " line.";
      }
    }

    String returnString = "";

    // If the player is at the "Random" station, get an actual random station.
    if (nextStation.getName().equals("Random")) {
      nextStation = processor.getTube().getRandomStation();

      returnString += "You have taken the Waterloo&City line and will arrive at a random station.\n\n";
    }

    player.goStation(nextStation);
    processor.moveCharacters(); // Move characters in random directions after the player moves.
    returnString += processor.getDescription(); // Get the description of the new station.

    return returnString;
  }
}