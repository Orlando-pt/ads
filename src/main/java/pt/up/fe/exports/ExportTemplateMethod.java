package pt.up.fe.exports;

import java.util.ArrayList;
import java.util.List;
import pt.up.fe.events.Event;
import pt.up.fe.person.Person;

public abstract class ExportTemplateMethod {

  public List<Person> getPersonData() {
    //do something
    return new ArrayList<Person>();
  }

  public List<Event> getEventData() {
    //do something
    return new ArrayList<Event>();
  }

  public void openFile() {
    //do something
  }

  public void closeFile() {
    //do something
  }

  public abstract void writeToFile();

}
