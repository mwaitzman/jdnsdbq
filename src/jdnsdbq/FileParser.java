package jdnsdbq;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Optional;
public class FileParser {
  public File theFile = null;
  public FileParser(File targetFile) {
    this.theFile = targetFile;
  }
  public Optional<String> getValue(String variable) throws FileNotFoundException{
    System.out.println("rgjir9tugji");
    Scanner fS = new Scanner(this.theFile);
    System.out.println("aaa");
    String data = fS.next();
    Optional<String> OptValue;
    while(fS.hasNext()) {
      System.out.println("84utj");
      System.out.println(data + "0" + variable + "1");
      if(variable.equals(data)) {
      data = fS.next();
      System.out.println("API_Key variable found in configFile");
      return OptValue=(Optional.of(data));
      }
      data = fS.next();
    }
    return OptValue=(Optional.empty());
  }
}
