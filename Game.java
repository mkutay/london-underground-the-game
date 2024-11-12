/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game { 
  private Parser parser;
  private Tube theTube;

  /**
   * Create the game and initialise its internal map.
   */
  public Game() {
    parser = new Parser();
    theTube = new Tube();
  }

  /**
   *  Main play routine. Loops until end of play.
   */
  public void play() {
    printWelcome();

    // Enter the main command loop.  Here we repeatedly read commands and
    // execute them until the game is over.

    boolean finished = false;
    
    while (!finished) {
      Command command = parser.getCommand();
      finished = processCommand(command);
    }
    
    System.out.println("Thank you for playing. Good bye.");
  }

  /**
   * Print out the opening message for the player.
   */
  private void printWelcome() {
    System.out.println();
    System.out.println("Welcome to the World of Zuul!");
    System.out.println("World of Zuul is a new, incredibly boring adventure game.");
    System.out.println("Type 'help' if you need help.");
    System.out.println();
    System.out.println(theTube.getCurrentExits());
  }

  /**
   * Given a command, process (that is: execute) the command.
   * @param command The command to be processed.
   * @return true If the command ends the game, false otherwise.
   */
  private boolean processCommand(Command command) {
    boolean wantToQuit = false;

    if (command.isUnknown()) {
      System.out.println("I don't know what you mean...");
      return false;
    }

    String commandWord = command.getCommandWord();
    if (commandWord.equals("help")) {
      printHelp(command);
    } else if (commandWord.equals("go")) {
      theTube.goStation(command);
    } else if (commandWord.equals("take")) {
      theTube.takeLine(command);
    } else if (commandWord.equals("quit")) {
      wantToQuit = quit(command);
    }
    
    // else command not recognised.
    return wantToQuit;
  }

  // implementations of user commands:

  /**
   * Print out some help information.
   * Here we print some stupid, cryptic message and a list of the 
   * command words.
   */
  private void printHelp(Command command) {
    if (command.hasSecondWord()) {
      String word = command.getSecondWord();
      String description = parser.getCommandDescription(word);
      return;
    }

    System.out.println("You lost your Oyster card during your commute to work. You should find it before leaving the London Underground.\n");
    System.out.println("Your command words are:");
    parser.showCommands();
  }

  /** 
   * "Quit" was entered. Check the rest of the command to see
   * whether we really quit the game.
   * @return true, if this command quits the game, false otherwise.
   */
  private boolean quit(Command command) 
  {
    if (command.hasSecondWord()) {
      System.out.println("Quit what?");
      return false;
    } else {
      return true; // signal that we want to quit
    }
  }
}