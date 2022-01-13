package pt.up.fe.imports;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pt.up.fe.sources.Source;

public class SourceJsonStrategy extends JsonStrategy<Source> {
  @Override
  public List<Source> doImport(List<JSONObject> data) {
    List<Source> list = new ArrayList<>();
    for (JSONObject obj : data) {
      try {
        list.add(Source.importJSONObject(obj));
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    return list;
  }
}
