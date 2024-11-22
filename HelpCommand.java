public class HelpCommand implements CommandAction {
  private static final int commandLength1 = 1;
  private static final int commandLength2 = 2;

  /**
   * @param commandLength The length of the command to be verified.
   * @return If the given commandLength is valid.
   */
  public boolean verifyCommandLength(int commandLength) {
    return commandLength == commandLength1 || commandLength == commandLength2;
  }

  /**
   * @return Some help information. If the player types "help"
   * followed by a command, it will return the description of that command.
   * @param command The help command to be processed.
   */
  public String execute(Command command, Processor processor) {
    CommandWords commands = new CommandWords();
    String commandName = command.getWord(1);

    if (commandName != null) {
      String description = commands.getCommandDescription(commandName);

      if (description == null) {
        return "I don't know what you mean by \"" + commandName + "\".";
      }
      return description;
    }

    return "You lost your Oyster card during your commute to work. You should find it before leaving the London Underground.\n\n"
      + "You can type the following commands:\n"
      + commands.getAll()
      + "\n\nFor more information about a command, type \"help [command]\".";
  }
}