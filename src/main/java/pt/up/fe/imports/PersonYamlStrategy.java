package pt.up.fe.imports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import pt.up.fe.person.Person;

public class PersonYamlStrategy extends YamlStrategy<Person> {
  @Override
  public List<Person> doImport(List<Map<String, Object>> data) {
    List<Person> list = new ArrayList<>();
    for (Map<String, Object> obj : data) {
    }
    return list;
  }
}
