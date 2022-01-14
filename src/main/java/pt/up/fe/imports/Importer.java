package pt.up.fe.imports;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public abstract class Importer<T, G> {
  private Strategy<T, G> strategy;
  private String path;

  public Importer(Strategy<T, G> strategy, String path) {
    this.strategy = strategy;
    this.path = path;
  }

  public List<T> doImport() {
    String path = this.appendExtension(this.path);
    FileReader fReader = this.readFromFile(path);
    List<G> data = this.buildInputList(fReader);
    return this.strategy.doImport(data);
  }

  private FileReader readFromFile(String path) {
    FileReader fReader = null;
    try {
      fReader = new FileReader(path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return fReader;
  }

  protected abstract String appendExtension(String path);

  protected abstract List<G> buildInputList(FileReader fReader);
}
