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
//import java.net.HttpURLConnection;
class Client {
  static jLog jlog = new jLog();
  static String APIroot = "https://api.dnsdb.info/";
  public static void main(String[] args) {
    //jlog.ls("Starting program...");
    jlog.ls("Starting program (Client.java)");
    //
    File configFile = new File("config.txt");

    jlog.ls("created new File object (configFile)");
    try {
                FileParser fp = new FileParser(configFile);
      Optional<String> OpAPI_Key = fp.getValue("API_Key");
      //fp.close();
      if(OpAPI_Key.isPresent()) {
        String API_Key = OpAPI_Key.get();
        System.out.println("Your API Key is " + API_Key);
        try {
          String r = (testAPIKey(API_Key)) ? "api key validated successfully" : "invalid api key";
          jlog.ls(r);
          r = null;
        }
        catch(Exception e) {
          System.out.println(":( " + e);
        }
      }
      else {
        System.out.println("API Key was not found.");
        //TODO ask user if they want to enter their API key here manually
      }
    }
    catch(FileNotFoundException e) {
      System.out.println(e);
    }
    jlog.ls("end of program");
  }

  static boolean testAPIKey(String APIKey) throws Exception {
    jlog.ls("start of testAPIKey method");
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
