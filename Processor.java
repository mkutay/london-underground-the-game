/**
 * This class is the processor class of the Game.
 */
public class Processor {
  private Tube theTube;
  private Player player;

  public Processor() {
    theTube = new Tube();
    player = new Player(theTube.getStartStation());
  }

  /** 
   * Try to in to one direction. If there is an exit, enter the new
   * room, otherwise print an error message.
   */
  public String processGoCommand(Command command) {
    // the "go" command can only take one parameter
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return incorrectFormat();
    }

    String direction = command.getWord(1);
    
    if (direction.equals("back")) {
      if (player.getBackStackCount() == 1) {
        return "You cannot go back any further. You are currently at the very beginning.";
      }

      player.popBackStack();
      return getCurrentExits();
    }

    return "I don't know where I should go.";
  }

  public String processTakeCommand(Command command) {
    if (!command.hasIndex(1) || !command.hasIndex(2)) { // if there is no second or third word
      return incorrectFormat();
    }

    String word2 = command.getWord(1);
    String word3 = command.getWord(2);

    // trying to leave current station
    Station nextStation = player.getCurrentStation().getExit(word2, word3);

    if (nextStation == null) {
      return "You cannot take " +
      capitalizeFirstLetter(word2) + " " +
      capitalizeFirstLetter(word3) + " line.";
    }

    if (nextStation.getName().equals("Random")) {
      nextStation = theTube.getRandomStation();
    }

    player.pushBackStack(nextStation);
    return getCurrentExits();
  }

  public String getCurrentExits() {
    return player.getCurrentStation().getDescription();
  }

  private String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

  private String incorrectFormat() {
    return "Entered input has incorrect format. Please enter again.";
  }
}
