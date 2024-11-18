import java.util.ArrayList;

/**
 * This class represents the characters in the game.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Characters {
  private ArrayList<Character> characters;

  public Characters() {
    characters = new ArrayList<Character>();
  }

  /**
   * Add a character to the list. 
   */
  public void addCharacter(Character character) {
    characters.add(character);
  }

  /**
   * @return the character with the given name, null if it doesn't exist.
   */
  public Character getCharacter(String name) {
    for (Character character : characters) {
      if (character.getName().toLowerCase().equals(name)) {
        return character;
      }
    }
    return null;
  }

  /**
   * Move all characters to a random station that they are allowed to go to.
   */
  public void moveCharacters() {
    for (Character character : characters) {
      character.moveRandom();
      System.out.println(character.getName() + " is now at " + character.getCurrentStation().getName());
    }
  }

  public Characters getCharactersOnStation(Station station) {
    Characters charactersOnStation = new Characters();
    for (Character character : characters) {
      if (character.getCurrentStation().equals(station)) {
        charactersOnStation.addCharacter(character);
      }
    }
    return charactersOnStation;
  }

  public String toString() {
    String characterString = "You also find the following characters in the station:";
    for (Character character : characters) {
      characterString += "\n  " + character.getName();
    }
    return characterString;
  }
}
