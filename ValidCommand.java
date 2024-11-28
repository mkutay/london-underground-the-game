import java.util.ArrayList;

/**
 * This class is the valid command class of the "The London Underground"
 * application. "The London Underground" is a simple, text based adventure game
 * that was inspired by the stations found in Central London.
 * 
 * This class holds the description and valid lengths of a valid command.
 * A valid command can have one or two valid lengths.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class ValidCommand {
  private String description; // the description of the command
  private ArrayList<Integer> commandLengths; // the valid lengths of the command

  /**
   * Constructor - Create a new valid command with a description and two valid command lengths.
   */
  public ValidCommand(String description, int commandLength1, int commandLength2) {
    commandLengths = new ArrayList<>();
    commandLengths.add(commandLength1);
    commandLengths.add(commandLength2);
    this.description = description;
  }

  /**
   * Constructor - Create a new valid command with a description and one valid command length.
   */
  public ValidCommand(String description, int commandLength) {
    commandLengths = new ArrayList<>();
    commandLengths.add(commandLength);
    this.description = description;
  }

  /**
   * @return The description of the command.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Verify if a command length is valid for this command.
   * @param commandLength The length of the command to be verified.
   * @return True if the command length is valid, false otherwise.
   */
  public boolean verifyCommandLength(int commandLength) {
    return commandLengths.contains(commandLength);
  }
}
