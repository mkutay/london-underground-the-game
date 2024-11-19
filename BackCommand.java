public class BackCommand implements CommandAction {

  /** 
   * Execute the "back" command, allowing the player to go back to the previous station, indefinitely.
   * @param command Command object representing the user input.
   * @return String to be outputed to System.out.
   */
  public String execute(Command command, Processor processor) {
    if (command.hasIndex(1)) {
      return processor.incorrectFormat();
    }

    Player player = processor.getPlayer();

    if (!player.goBack()) { // If the player is at the very beginning and cannot go back any further.
      return "You cannot go back any further. You are currently at the very beginning.";
    }

    return processor.getStationDescription();
  }
}