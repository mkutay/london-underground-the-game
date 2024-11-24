/**
 * This class is the processor class of the "London Underground" application.
 * "London Underground" is a simple, text based adventure game.
 * 
 * This class processes the commands entered by the user and returns
 * the appropriate output. It also holds the Tube, Player, and Characters
 * objects.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Processor {
  private Tube tube;
  private Player player;
  private CommandWords validCommands;

  /**
   * Constructor - Create the tube, player, and characters objects. Initialises the command registry.
   */
  public Processor() {
    validCommands = new CommandWords();
    tube = new Tube();
    player = new Player(tube.getStation("Piccadilly Circus"), 10); // set the players location as Piccadilly Circus and the max weight of the inventory as 10 lbs
  }

  /**
   * Process the command given by the user.
   * @param command The command to be processed.
   * @return The output of the command.
   */
  public String processCommand(Command command) {
    if (command.getWord(0) == null) { // if the user enters nothing or given command is not in ValidCommands
      return "I don't know what you mean. You might have entered it as an incorrect format. Please enter again or try typing \"help\" for more information.";
    }

    String commandWord = command.getWord(0).toLowerCase();
    if (commandWord.equals("help")) {
      return helpCommand(command);
    } else if (commandWord.equals("take")) {
      return takeCommand(command);
    } else if (commandWord.equals("drop")) {
      return dropCommand(command);
    } else if (commandWord.equals("inventory")) {
      return "Inventory:" + player.getInventory().toString();
    } else if (commandWord.equals("back")) {
      return backCommand(command);
    } else if (commandWord.equals("talk")) {
      return talkCommand(command);
    } else if (commandWord.equals("give")) {
      return giveCommand(command);
    } else if (commandWord.equals("pick")) {
      return pickCommand(command);
    } else if (commandWord.equals("use")) {
      return useCommand(command);
    } else {
      return null; // the "quit" command is handled here
    }
  }

  /**
   * @return The description of the current station, including the items, exits, and characters.
   */
  public String getDescription() {
    String returnString = player.getCurrentStation().getDescription();
    String characterString = tube.getCharactersDescription(player.getCurrentStation());
    if (!characterString.equals("")) {
      returnString += "\n\nYou also find the following characters in the station:" + characterString;
    }

    return returnString;
  }

  private String backCommand(Command command) {
    if (!player.goBack()) { // If the player is at the very beginning and cannot go back any further.
      return "You cannot go back any further. You are currently at the very beginning.";
    }
    return getDescription(); // Return the description of the old station the player is now at.
  }

  private String dropCommand(Command command) {
    String itemName = command.getWord(1);
    Item item = player.getInventory().getItem(itemName);
    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    player.getInventory().removeItem(item);
    player.getCurrentStation().getItems().addItem(item);

    return "You have dropped " + itemName + " in your location.";
  }

  private String giveCommand(Command command) {
    String characterName = command.getWord(1);
    String itemName = command.getWord(2);

    Character character = tube.getCharacter(characterName.toLowerCase()); // the character with name characterName.
    if (character == null) {
      return "There is no character with the name " + characterName + ".";
    }

    Item item = player.getInventory().getItem(itemName);
    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    Item exchangedItem = character.exchangeItem(item);
    if (exchangedItem == null) {
      return "The character does not want " + item.getName() + ".";
    }

    player.getInventory().removeItem(item);
    player.getInventory().addItem(exchangedItem);

    return "You have given " + item.getName() + " and received " + exchangedItem.getName() + " in exchange.";
  }

  private String helpCommand(Command command) {
    String commandName = command.getWord(1);

    if (commandName != null) {
      String description = validCommands.getCommandDescription(commandName);

      if (description == null) {
        return "I don't know what you mean by \"" + commandName + "\".";
      }
      return description;
    }

    return "You lost your Oyster card during your commute to work. You should find it before leaving the London Underground.\n\n"
      + "You can type the following commands:\n"
      + validCommands.getAll()
      + "\n\nFor more information about a command, type \"help [command]\".";
  }

  private String pickCommand(Command command) {
    String itemName = command.getWord(1);

    Item item = player.getCurrentStation().getItems().getItem(itemName);
    if (item == null) {
      return "There is no " + itemName + " in this station.";
    }

    // addItem returns true if the item was picked up (that is if it was light enough)
    if (player.getInventory().addItem(item)) {
      player.getCurrentStation().getItems().removeItem(item);
      return "You have picked up " + item.getName() + ".";
    }

    return "You cannot pick up " + item.getName() + " because it is too heavy.";
  }

  private String takeCommand(Command command) {
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

    // If the player is at the "Random" station, get an actual random station while informing the user.
    if (nextStation.getName().equals("Random")) {
      nextStation = tube.getRandomStation();

      returnString += "You have taken the Waterloo&City line and will arrive at a random station.\n\n";
    }

    player.goStation(nextStation);
    tube.moveCharacters(); // Move characters in random directions after the player moves.
    returnString += getDescription(); // Get the description of the new station.

    return returnString;
  }

  private String talkCommand(Command command) {
    String characterName = command.getWord(1);

    Character character = tube.getCharacter(characterName.toLowerCase());
    if (character == null) {
      return "There is no character with the name " + characterName + ".";
    }

    Item givenItem = character.exchangeItem(null);
    String returnString = character.getDialogue();

    if (givenItem != null) {
      // Add the item to the player's inventory if the character straight up gives an item to the player.
      player.getInventory().addItem(givenItem);
      returnString += "\n\nYou have received " + givenItem.getName() + " from " + character.getName() + ".";
    }

    return returnString;
  }

  private String useCommand(Command command) {
    String itemName = command.getWord(1);

    Item item = player.getInventory().getItem(itemName);
    if (item == null) {
      return "You do not have " + itemName + " in your inventory.";
    }

    String dialogue = item.getEffectDialogue();
    if (dialogue == null) {
      return "You cannot use " + itemName + ".";
    }

    player.getInventory().removeItem(item);
    return dialogue;
  }
}