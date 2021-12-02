package pt.up.fe.exports;

import java.util.Iterator;
import org.json.JSONArray;

public class JsonExporter<T extends IExportObject> extends Exporter<T> {
  public JsonExporter(String path) {
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
  protected String buildOutputString(Iterator<T> iterator) {
    JSONArray arr = new JSONArray();
    while (iterator.hasNext()) {
      T c = iterator.next();
      arr.put(c.toJSONObject());
    }
    return arr.toString();
  }
}
