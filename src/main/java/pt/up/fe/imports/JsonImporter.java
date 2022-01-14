package pt.up.fe.imports;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.up.fe.exports.IExportObject;

public class JsonImporter<T extends IExportObject> extends Importer<T, JSONObject> {

  public JsonImporter(JsonStrategy<T> importStrategy, String path) {
    super(importStrategy, path);
  }

  @Override
  protected String appendExtension(String path) {
    if (path.endsWith(".json") == false) {
      path += ".json";
    }
    return path;
  }

  @Override
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
