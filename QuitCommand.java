public class QuitCommand implements CommandAction {
  private int commandLength1;
  private int commandLength2;
  
  /**
   * @param commandLength1 The first valid command length.
   * @param commandLength2 The second valid command length.
   */
  public QuitCommand(int commandLength1, int commandLength2) {
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
   * Execute the quit command. Check the rest of the command to see
   * whether we really quit the game.
   * @param command The command to execute.
   * @return Null, if this command quits the game, an output to be written to System.out otherwise.
   */
  public String execute(Command command, Processor processor) {
    // Return null to signal that we want to quit
    return null;
  }
}