package pt.up.fe.queries;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import pt.up.fe.Main;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.IntervalDate;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.exports.JsonExporter;
import pt.up.fe.person.Person;

public class QueryMementoCaretaker {

  private LinkedList<QueryMemento> commandHistory;
  private QueryInvoker queryInvoker;
  private String pathExportFile;

  public QueryMementoCaretaker() {
    this.commandHistory = new LinkedList<>();
    this.pathExportFile = "queries/query_history.json";
  }

  public void setOriginator(QueryInvoker originator) {
    this.queryInvoker = originator;
  }

  public void notifySave() {
    this.commandHistory.addFirst(
        queryInvoker.save()
    );
  }

  public void restoreCommand(int position) {
    queryInvoker.restore(
        this.commandHistory.get(position)
    );
  }

  public List<QueryMemento> getCommandsHistory() {
    return this.commandHistory;
  }

  public void importCommands() {
    FileReader fReader = null;
    try {
      fReader = new FileReader(pathExportFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    JSONTokener token = new JSONTokener(fReader);
    JSONArray arr = new JSONArray(token);

    for (Object obj : arr) {
      JSONObject object = (JSONObject) obj;
      this.commandHistory.add(
          this.commandCreator(object)
      );
    }

    try {
      fReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public QueryMemento commandCreator(JSONObject jsonObject) {
    switch (jsonObject.getString("query_command")) {
      case "pt.up.fe.queries.FilterPersonByNameQuery":
        return new QueryMemento(createNameFilter(jsonObject));
      case "pt.up.fe.queries.FilterPersonByBirthQuery":
        return new QueryMemento(createBirthQuery(jsonObject));
      case "pt.up.fe.queries.ChildrenQuery":
        return new QueryMemento(createChildrenQuery(jsonObject));
      case "pt.up.fe.queries.GrandChildrenQuery":
        return new QueryMemento(createGrandChildrenQuery(jsonObject));
      case "pt.up.fe.queries.GrandGrandChildrenQuery":
        return new QueryMemento(createGrandGrandChildrenQuery(jsonObject));
      default:
        System.err.println("Unrecognized query: " + jsonObject.getString("query_command"));
        break;
    }

    return null;
  }

  private ChildrenQuery createChildrenQuery(JSONObject jsonObject) {
    return new ChildrenQuery(
        null,
        this.getPersonFromMainPeopleList(
            jsonObject.getJSONObject("person").getString("uuid")
        )
    );
  }

  private GrandChildrenQuery createGrandChildrenQuery(JSONObject jsonObject) {
    return new GrandChildrenQuery(
        null,
        this.getPersonFromMainPeopleList(
            jsonObject.getJSONObject("person").getString("uuid")
        )
    );
  }

  private GrandGrandChildrenQuery createGrandGrandChildrenQuery(JSONObject jsonObject) {
    return new GrandGrandChildrenQuery(
        null,
        this.getPersonFromMainPeopleList(
            jsonObject.getJSONObject("person").getString("uuid")
        )
    );
  }

  private Person getPersonFromMainPeopleList(String uuid) {
    List<Person> persons = Main.peopleList.stream().filter(
        person -> person.getId().toString().equals(uuid)
    ).collect(Collectors.toList());

    return persons.size() == 1 ? persons.get(0) : null;
  }

  private FilterPersonByNameQuery createNameFilter(JSONObject jsonObject) {
    SpecifiedPersonAttributes attributes = new SpecifiedPersonAttributes();
    JSONObject nameObject;

    nameObject = this.getPossibleNull(jsonObject.getJSONObject("name_fields"), "name");
    if (!jsonObject.isNull("name")) {
          attributes.setName(
              new NameAttribute(nameObject.getString("name"), nameObject.getBoolean("exact")));
      }

    nameObject = this.getPossibleNull(jsonObject.getJSONObject("name_fields"), "middle_name");
    if (!jsonObject.isNull("middle_name")) {
          attributes.setMiddleName(
              new NameAttribute(nameObject.getString("middle_name"),
                  nameObject.getBoolean("exact")));
      }

    nameObject = this.getPossibleNull(jsonObject.getJSONObject("name_fields"), "last_name");
    if (!jsonObject.isNull("last_name")) {
          attributes.setLastName(
              new NameAttribute(nameObject.getString("last_name"), nameObject.getBoolean("exact")));
      }

    return new FilterPersonByNameQuery(null, attributes, Main.peopleList);
  }

  private FilterPersonByBirthQuery createBirthQuery(JSONObject jsonObject) {
    JSONObject dateObject;
    IDate date;

    dateObject = this.getPossibleNull(jsonObject.getJSONObject("date").getJSONObject("date"),
        "startDate");

    if (dateObject == null) {
      date = this.getSimpleDateFromJson(jsonObject.getJSONObject("date").getJSONObject("date"));
    } else {
      SimpleDate startDate = this.getSimpleDateFromJson(dateObject);
      SimpleDate endDate = this.getSimpleDateFromJson(
          jsonObject.getJSONObject("date").getJSONObject("date").getJSONObject("endDate")
      );

      date = new IntervalDate(startDate, endDate);
    }

    return new FilterPersonByBirthQuery(null, new DateAttribute(date, this.getDateQueryType(
        jsonObject.getJSONObject("date").getString("query_type")
    )), Main.peopleList);
  }

  private DateQueryTypeEnum getDateQueryType(String type) {
    switch (type) {
      case "CONTAINS":
        return DateQueryTypeEnum.CONTAINS;
      case "AFTER":
        return DateQueryTypeEnum.AFTER;
      case "BEFORE":
        return DateQueryTypeEnum.BEFORE;

      default:
        System.err.println("Unknown type: " + type);
        return null;
    }
  }

  private SimpleDate getSimpleDateFromJson(JSONObject jsonObject) {
    SimpleDate date = new SimpleDate();
    int currentField;

    currentField = this.getPossibleNullInt(jsonObject, "year");
      if (currentField != -1) {
          date.setYear(currentField);
      }

    currentField = this.getPossibleNullInt(jsonObject, "month");
      if (currentField != -1) {
          date.setMonth(currentField);
      }

    currentField = this.getPossibleNullInt(jsonObject, "day");
      if (currentField != -1) {
          date.setDay(currentField);
      }

    return date;
  }

  private int getPossibleNullInt(JSONObject object, String key) {
    try {
      return object.getInt(key);
    } catch (Exception e) {
      return -1;
    }
  }

  private JSONObject getPossibleNull(JSONObject key, String fieldName) {
    try {
      return key.getJSONObject(fieldName);
    } catch (Exception e) {
      return null;
    }
  }

  public void exportCommands() {
    JsonExporter<QueryMemento> jsonExporter = new JsonExporter<QueryMemento>(pathExportFile);
    jsonExporter.setObject(this.commandHistory.iterator());
    jsonExporter.export();
  }

}
