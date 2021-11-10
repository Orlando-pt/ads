package pt.up.fe.exports;

import pt.up.fe.person.Person;
import pt.up.fe.events.Event;

import java.util.ArrayList;
import java.util.List;

public abstract class ExportTemplateMethod {

    public List<Person> getPersonData(){
        //do something
        return new ArrayList<Person>();
    }

    public List<Event> getEventData(){
        //do something
        return new ArrayList<Event>();
    }

    public abstract void openFile();

    public abstract void closeFile();
}
