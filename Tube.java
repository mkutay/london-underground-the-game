import java.util.ArrayList;

/**
 * This class represents the tube map. It creates all the stations and links them together.
 * It also creates the items that are placed in the stations.
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Tube {
  private ArrayList<Station> stations;
  Reader reader; // helper to read from files
  
  public Tube() {
    stations = new ArrayList<Station>();
    reader = new Reader();

    createStations();
    connectStations();
  }
  
  /**
   * Create all the stations after reading from the files.
   */
  private void createStations() {
    ArrayList<String> stationsFile = reader.readFile("stations.txt");
    for (int i = 0; i < stationsFile.size(); i += 2) {
      Station station = new Station(stationsFile.get(i), stationsFile.get(i + 1));
      stations.add(station);
    }
    stations.add(new Station("You are now being transported to a random station on the tube.", "Random"));
  }

  /**
   * Link the station's exits together by reading from the files.
   */
  private void connectStations() {
    ArrayList<String> connectionsFile = reader.readFile("connections.txt");
    for (int i = 0; i < connectionsFile.size(); i += 6) {
      setConnection(connectionsFile.get(i), connectionsFile.get(i + 1), connectionsFile.get(i + 2), connectionsFile.get(i + 3), connectionsFile.get(i + 4));
    }

    setConnection("Bank", "Random", "Waterloo&City", "Random", "Random");
  }

  /**
   * Set the connection between two stations.
   * @param name1 The name of the first station.
   * @param name2 The name of the second station.
   * @param line The line that connects the two stations, like Bakerloo
   * @param direction1 The direction from the first station to the second station, like Northbound
   * @param direction2 The direction from the second station to the first station, like Southbound
   */
  private void setConnection(String name1, String name2, String line, String direction1, String direction2) {
    Station station1 = null;
    Station station2 = null;
    for (Station station : stations) {
      if (station.getName().equals(name1)) {
        station1 = station;
      } else if (station.getName().equals(name2)) {
        station2 = station;
      }
    }

    if (station1 != null && station2 != null) {
      station1.setExit(direction1, line, station2);
      station2.setExit(direction2, line, station1);
    } else {
      // This should not run, but is used as a precaution.
      System.out.println("ERROR: station(s) not found: " + name1 + ", " + name2);
    }
  }

  /**
   * @return the predefined starting station of the game.
   */
  public Station getStartStation() {
    return stations.get(1); // start the game at Piccadilly Circus
  }

  /**
   * @return a random station from the tube, not including the Random station.
   */
  public Station getRandomStation() {
    int randomIndex = (int) (Math.random() * (stations.size() - 1));
    return stations.get(randomIndex);
  }

  /**
   * @param name The name of the station.
   * @return The station with the given name.
   */
  public Station getStation(String name) {
    for (Station station : stations) {
      if (station.getName().equals(name)) {
        return station;
      }
    }
    return null;
  }
}