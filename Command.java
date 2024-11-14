import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kölling and David J. Barnes
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
    return !hasIndex(0) || words.get(0) == null;
  }
}

