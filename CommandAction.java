/**
 * This is the CommandAction interface of the "London Underground" application.
 * "London Underground" is a simple, text-based adventure game.
 * 
 * Represents an action that can be executed by a command.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public interface CommandAction {
  /**
   * @param commandLength The length of the command to be verified.
   * @return If the given commandLength is valid.
   */
  public boolean verifyCommandLength(int commandLength);

  /**
   * Execute the command.
   * @param command The command to be executed.
   * @param processor The processor that executes the command.
   * @return The message to be displayed to the user.
   */
  public String execute(Command command, Processor processor);
}