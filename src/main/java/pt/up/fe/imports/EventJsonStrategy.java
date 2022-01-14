package pt.up.fe.imports;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pt.up.fe.events.Event;

public class EventJsonStrategy extends JsonStrategy<Event> {
  @Override
  public List<Event> doImport(List<JSONObject> data) {
    List<Event> list = new ArrayList<>();
    for (JSONObject obj : data) {
      try {
        list.add(Event.importJSONObject(obj));
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    return list;
  }
}
