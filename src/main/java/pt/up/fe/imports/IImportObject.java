package pt.up.fe.imports;

import org.json.JSONObject;

public interface IImportObject<T> {
  T importJSONObject(JSONObject obj);
}
