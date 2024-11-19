public class QuitCommand implements CommandAction {
  public String execute(Command command, Processor processor) {
    if (command.hasIndex(1)) {
      return processor.incorrectFormat();
    }
    // Return null to signal that we want to quit
    return null;
  }
}