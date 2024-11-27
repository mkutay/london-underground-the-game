/**
 * This class is the Processor class of the "London Underground" application.
 * "London Underground" is a simple, text based adventure game that was
 * inspired by the stations found in Central London.
 * 
 * This class processes the commands entered by the user and returns
 * the appropriate output. It also holds the state of the game by having
 * the Tube and Player objects.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Processor {
  private Tube tube;
  private Player player;
  private CommandWords validCommands;

  /**
   * Constructor - Create the tube and player objects.
   * Set the player's starting location as the Piccadilly Circus station
   * and the max weight of the player's inventory as 10 lbs.
   */
  public Processor() {
    validCommands = new CommandWords();
    tube = new Tube();
    // Set the player's initial location and the max weight of their inventory.
    player = new Player(tube.getStation("Piccadilly Circus"), 10);
  }

  /**
   * Process the command given by the user and output accordingly.
   * @param command The command to be processed.
   * @return True if the game ends, false otherwise.
   */
  public boolean processCommand(Command command) {
    System.out.println();

    if (command.getWord(0) == null) {
      // If the user enters nothing or given command is not in ValidCommands
      System.out.println("I don't know what you mean. You might have entered it as an incorrect format. Please enter again or try typing \"help\" for more information.");
    }

    String commandWord = command.getWord(0).toLowerCase();
    String output;
    if (commandWord.equals("help")) {
      output = helpCommand(command);
    } else if (commandWord.equals("take")) {
      output = takeCommand(command);
    } else if (commandWord.equals("drop")) {
      output = dropCommand(command);
    } else if (commandWord.equals("inventory")) {
      output = "Inventory:" + player.getInventory().toString();
    } else if (commandWord.equals("back")) {
      output = backCommand(command);
    } else if (commandWord.equals("talk")) {
      output = talkCommand(command);
    } else if (commandWord.equals("give")) {
      output = giveCommand(command);
    } else if (commandWord.equals("pick")) {
      output = pickCommand(command);
    } else if (commandWord.equals("use")) {
      output = useCommand(command);
    } else {
      output = "Thank you for playing. Goodbye!"; // the "quit" command is handled here
    }

    System.out.println(output);
    
    if (output.contains("Goodbye!") || output.contains("Congratulations!")) {
      // If the game ends (that is, when the output either contains Goodbye! or Congratulations!)
      return true;
    }

    return false;
  }

  /**
   * @return The description of the current station, including the items, exits, and characters.
   */
  public String getDescription() {
    String returnString = player.getCurrentStation().getDescription();
    String characterString = tube.getCharactersDescription(player.getCurrentStation());
    if (!characterString.equals("")) {
      // If there are characters in the station, add them to the returnString.
      returnString += "\n\nYou also find the following characters in the station:" + characterString;
    }

    return returnString;
  }

  /**
   * Execute the "back" command, allowing the player to go back to the previous station until the very beginning.
   * @param command The command to be executed.
   * @return The output of the command that will be displayed to the user.
   */
  private String backCommand(Command command) {
    if (!player.goBack()) { // If the player is at the very beginning and cannot go back any further.
      return "You cannot go back any further. You are currently at the very beginning.";
    }
    return getDescription(); // Return the description of the old station the player is now at.
  }

  /**
   * Execute the "drop" command, allowing the player to drop an item from their inventory onto the current station.
   * @param command The command to be executed.
   * @return String to be displayed to the user.
   */
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

  /**
   * Execute the "give" command, allowing the player to give an item from their inventory to a character.
   * @param command The command to be executed.
   * @return String to be outputed to the console.
   */
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

  /**
   * @param command The help command to be processed.
   * @return Some help information. If the player types an additional command (ie "help take"), it will return the description of that command.
   */
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

  /**
   * Execute the "pick" command, allowing the player to pick up an item from the current station.
   * @param command The pick command to be processed.
   * @return The output of the command.
   */
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

  /**
   * Execute the "take" command, allowing the player to take a specific exit from the current station.
   * @note If the player goes to the invisible "Random" station, the player will be automatically moved again to a random station.
   * @param command The take command to be processed.
   * @return The output of the command.
   */
  private String takeCommand(Command command) {
    String directionName = command.getWord(1);
    String lineName = command.getWord(2);

    Station nextStation;

    if (lineName == null) {
      nextStation = player.getCurrentStation().getExit(directionName);
      if (nextStation == null) {
        return "Either there is more than one possible exit or you have entered incorrectly. Please be more specific.";
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

  /**
   * Execute the "talk" command, allowing the player to talk to a character.
   * @param command The talk command to be processed.
   * @return The output of the command.
   */
  private String talkCommand(Command command) {
    String characterName = command.getWord(1);

    Character character = tube.getCharacter(characterName.toLowerCase());
    if (character == null) {
      return "There is no character with the name " + characterName + ".";
    }

    String returnString = character.getDialogue();

    return character.getName() + ": " + returnString;
  }

  /**
   * Execute the "use" command, allowing the player to use an item from their inventory.
   * @param command The use command to be processed.
   * @return The output of the command to be displayed on the console.
   */
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