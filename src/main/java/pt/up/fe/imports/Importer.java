package pt.up.fe.imports;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Importer<T> {
  private String path;
  private List<T> list = new ArrayList<>();

  public Importer(String path) {
    this.path = path;
  }

  public void doImport() {
    String path = this.appendExtension(this.path);
    FileReader fReader = this.readFromFile(path);
    this.list.addAll(this.buildInputList(fReader));
  }

  protected FileReader readFromFile(String path) {
    FileReader fReader = null;
    try {
      fReader = new FileReader(path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return fReader;
  }

  protected abstract String appendExtension(String path);

  protected abstract List<T> buildInputList(FileReader fReader);

  public List<T> getList() {
    return this.list;
  }
}
