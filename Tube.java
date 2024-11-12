import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 *
 * @author Mehmet Kutay Bozkurt
 * @version 0.1
 */
public class Tube {
  private Stack<Station> backStack;
  private ArrayList<Station> stations;
  
  public Tube() {
    stations = new ArrayList<Station>();
    backStack = new Stack<Station>();
    createStations();
  }
  
  /**
   * Create all the stations and link their exits together.
   */
  private void createStations() {
    stations.add(new Station("You are currently at Oxford Circuis station.", "Oxford Circuis"));
    stations.add(new Station("You are currently at Picadilly Circuis station.", "Picadilly Circuis"));
    stations.add(new Station("You are currently at Leicester Square station.", "Leicester Square"));
    stations.add(new Station("You are currently at Covent Garden station.", "Covent Garden"));
    stations.add(new Station("You are currently at Holborn station.", "Holborn"));
    stations.add(new Station("You are currently at Chancery Lane station. Don't forget to do your studies at Maughan Library.", "Chancery Lane"));
    stations.add(new Station("You are currently at Embankment station.", "Embankment"));
    stations.add(new Station("You are currently at Temple station. Check out the old tube map outside of the station.", "Temple"));
    stations.add(new Station("You are currently at Blackfriars station.", "Blackfriars"));
    stations.add(new Station("You are currently at Bank station.", "Bank"));
    stations.add(new Station("You are currently at Waterloo station.", "Waterloo"));
    stations.add(new Station("You are currently at Southwark station.", "Soutwark"));
    stations.add(new Station("You are currently at London Bridge station.", "London Bridge"));
    stations.add(new Station("You are now being transported to a random station on the tube.", "Random"));
    
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

    stations.get(9).setExit("Random", "Waterloo&City", stations.get(13));

    backStack.push(stations.get(1)); // start the game at Piccadilly Circuis
  }
  
  /** 
   * Try to in to one direction. If there is an exit, enter the new
   * room, otherwise print an error message.
   */
  public void processGoCommand(Command command) {
    // the "go" command can only take one parameter
    if (!command.hasIndex(1) || command.hasIndex(2)) {
      printIncorrectFormat();
      return;
    }

    String direction = command.getWord(1);
    
    if (direction.equals("back")) {
      if (backStack.size() == 1) {
        System.out.println("You cannot go back any further. You are currently at the very beginning.");
        return;
      }

      backStack.pop();
      System.out.println(getCurrentExits());
      return;
    }

    System.out.println("I don't know where I should go.");
  }

  public void processTakeCommand(Command command) {
    if (!command.hasIndex(1) || !command.hasIndex(2)) { // if there is no second or third word
      printIncorrectFormat();
      return;
    }

    String word2 = command.getWord(1);
    String word3 = command.getWord(2);

    // trying to leave current station
    Station nextStation = getCurrentStation().getExit(word2, word3);

    if (nextStation == null) {
      System.out.println("You cannot take " +
      capitalizeFirstLetter(word2) + " " +
      capitalizeFirstLetter(word3) + " line.");
      return;
    }

    if (nextStation.getName().equals("Random")) {
      int randomIndex = (int) (Math.random() * (stations.size() - 1));
      nextStation = stations.get(randomIndex);
    }

    backStack.push(nextStation);
    System.out.println(getCurrentExits());
  }

  public String getCurrentExits() {
    return getCurrentStation().getDescription();
  }
  
  /**
   * @return The current station 
   */
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
