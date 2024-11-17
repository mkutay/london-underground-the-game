import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds information about a command that was issued by the user. A
 * command is implemented as a list of Strings (that is the words of the command).
 * 
 * @author Michael Kölling, David J. Barnes, and Mehmet Kutay Bozkurt
 * @version 2016.02.29
 */

public class Command {
  ArrayList<String> words;

  /**
   * Create a command object by the given words taken from the parser.
   * @param words 
   */
  public Command(ArrayList<String> words) {
    this.words = words;
  }

  /**
   * @return If the command has a given index, return true, false otherwise.
   */
  public boolean hasIndex(int index) {
    return index < words.size();
  }

  /**
   * @return The word of the command at a certain index, or null if the index is out of bounds.
   */
  public String getWord(int index) {
    return hasIndex(index) ? words.get(index) : null;
  }

  /**
   * @return true if this command was not understood.
   */
  public boolean isUnknown() {
    if (!hasIndex(0) || words.get(0) == null) {
      return true;
    }
    return false;
  }
}

