public class HelpCommand implements CommandAction {
  /**
   * @return Some help information. If the player types "help"
   * followed by a command, it will return the description of that command.
   * @param command The help command to be processed
   */
  public String execute(Command command, Processor processor) {
    CommandWords commands = new CommandWords();

    if (command.hasIndex(1)) {
      String commandName = command.getWord(1);
      String description = commands.getCommandDescription(commandName);

      if (description == null) {
        return "I don't know what you mean by \"" + commandName + "\".";
      }
      return description;
    }

    return "You lost your Oyster card during your commute to work. You should find it before leaving the London Underground.\n\n"
      + "You can type the following commands:\n"
      + commands.getEveryCommand()
      + "\n\nFor more information about a command, type \"help [command]\".";
  }
}