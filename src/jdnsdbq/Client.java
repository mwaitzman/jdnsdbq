package jdnsdbq;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.InputStreamReader;
import jdnsdbq.FileParser;
import jdnsdbq.jLog;
import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.Reader;
//import jdnsdbq.utils.jLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;
import java.net.MalformedURLException;
//import java.net.HttpURLConnection;
class Client {
  static jLog jlog = new jLog();
  static String APIroot = "https://api.dnsdb.info/";
  static Scanner uin = new Scanner(System.in);
  static String API_key;
  static File configFile;
  public static void main(String[] args) {
    jlog.ls("Starting program (Client.java)");
    configFile = new File("config.txt");
    jlog.ls("created new File object (configFile)");
      doAPIkey();

    jlog.ls("end of program");
  }

  static void doAPIkey() {
    if (APIkeyFoundInFile(configFile) == true) {
      System.out.println("Successfully retrieved API key with value " + API_key + " from the config file.");
      try {
        if (testAPIKey(API_key) == true) {
          System.out.println("Your API key is valid!");
          return;
        }
        else {
          System.out.println("Your API key is invalid.");
        }
      }
      catch(IOException ex) {
        System.out.println(ex);
        //throw ex;
      }
    }
    else {
      //TODO change it so the program won't input the message below if the config file could not be found --maybe use a Boolean instead of boolean for the APIkeyFoundInFile method to accomplish this?
      System.out.println("Unfortunately, an API key could not be found in the config file.");
    }
      doManualAPIkeyInput();
  }

  //
  static boolean APIkeyFoundInFile(File configFile) {
    FileParser fp = new FileParser(configFile);
    try {
      Optional<String> OpAPI_Key = fp.getValue("API_Key");
      if(OpAPI_Key.isPresent()) {
        API_key = OpAPI_Key.get();
        return true;
      }
      else {
        return false;
      }
    }
    catch(FileNotFoundException e) {
      System.out.println("Could not find the config file.");
      //TODO add option to create the config  file now or specify the new location of the File
      return false;
    }
  }

  static void doManualAPIkeyInput() {
    System.out.println("Please enter your API key below:");
    Scanner sc = new Scanner(System.in);
    API_key = sc.nextLine();
    System.out.println("Testing API key...");
    try {
      if (testAPIKey(API_key) == true ) {
        System.out.println("Your API key is valid!");
        saveAPIkeytoConfigFile();
      }
      else {
        System.out.println("Your API key was invalid. Trying again...");
        doManualAPIkeyInput();
      }
    }
    catch(IOException ex) {
      System.out.println(ex);
      //throw ex;
    }
    finally {
      sc.close();
    }
  }



  static void saveAPIkeytoConfigFile() {
    //TODO
    System.out.println("saving API key to the config file...");
  }



  static boolean testAPIKey(String APIKey) throws IOException {
    jlog.ls("start of testAPIKey method");
    try {
          URL url = new URL(APIroot + "/lookup");
          HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
          jlog.ls("connection opened");
          conn.setRequestProperty("X-API-Key", APIKey);
          //String data = String.valueOf(conn.getErrorStream()) ;
      //    String data = (conn.getResponseCode() < 299) ? String.valueOf(conn.getResponseMessage()) : String.valueOf(conn.getResponseMessage()+ "eee" + conn.getResponseCode());
          //StringBuilder data;
        //  conn.getHeaderFields().forEach(header -> data += header);
        //  return data;
            //String fullResponse = getFullResponse(conn);//data;
            jlog.ls("end of testAPIKey method");
            return (conn.getResponseCode() != HttpsURLConnection.HTTP_FORBIDDEN);
          //return new String((HttpsURLConnection conn = (HttpsURLConnection)(new URL(APIroot))-> conn.openConnection() -> conn.getResponseCode() > 299) ? this.getResponseMessage() : this.getErrorStream()  );
          // what the return statement is supposed to do is create a new URL from the String APIroot, then create an HttpsURLConnection from that, then open the connection, get the connection's response code, check if it that code is over 299, getting its response message if it is, and getting its error stream if it is not, and then finally converting the response's value to a string and then returning that string.
    }
    catch(MalformedURLException ex) {
      System.out.println(ex);
      throw ex;
    }
    catch(IOException ex) {
      System.out.println(ex);
      throw ex;
    }
  }
  public static String getFullResponse(HttpsURLConnection con) throws IOException {//TODO: add Https unique responses (currently it only has the http responses)
        StringBuilder fullResponseBuilder = new StringBuilder();

        fullResponseBuilder.append(con.getResponseCode())
            .append(" ")
            .append(con.getResponseMessage())
            .append("\n");

        con.getHeaderFields()
            .entrySet()
            .stream()
            .filter(entry -> entry.getKey() != null)
            .forEach(entry -> {

                fullResponseBuilder.append(entry.getKey())
                    .append(": ");

                List<String> headerValues = entry.getValue();
                Iterator<String> it = headerValues.iterator();
                if (it.hasNext()) {
                    fullResponseBuilder.append(it.next());

                    while (it.hasNext()) {
                        fullResponseBuilder.append(", ")
                            .append(it.next());
                    }
                }

                fullResponseBuilder.append("\n");
            });

        Reader streamReader = null;

        if (con.getResponseCode() > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();

        fullResponseBuilder.append("Response: ")
            .append(content);

        return fullResponseBuilder.toString();
    }
}
