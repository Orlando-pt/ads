package pt.up.fe;

import java.util.Map;
import org.json.JSONObject;

public interface IExportObject {
  Map<String, Object> toYAMLObject();

  JSONObject toJSONObject();
}
