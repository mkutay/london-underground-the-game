public class BackCommand implements CommandAction {
  public String execute(Command command, Processor processor) {
    if (command.hasIndex(1)) {
      return processor.incorrectFormat();
    }

    Player player = processor.getPlayer();

    if (player.getBackStackCount() == 1) {
      return "You cannot go back any further. You are currently at the very beginning.";
    }

    player.popBackStack();
    return processor.getStationDescription();
  }
}