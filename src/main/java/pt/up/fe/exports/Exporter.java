package pt.up.fe.exports;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public abstract class Exporter<T> {
  private String path;
  private Iterator<T> object;

  public Exporter(String path) {
    this.path = path;
  }

  public void export() {
    String contents = this.buildOutputString(this.object);
    String path = this.appendExtension(this.path);
    this.writeToFile(path, contents);
  }

  protected void writeToFile(String path, String str) {
    try (FileWriter file = new FileWriter(path)) {
      file.write(str);
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected abstract String appendExtension(String path);

  protected abstract String buildOutputString(Iterator<T> iterator);

  public Exporter<T> setObject(Iterator<T> object) {
    this.object = object;
    return this;
  }
}
