package pt.up.fe.exports;

import pt.up.fe.person.Person;
import pt.up.fe.events.Event;

import java.util.List;

public class Export {
    private ExportTemplateMethod strategy;

    public void exportPersonData(){
        //step1...
        List<Person> people = strategy.getPersonData();
        //rest of steps...
    }

    public void exportEventData(){
        //step1...
        List<Event> events = strategy.getEventData();
        //rest of steps...
    }

}
