import java.util.ArrayList;
import java.util.Stack;

/**
 * Write a description of class Tube here.
 *
 * @author Mehmet Kutay Bozkurt
 * @version 0.1
 */
public class Tube {
  private Stack<Station> backStack;
  private ArrayList<Station> stations;
  private String randomStation = "bank";
  private String randomLine = "waterloo&city";
  
  public Tube() {
    stations = new ArrayList<Station>();
    backStack = new Stack<Station>();
    createStations();
  }
  
  /**
   * Create all the rooms and link their exits together.
   */
  private void createStations() {
    stations.add(new Station("You are at Oxford Circuis station.", "Oxford Circuis"));
    stations.add(new Station("You are at Picadilly Circuis station.", "Picadilly Circuis"));
    stations.add(new Station("You are at Leicester Square station.", "Leicester Square"));
    stations.add(new Station("You are at Covent Garden station.", "Covent Garden"));
    stations.add(new Station("You are at Holborn station.", "Holborn"));
    stations.add(new Station("You are at Chancery Lane station. Don't forget to do your studies at Maughan Library.", "Chancery Lane"));
    stations.add(new Station("You are at Embankment station.", "Embankment"));
    stations.add(new Station("You are at Temple station. Check out the old tube map outside of the station.", "Temple"));
    stations.add(new Station("You are at Blackfriars station.", "Blackfriars"));
    stations.add(new Station("You are at Bank station.", "Bank"));
    stations.add(new Station("You are at Waterloo station.", "Waterloo"));
    stations.add(new Station("You are at Southwark station.", "Soutwark"));
    stations.add(new Station("You are at London Bridge station.", "London Bridge"));
    
    stations.get(0).setExit("Southbound", "Bakerloo", stations.get(1));
    stations.get(1).setExit("Northbound", "Bakerloo", stations.get(0));
    
    stations.get(1).setExit("Southbound", "Bakerloo", stations.get(6));
    stations.get(6).setExit("Northbound", "Bakerloo", stations.get(1));
    
    stations.get(6).setExit("Southbound", "Bakerloo", stations.get(10));
    stations.get(10).setExit("Northbound", "Bakerloo", stations.get(6));
    
    stations.get(0).setExit("Eastbound", "Central", stations.get(4));
    stations.get(4).setExit("Westbound", "Central", stations.get(0));
    
    stations.get(4).setExit("Eastbound", "Central", stations.get(5));
    stations.get(5).setExit("Westbound", "Central", stations.get(4));
    
    stations.get(5).setExit("Eastbound", "Central", stations.get(9));
    stations.get(9).setExit("Westbound", "Central", stations.get(5));
    
    stations.get(1).setExit("Eastbound", "Picadilly", stations.get(2));
    stations.get(2).setExit("Westbound", "Picadilly", stations.get(1));
    
    stations.get(2).setExit("Eastbound", "Picadilly", stations.get(3));
    stations.get(3).setExit("Westbound", "Picadilly", stations.get(2));
    
    stations.get(3).setExit("Eastbound", "Picadilly", stations.get(4));
    stations.get(4).setExit("Westbound", "Picadilly", stations.get(3));
    
    stations.get(4).setExit("Eastbound", "Picadilly", stations.get(5));
    stations.get(5).setExit("Westbound", "Picadilly", stations.get(4));
    
    stations.get(6).setExit("Eastbound", "District", stations.get(7));
    stations.get(7).setExit("Westbound", "District", stations.get(6));
    
    stations.get(7).setExit("Eastbound", "District", stations.get(8));
    stations.get(8).setExit("Westbound", "District", stations.get(7));
    
    stations.get(8).setExit("Eastbound", "District", stations.get(9));
    stations.get(9).setExit("Westbound", "District", stations.get(8));
    
    stations.get(10).setExit("Eastbound", "Jubilee", stations.get(11));
    stations.get(11).setExit("Westbound", "Jubilee", stations.get(10));
    
    stations.get(11).setExit("Eastbound", "Jubilee", stations.get(12));
    stations.get(12).setExit("Westbound", "Jubilee", stations.get(11));
    
    stations.get(12).setExit("Northbound", "Northern", stations.get(9));
    stations.get(9).setExit("Southbound", "Northern", stations.get(12));

    backStack.push(stations.get(9)); // start the game at Piccadilly Circuis
  }
  
  /** 
   * Try to in to one direction. If there is an exit, enter the new
   * room, otherwise print an error message.
   */
  public void goStation(Command command) {
    // the "go" command can only take one parameter
    if (!command.hasSecondWord() || command.hasThirdWord()) {
      System.out.println("Enter the input correctly.");
      return;
    }

    String directionKey = command.getSecondWord();
    
    if (directionKey.equals("back")) {
      if (backStack.size() == 1) {
        System.out.println("You cannot go back any further you are at the very beginning.");
        return;
      }
      backStack.pop();
      System.out.println(getCurrentExits());
		} else {
      System.out.println("I don't know where I should go.");
    }
  }

  public void takeLine(Command command) {
    if (!command.hasSecondWord()) {
      // if there is no second word, we don't know where to go
      printIncorrectFormat();
      return;
    }

    String word2 = command.getSecondWord();
    Station nextStation;
    
    if (!command.hasThirdWord()) {
      if (!getCurrentStation().getName().toLowerCase().equals(randomStation) || !word2.toLowerCase().equals(randomLine)) {
        printIncorrectFormat();
        return;
      }
      int randomIndex = (int) (Math.random() * stations.size());
      
      nextStation = stations.get(randomIndex);
    } else {
      String word3 = command.getThirdWord();

      // trying to leave current station
      nextStation = getCurrentStation().getExit(word2, word3);

      if (nextStation == null) {
        System.out.println("You cannot take " +
        capitalizeFirstLetter(word2) + " " +
        capitalizeFirstLetter(word3) + " line.");
        return;
      }
    }

    backStack.push(nextStation);
    System.out.println(getCurrentExits());
  }

  public String getCurrentExits() {
    String str = getCurrentStation().getLongDescription();
    if (!getCurrentStation().getName().toLowerCase().equals(randomStation)) {
      return str + ".";
    }
    str += ",\n  Waterloo&City line to a random station on the tube.";
    return str;
  }
  
  private Station getCurrentStation() {
    // the top of the stack is the current station
    return backStack.peek();
  }

	private String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

  private void printIncorrectFormat() {
    System.out.println("Entered input has incorrect format. Please enter again.");
  }
}
