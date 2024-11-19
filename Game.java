/**
 * This class is the main class of the "London Underground" application. 
 * "London Underground" is a simple, text based adventure game. Players
 * can walk around some of the underground stations in central London,
 * pick up and drop items, and try to find a way out of the stations by
 * talking with characters and using items.
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
   * Constructor - Creeate the parser and processor objects.
   */
  public Game() {
    parser = new Parser();
    processor = new Processor();
  }

  /**
   * Main play routine. Loops until the end of the game.
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
    System.out.println(processor.getDescription());
  }

  /**
   * Process the command given by the user.
   * @param command The command to be processed.
   * @return True if the command ends the game, false otherwise.
   */
  private boolean processCommand(Command command) {
    String output = processor.processCommand(command);

    System.out.println();

    if (output == null) {
      System.out.println("Thank you for playing. Goodbye!");
      return true;
    }

    System.out.println(output);
    return false;
  }
}