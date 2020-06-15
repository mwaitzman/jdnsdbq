import java.net.HttpsURLConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Scanner;
class jdnsdbq {
  static String API_Key;
  public static void main(String[] args) {
    boolean configValid = checkConfigFile();
    if (configValid == false) {
      System.out.println("Please input your API Key.");
      Scanner sc = new Scanner(System.in);
      API_Key = sc.next();
    }
  }
  static boolean checkConfigFile {
    File configFile = new File("config.txt");
    String currentLine;
    int index;
    Scanner configFileScanner = new Scanner(configFile);
    while (configFileScanner.hasNext()) {
      currentLine = configFileScanner.nextLine();
      if ((index = currentLine.indexOf("API_Key")) != -1) {
        API_Key = currentLine.substring(index);
        return true;
      }
    }
    return false;
  }
}
