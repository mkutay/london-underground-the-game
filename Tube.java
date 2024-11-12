import java.util.ArrayList;

/**
 * This class represents the tube map. It creates all the stations and links them together.
 * @author Mehmet Kutay Bozkurt
 * @version 0.1
 */
public class Tube {
  private ArrayList<Station> stations;
  
  public Tube() {
    stations = new ArrayList<Station>();
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
    
    setConnection("Oxford Circuis", "Picadilly Circuis", "Bakerloo", "Southbound", "Northbound");
    setConnection("Picadilly Circuis", "Embankment", "Bakerloo", "Southbound", "Northbound");
    setConnection("Embankment", "Waterloo", "Bakerloo", "Southbound", "Northbound");

    setConnection("Oxford Circuis", "Holborn", "Central", "Eastbound", "Westbound");
    setConnection("Holborn", "Chancery Lane", "Central", "Eastbound", "Westbound");
    setConnection("Chancery Lane", "Bank", "Central", "Eastbound", "Westbound");

    setConnection("Embankment", "Temple", "District", "Eastbound", "Westbound");
    setConnection("Temple", "Blackfriars", "District", "Eastbound", "Westbound");
    setConnection("Blackfriars", "Bank", "District", "Eastbound", "Westbound");

    setConnection("Waterloo", "Southwark", "Jubilee", "Eastbound", "Westbound");
    setConnection("Southwark", "London Bridge", "Jubilee", "Eastbound", "Westbound");

    setConnection("Picadilly Circuis", "Leicester Square", "Picadilly", "Eastbound", "Westbound");
    setConnection("Leicester Square", "Covent Garden", "Picadilly", "Eastbound", "Westbound");
    setConnection("Covent Garden", "Holborn", "Picadilly", "Eastbound", "Westbound");
    
    setConnection("Bank", "London Bridge", "Northern", "Southbound", "Northbound");
    
    setConnection("Bank", "Random", "Waterloo&City", "Random", null);
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
      if (direction2 != null) {
        station2.setExit(direction2, line, station1);
      }
    } else {
      System.out.println("error");
    }
  }

  public Station getStartStation() {
    return stations.get(1); // start the game at Piccadilly Circuis
  }

  public Station getRandomStation() {
    int randomIndex = (int) (Math.random() * (stations.size() - 1));
    return stations.get(randomIndex);
  }
}