public interface CommandAction {
  String execute(Command command, Processor processor);
}