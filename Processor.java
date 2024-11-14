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
   * Process the "back" command, allowing the player to go back to the previous station.
   * @return String to be outputed to System.out
   */
  public String processBackCommand(Command command) {
    // the "back" command cannot take parameters
    if (command.hasIndex(1)) {
      return incorrectFormat();
    }

    if (player.getBackStackCount() == 1) {
      return "You cannot go back any further. You are currently at the very beginning.";
    }

    player.popBackStack();
    return getCurrentExits();
  }

  /**
   * Process the "take" command, allowing the player to take a specific
   * line/direction to the next station.
   * @return String to be outputed to System.out
   */
  public String processTakeCommand(Command command) {
    if (!command.hasIndex(1)) {
      return incorrectFormat();
    }

    String word2 = command.getWord(1);
    String word3 = command.getWord(2);

    // trying to leave current station
    Station nextStation = player.getCurrentStation().getExit(word2, word3);

    if (nextStation == null) {
      if (word3 == null) {
        return "There is more than one possible exits. Please be more specific";
      }
      return "You cannot take " +
        capitalizeFirstLetter(word2) + " " +
        capitalizeFirstLetter(word3) + " line.";
    }

    // if we're at the invisible "Random" station, get an actual random station
    if (nextStation.getName().equals("Random")) {
      nextStation = theTube.getRandomStation();
    }

    player.pushBackStack(nextStation);
    return getCurrentExits();
  }

  /**
   * Process the "pick" command, allowing the player to pick up an item from the station.
   * @return String to be outputed to System.out
   */
  public String processPickCommand(Command command) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return incorrectFormat();
    }

    String word2 = command.getWord(1);
    Item item = player.getCurrentStation().getItem(word2);

    if (item == null) {
      return "There is no " + word2 + " in this station.";
    }

    if (player.pickItem(item)) {
      player.getCurrentStation().removeItem(item);
      return "You have picked up " + item.getName() + ".";
    }

    return "You cannot pick up " + item.getName() + " because it is too heavy.";
  }

  public String processDropCommand(Command command) {
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      return incorrectFormat();
    }

    String word2 = command.getWord(1);
    Item item = player.getItem(word2);

    if (item == null) {
      return "You do not have " + word2 + " in your inventory.";
    }

    player.dropItem(item);
    player.getCurrentStation().addItem(item);

    return "You have dropped " + item.getName() + " in your location.";
  }

  /**
   * Process the "inventory" command, allowing the player to see the items in their inventory.
   * @return String to be outputed to System.out
   */
  public String processInventoryCommand(Command command) {
    if (command.hasIndex(1)) {
      return incorrectFormat();
    }

    return player.getInventory();
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
