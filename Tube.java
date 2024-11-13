import java.util.ArrayList;

/**
 * This class represents the tube map. It creates all the stations and links them together.
 * @author Mehmet Kutay Bozkurt
 * @version 0.1
 */
public class Tube {
  private ArrayList<Station> stations;
  Reader reader;
  
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
  }

  /**
   * Link the station's exits together by reading from the files.
   */
  private void connectStations() {
    ArrayList<String> connectionsFile = reader.readFile("connections.txt");
    for (int i = 0; i < connectionsFile.size(); i += 6) {
      setConnection(connectionsFile.get(i), connectionsFile.get(i + 1), connectionsFile.get(i + 2), connectionsFile.get(i + 3), connectionsFile.get(i + 4));
    }
  }

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
      System.out.println("Error: station(s) not found: " + name1 + ", " + name2);
    }
  }

  public Station getStartStation() {
    return stations.get(1); // start the game at Piccadilly Circus
  }

  public Station getRandomStation() {
    int randomIndex = (int) (Math.random() * (stations.size() - 1));
    return stations.get(randomIndex);
  }
}