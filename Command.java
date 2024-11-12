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
   * Create a command object. First and second word must be supplied, but
   * either one (or both) can be null.
   * @param firstWord The first word of the command. Null if the command
   *          was not recognised.
   * @param secondWord The second word of the command.
   */
  public Command(ArrayList<String> words) {
    this.words = words;
  }

  /**
   * Return the command word (the first word) of this command. If the
   * command was not understood, the result is null.
   * @return The command word.
   */
  public String getCommandWord() {
    return doesIndexExist(0) ? words.get(0) : null;
  }

  /**
   * @return The second word of this command. Returns null if there was no
   * second word.
   */
  public String getSecondWord() {
    return doesIndexExist(1) ? words.get(1) : null;
  }

  /**
   * @return true if this command was not understood.
   */
  public boolean isUnknown() {
    return !doesIndexExist(0);
  }

  /**
   * @return true if the command has a second word.
   */
  public boolean hasSecondWord() {
    return doesIndexExist(1);
  }

  /**
   * @return The third word of this command. Returns null if there was no
   * third word.
   */
  public String getThirdWord() {
    return doesIndexExist(2) ? words.get(2) : null;
  }

  /**
   * @return true if the command has a third word.
   */
  public boolean hasThirdWord() {
    return doesIndexExist(2);
  }

  private boolean doesIndexExist(int index) {
    return index < words.size();
  }
}

