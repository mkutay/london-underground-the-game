import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class is the Reader class of the "London Underground" application.
 * "London Underground" is a simple, text based adventure game that was
 * inspired by the stations found in Central London.
 * 
 * This class reads a file and returns its content as an ArrayList of
 * Strings, seperated by new lines. The file should be in the same
 * directory as the program.
 * 
 * @author Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Reader {
  /**
   * This method was taken from https://www.w3schools.com/java/java_files_read.asp.
   * @return An ArrayList of Strings with the content of the file that is seperated by new lines.
   */
  public static ArrayList<String> readFile(String fileName) {
    ArrayList<String> returnList = new ArrayList<>();
    try {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        returnList.add(scanner.nextLine());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      // This exception should not happen in the game, but it is still handled.
      System.out.println("Couldn't read the " + fileName + " file.");
      e.printStackTrace();
    }
    return returnList;
  }
}