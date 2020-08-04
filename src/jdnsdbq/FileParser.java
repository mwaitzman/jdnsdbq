package jdnsdbq;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Optional;
import jdnsdbq.jLog;
public class FileParser {
  public File theFile = null;
  jdnsdbq.jLog jlog = new jLog();
  public FileParser(File targetFile) {
    this.theFile = targetFile;
  }
  public Optional<String> getValue(String variable) throws FileNotFoundException{
    jlog.ls("beginning of of the getValue method");
    Scanner fS = new Scanner(this.theFile);
    jlog.ls("created Scanner");
    String data = fS.next();
    Optional<String> OptValue;
    while(fS.hasNext()) {
      jlog.ls("beginning of while loop");
      jlog.ls("data = \"" + data + "\"; variable = \""+ variable + "\".");
      if(variable.equals(data)) {
      data = fS.next();
      jlog.ls("API_Key variable found in configFile");
      return OptValue=(Optional.of(data));
      }
      data = fS.next();
    }
    return OptValue=(Optional.empty());
  }
}
