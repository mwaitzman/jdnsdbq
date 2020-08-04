package jdnsdbq;
import jdnsdbq.FileParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
class Client {
  public static void main(String[] args) {
    System.out.println("Starting program...");
    //
    File configFile = new File("config.txt");

    System.out.println("84gr9t58hitjop");
    try {
                FileParser fp = new FileParser(configFile);
      Optional<String> OpAPI_Key = fp.getValue("API_Key");
      //fp.close();
      if(OpAPI_Key.isPresent()) {
        String API_Key = OpAPI_Key.get();
        System.out.println("Your API Key is " + API_Key);
      }
      else {
        System.out.println("API Key was not found.");
      }
    }
    catch(FileNotFoundException e) {
      System.out.println(e);
    }
  }

}
