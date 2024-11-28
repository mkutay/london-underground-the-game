/**
 * This class is the main class of the "The London Underground" application. 
 * "The London Underground" is a simple, text based adventure game that was
 * inspired by the stations found in Central London. Players can walk
 * around some of the underground stations in the city, pick up
 * and drop items, and try to find a way out of the stations by
 * talking with characters and using items.
 * 
 * This main class creates and initialises the parser and the processor,
 * which are used to read and process the user commands, respectively.
 * It also initiates the game loop.
 * 
 * @author  Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Game { 
  private Parser parser; // reads and parses the user's input commands.
  private Processor processor; // processes the commands and updates the game state.

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

    boolean finished = false;
    
    // Enter the main command loop. Here we repeatedly read commands and execute them until the game is over.
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
    System.out.println(processor.getDescription()); // print the initial description of the starting location.
  }

  /**
   * Process the command given by the user and read by the parser.
   * @param command The command to be processed.
   * @return True if the command ends the game, false otherwise.
   */
  private boolean processCommand(Command command) {
    boolean isEnded = processor.processCommand(command);
    return isEnded;
  }
}