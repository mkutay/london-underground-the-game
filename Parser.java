import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Parser 
{
  private CommandWords commands; // holds all valid command words
  private Scanner reader; // source of command input

  /**
   * Create a parser to read from the terminal window.
   */
  public Parser() {
    commands = new CommandWords();
    reader = new Scanner(System.in);
  }

  /**
   * @return The next command from the user.
   */
  public Command getCommand() {
    String inputLine; // will hold the full input line
    ArrayList<String> words = new ArrayList<>();

    System.out.print("> "); // print prompt

    inputLine = reader.nextLine();

    // find up to two words on the line
    try (Scanner tokenizer = new Scanner(inputLine)) {
      if (tokenizer.hasNext()) {
        words.add(tokenizer.next()); // get first word
        if (tokenizer.hasNext()) {
          words.add(tokenizer.next()); // get second word
          if (tokenizer.hasNext()) {
            words.add(tokenizer.next()); // get third word
          }
        }
      }
    }

    // Now check whether this word is known. If so, create a command
    // with it. If not, create a "null" command (for unknown command).
    if (commands.isCommand(words.get(0))) {
      return new Command(words);
    } else {
      words.set(0, null);
      return new Command(words); 
    }
  }

  /**
   * Print out a list of valid command words.
   */
  public void showCommands() {
    commands.showAll();
  }

  public String getCommandDescription(String command) {
    return commands.getCommandDescription(command);
  }
}
