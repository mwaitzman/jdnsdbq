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
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
class jdnsdbq {
  static String API_Key;
  static Scanner uIS = new Scanner(System.in);//UserInputScanner
  public static void main(String[] args) throws IOException, MalformedURLException {
    //findApiKey();
//    File configFile = "config.txt";
//    from config file get value of API_KEY and assign it to jdnsdbq.API_KEY
//    //////////////
API_Key = "dce-445b835b1622cabf03e438ebc22ddec6b5877c3d099627bbb846a516ce15";
//    try {
      URL url = new URL("https://api.dnsdb.info/");
  //  } catch (MalformedURLException MalfURLExcep) {
    //  System.err.println(MalfURLExcep);
    //}
    HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
    Map<String, String> headers = new HashMap<>();
    headers.put("X-API-Key", API_Key);
    for (String headerKey : headers.keySet()) {
      conn.setRequestProperty(headerKey, headers.get(headerKey));
      conn.setRequestMethod("GET");
    }
    int responseCode = conn.getResponseCode();
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
    }
    else {
      System.out.println("invalid response code");
  }
}
}//main
  /*static void findApiKey() {
    scanFor(File="config.txt", var = "API_KEY",) -> return var.value: else {
      //add code to get the key from user and save it to the config file
    }

  }*/
//class
