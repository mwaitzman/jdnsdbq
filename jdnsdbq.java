/*import java.net.HttpsURLConnection;
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
*/
import java.net.HttpsURLConnection;

class jdnsdbq {
  static String API_Key;
  public static void main(String[] args) {
    //findApiKey();
    File configFile = "config.txt";
    from config file get value of API_KEY and assign it to jdnsdbq.API_KEY
    //////////////
    URL url = "https://api.dnsdb.info/";
    HttpsUrlConnection conn = (HttpsURLConnection)url.openConnection();
    Map<String, String> headers = new HashMap<>();
    headers.put("X-API-Key", API_KEY);
    for (String headerKey : headers.keySet()) {
      conn.setRequestProperty(headerKey, headers.get(headerKey));
      conn.setRequestMethod("GET");
    }

  }
}//main
  /*static void findApiKey() {
    scanFor(File="config.txt", var = "API_KEY",) -> return var.value: else {
      //add code to get the key from user and save it to the config file
    }

  }*/
//class
