package pt.up.fe.queries;

import java.util.ArrayList;
import java.util.List;

import pt.up.fe.person.Person;

public class QueryResultPersonList {
    
    private List<Person> personList;

    public QueryResultPersonList() {
        this.personList = new ArrayList<>();
    }

    public boolean addPerson(Person person) {
        return this.personList.add(person);
    }

    public boolean addAllPersons(List<Person> persons) {
        return this.personList.addAll(persons);
    }

    public List<Person> getPersonList() {
        return this.personList;
    }

    public void emptyList() {
        this.personList.clear();
    }
}
