package pt.up.fe.exports;

import java.util.Map;
import org.json.JSONObject;

public interface IExportObject {
  Map<String, Object> toYAMLObject();

  JSONObject toJSONObject();
}
