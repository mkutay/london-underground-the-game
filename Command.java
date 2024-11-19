import java.util.ArrayList;

/**
 * This class is part of the "London Underground" application.
 * "London Underground" game is a simple, text based adventure game.
 * 
 * This class holds information about a command that was issued by the user. A
 * command is implemented as a list of Strings (that are the words of the command).
 * 
 * @author Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Command {
  ArrayList<String> words;

  /**
   * Constructor - Create a command object by the given words taken from the parser.
   * @param words The words of the command as an ArrayList.
   */
  public Command(ArrayList<String> words) {
    this.words = words;
  }

  /**
   * @return If the command has a given index, return true, false otherwise.
   * @param index The index to be checked.
   */
  public boolean hasIndex(int index) {
    if (index < words.size()) {
      return true;
    }
    return false;
  }

  /**
   * @return The word of the command at a certain index, or null if the index is out of bounds.
   * @param index The index of the word to be returned.
   */
  public String getWord(int index) {
    if (!hasIndex(index)) {
      return null;
    }
    return words.get(index);
  }

  /**
   * @return True if this command was not understood.
   */
  public boolean isUnknown() {
    if (!hasIndex(0) || words.get(0) == null) {
      return true;
    }
    return false;
  }
}

