public class QuitCommand implements CommandAction {

  /** 
   * Execute the quit command. Check the rest of the command to see
   * whether we really quit the game.
   * @param command The command to execute.
   * @return Null, if this command quits the game, an output to be written to System.out otherwise.
   */
  public String execute(Command command, Processor processor) {
    if (command.hasIndex(1)) {
      return processor.incorrectFormat();
    }
    // Return null to signal that we want to quit
    return null;
  }
}