package pt.up.fe.imports;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import pt.up.fe.person.Person;

public class PersonJsonStrategy extends JsonStrategy<Person> {
  @Override
  public List<Person> doImport(List<JSONObject> data) {
    List<Person> list = new ArrayList<>();
    for (JSONObject obj : data) {
      list.add(Person.importJSONObject(obj));
    }
    return list;
  }
}
