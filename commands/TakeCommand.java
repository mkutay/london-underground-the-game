public class TakeCommand implements CommandAction {
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
        return "There is more than one possible exit. Please be more specific.";
      }
      return "You cannot take " + directionName + " " + lineName + " line.";
    }

    // If the player is at the "Random" station, get an actual random station.
    if (nextStation.getName().equals("Random")) {
      nextStation = processor.getTube().getRandomStation();
    }

    player.pushBackStack(nextStation);
    processor.getCharacters().moveCharacters();

    return processor.getStationDescription();
  }
}