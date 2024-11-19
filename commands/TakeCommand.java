public class TakeCommand implements CommandAction {

  /**
   * Execute the "take" command, allowing the player to take a specific
   * line/direction to the next station.
   * @param command Command object representing the command to be executed.
   * @return String to be outputed to System.out
   */
  public String execute(Command command, Processor processor) {
    if (!command.hasIndex(1)) {
      return processor.incorrectFormat();
    }

    Player player = processor.getPlayer();
    String directionName = command.getWord(1);
    String lineName = command.getWord(2);
    Station nextStation = player.getCurrentStation().getExit(directionName, lineName);

    if (nextStation == null) {
      if (lineName == null) {
        // Only one word was given and it was not enough to determine the next station.
        return "There is more than one possible exit. Please be more specific.";
      }
      return "You cannot take " + directionName + " " + lineName + " line.";
    }

    // If the player is at the "Random" station, get an actual random station.
    if (nextStation.getName().equals("Random")) {
      nextStation = processor.getTube().getRandomStation();
    }

    player.goStation(nextStation);
    processor.getCharacters().moveCharacters(); // Move characters in random directions after the player moves.

    return processor.getStationDescription(); // Return the description of the new station.
  }
}