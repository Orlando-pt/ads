package pt.up.fe.imports;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonImporter<T extends Object> extends Importer<T> {
  public JsonImporter(String path) {
    super(path);
  }

  @Override
  protected String appendExtension(String path) {
    if (path.endsWith(".json") == false) {
      path += ".json";
    }
    return path;
  }

  @Override
  protected List<T> buildInputList(FileReader fReader) {
    JSONTokener token = new JSONTokener(fReader);
    try {
      fReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    JSONArray arr = new JSONArray(token);
    List<T> list = new ArrayList<>();
    for (Object obj : arr) {
      JSONObject jObj = (JSONObject) obj;
    }
    return list;
  }
}
