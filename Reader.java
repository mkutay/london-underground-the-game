import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class reads a file and returns its content as an ArrayList of Strings.
 * The file should be in the same directory as the program.
 * The readFile method of this class was taken from https://www.w3schools.com/java/java_files_read.asp
 * 
 * @author W3Schools and Mehmet Kutay Bozkurt
 * @version 1.0
 */
public class Reader {
  /**
   * @return an ArrayList of Strings with the content of the file on each line.
   */
  public ArrayList<String> readFile(String fileName) {
    ArrayList<String> returnList = new ArrayList<>();
    try {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        returnList.add(scanner.nextLine());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't read the " + fileName + " file.");
      e.printStackTrace();
    }
    return returnList;
  }
}