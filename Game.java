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
  private Processor processor;

  /**
   * Create the game and initialise its internal map.
   */
  public Game() {
    parser = new Parser();
    processor = new Processor();
  }

  /**
   *  Main play routine. Loops until end of play.
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
    
    System.out.println("Thank you for playing. Good bye.");
  }

  /**
   * Print out the opening message for the player.
   */
  private void printWelcome() {
    System.out.println();
    System.out.println("Welcome to the London Underground!");
    System.out.println("This is a game where you are lost in the London tube without your Oyster card.");
    System.out.println("You should find it before you leaving the stations.");
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
    if (command == null || command.isUnknown()) {
      System.out.println("I don't know what you mean. Try typing \"help\" for more information.");
      return false;
    }

    boolean wantToQuit = false;
    String commandWord = command.getWord(0);

    System.out.println();

    if (commandWord.equals("help")) {
      printHelp(command);
    } else if (commandWord.equals("go")) {
      System.out.println(processor.processGoCommand(command));
    } else if (commandWord.equals("take")) {
      System.out.println(processor.processTakeCommand(command));
    } else if (commandWord.equals("quit")) {
      wantToQuit = quit(command);
    }
    
    return wantToQuit;
  }

  /**
   * Print out some help information. If the player types "help"
   * followed by a command, it will print out the description of that command.
   * @param command The help command to be processed
   */
  public void printHelp(Command command) {
    if (command.hasIndex(1)) {
      String word = command.getWord(1);
      String description = parser.getCommandDescription(word);
      System.out.println(description != null ? description : "I don't know what you mean.");
    }

    System.out.println("You lost your Oyster card during your commute to work. You should find it before leaving the London Underground.\n");
    System.out.println("Your command words are:");
    System.out.println(parser.getCommands());
  }

  /** 
   * "quit" was entered. Check the rest of the command to see
   * whether we really quit the game.
   * @return true, if this command quits the game, false otherwise.
   */
  public boolean quit(Command command) {
    if (command.hasIndex(1)) {
      System.out.println("Quit what?");
      return false;
    }

    return true; // signal that we want to quit
  }
}