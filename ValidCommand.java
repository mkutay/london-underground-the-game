import java.util.ArrayList;

public class ValidCommand {
  private String description;
  private ArrayList<Integer> commandLengths;

  public ValidCommand(String description, int commandLength1, int commandLength2) {
    commandLengths = new ArrayList<>();
    commandLengths.add(commandLength1);
    commandLengths.add(commandLength2);
    this.description = description;
  }

  public ValidCommand(String description, int commandLength) {
    commandLengths = new ArrayList<>();
    commandLengths.add(commandLength);
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public boolean verifyCommandLength(int commandLength) {
    return commandLengths.contains(commandLength);
  }
}
