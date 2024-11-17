import java.util.Map.Entry;

/**
 * This class is the main class of the "London Underground" application. 
 * "London Underground" is a very simple, text based adventure game. Players
 * can walk around some of the underground stations in central London,
 * pick up and drop items, and try to find a way out of the stations.
 * 
 * This main class creates and initialises the parser and the processor,
 * which are used to read and process the user commands. It also initiates
 * the game loop.
 * 
 * @author  Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 1.0
 */

public class Game { 
  private Parser parser;
  private Processor processor;

  /**
   * Create the game and initialise its internal map.
   */
  public Game() {
    parser = new Parser();
    processor = new Processor();
  }

  /**
   * Main play routine. Loops until end of play.
   */
  public void play() {
    printWelcome();

    // Enter the main command loop. Here we repeatedly read commands and
    // execute them until the game is over.

    boolean finished = false;
    
    while (!finished) {
      Command command = parser.getCommand();
      finished = processCommand(command);
    }
  }

  /**
   * Print out the opening message for the player.
   */
  private void printWelcome() {
    System.out.println();
    System.out.println("Welcome to the London Underground!");
    System.out.println("This is a game where you are lost in the London tube without your Oyster card.");
    System.out.println("You should find it before you leave the underground.");
    System.out.println("Type \"help\" if you need help.");
    System.out.println();
    System.out.println(processor.getCurrentExits());
  }

  /**
   * Given a command, process the command.
   * @param command The command to be processed.
   * @return true if the command ends the game, false otherwise.
   */
  private boolean processCommand(Command command) {
    System.out.println();

    if (command.isUnknown()) {
      System.out.println("I don't know what you mean. Try typing \"help\" for more information.");
      return false;
    }

    String commandWord = command.getWord(0).toLowerCase();

    String output = null;
    boolean endGame = false;

    // the processor returns Strings to be printed out
    if (commandWord.equals("help")) {
      output = printHelp(command);
    } else if (commandWord.equals("back")) {
      output = processor.back(command);
    } else if (commandWord.equals("take")) {
      output = processor.take(command);
    } else if (commandWord.equals("quit")) {
      output = processor.quit(command);
    } else if (commandWord.equals("pick")) {
      output = processor.pick(command);
    } else if (commandWord.equals("drop")) {
      output = processor.drop(command);
    } else if (commandWord.equals("inventory")) {
      output = processor.inventory(command);
    } else if (commandWord.equals("use")) {
      Entry<Boolean, String> foo = processor.use(command);
      output = foo.getValue();
      endGame = foo.getKey();
    } else if (commandWord.equals("give")) {
      output = processor.give(command);
    } else if (commandWord.equals("talk")) {
      output = processor.talk(command);
    }

    if (output == null) {
      System.out.println("Thank you for playing. Good Bye!");
      return true;
    }

    System.out.println(output);
    return endGame;
  }

  /**
   * Print out some help information. If the player types "help"
   * followed by a command, it will print out the description of that command.
   * @param command The help command to be processed
   */
  private String printHelp(Command command) {
    if (command.hasIndex(1)) {
      String word = command.getWord(1);
      String description = parser.getCommandDescription(word);

      if (description == null) {
        return "I don't know what you mean.";
      }
      return description;
    }

    return "You lost your Oyster card during your commute to work. You should find it before leaving the London Underground.\n\n" + "Your command words are:\n" + parser.getCommands();
  }
}