package pt.up.fe.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pt.up.fe.person.Person;

public class FilterPersonListByAttributes implements QueryCommand{
    
    private QueryResultPersonList resultReceiver;
    private SpecifiedPersonAttributes specifiedFields;
    private List<Person> personList;

    public FilterPersonListByAttributes(QueryResultPersonList receiver, SpecifiedPersonAttributes specifiedFields, List<Person> personList) {
        this.resultReceiver = receiver;
        this.specifiedFields = specifiedFields;
        this.personList = personList;
    }

    public void execute() {
        List<Person> tempPersonsList = new ArrayList<>();

        // filter persons by name attribute
        NameAttribute nameAttr = this.specifiedFields.getName();
        tempPersonsList = this.personList.stream()
        .filter(
            person -> {
                // if the user wants to search for one exact name
                if (nameAttr.getExact()) return person.getName().equals(nameAttr.getName());
                else return person.getName().contains(nameAttr.getName());
            }
        ).collect(Collectors.toList());


        this.resultReceiver.addAllPersons(tempPersonsList);
    }
}
