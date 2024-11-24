import java.util.ArrayList;
import java.util.Scanner;

/**
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as each word as a parameter. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Parser  {
  private CommandWords commands; // holds all valid command words
  private Scanner reader; // source of command input

  /**
   * Contructor - Create a parser to read from the terminal window.
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

    System.out.print("\n> "); // print prompt

    inputLine = reader.nextLine();

    // find each word from the line and add them to the list
    try (Scanner tokenizer = new Scanner(inputLine)) {
      while (tokenizer.hasNext()) {
        words.add(tokenizer.next()); // get the next word
      }
    }

    if (words.isEmpty()) {
      return new Command(words);
    }

    // Now check whether the first word interpreted as a command is known.
    // If so, create a command with it. If not, create a "null" command (for unknown command).
    if (commands.isValidCommand(words.get(0).toLowerCase(), words.size())) {
      return new Command(words);
    } else {
      words.set(0, null);
      return new Command(words);
    }
  }
}
