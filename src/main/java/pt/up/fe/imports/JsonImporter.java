package pt.up.fe.imports;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.up.fe.exports.IExportObject;

public class JsonImporter<T extends IExportObject> {
  private JsonStrategy<T> importStrategy;
  private String path;

  public JsonImporter(JsonStrategy<T> importStrategy, String path) {
    this.importStrategy = importStrategy;
    this.path = path;
  }

  public List<T> doImport() {
    String path = this.appendExtension(this.path);
    FileReader fReader = this.readFromFile(path);
    List<JSONObject> data = this.buildInputList(fReader);
    return this.importStrategy.doImport(data);
  }

  protected String appendExtension(String path) {
    if (path.endsWith(".json") == false) {
      path += ".json";
    }
    return path;
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

  protected List<JSONObject> buildInputList(FileReader fReader) {
    JSONTokener token = new JSONTokener(fReader);
    JSONArray arr = new JSONArray(token);
    List<JSONObject> data = new ArrayList<>();
    for (Object obj : arr) {
      data.add((JSONObject) obj);
    }
    try {
      fReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }
}
