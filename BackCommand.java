public class BackCommand implements CommandAction {
  private int commandLength1;
  private int commandLength2;
  
  /**
   * @param commandLength1 The first valid command length.
   * @param commandLength2 The second valid command length.
   * @note The commandLength1 and commandLength2 can be equal if there is only one valid command length.
   */
  public BackCommand(int commandLength1, int commandLength2) {
    this.commandLength1 = commandLength1;
    this.commandLength2 = commandLength2;
  }

  /**
   * @param commandLength The length of the command to be verified.
   * @return If the given commandLength is valid.
   */
  public boolean verifyCommandLength(int commandLength) {
    return commandLength == commandLength1 || commandLength == commandLength2;
  }

  /** 
   * Execute the "back" command, allowing the player to go back to the previous station, indefinitely.
   * @param command Command object representing the user input.
   * @return String to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {
    if (!processor.getPlayer().goBack()) { // If the player is at the very beginning and cannot go back any further.
      return "You cannot go back any further. You are currently at the very beginning.";
    }

    return processor.getDescription(); // Return the description of the old station the player is now at.
  }
}