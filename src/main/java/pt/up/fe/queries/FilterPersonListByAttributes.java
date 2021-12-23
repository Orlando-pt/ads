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
        List<Person> tempPersonsList = personList;

        // filter persons by name attribute
        if (this.specifiedFields.getName() != null)
            tempPersonsList = this.filterByFirstName(tempPersonsList, this.specifiedFields.getName());

        if (this.specifiedFields.getMiddleName() != null)
            tempPersonsList = this.filterByMiddleName(tempPersonsList, this.specifiedFields.getMiddleName());

        if (this.specifiedFields.getLastName() != null)
            tempPersonsList = this.filterByLastName(tempPersonsList, this.specifiedFields.getLastName());

        this.resultReceiver.addAllPersons(tempPersonsList);
    }

    private List<Person> filterByFirstName(
        List<Person> currentPersonList,
        NameAttribute nameAttr
    ) {
        return currentPersonList.stream().filter(
            person -> {
                if (person.getName() != null) {
                    if (nameAttr.getExact()) return person.getName().equals(nameAttr.getName());
                    else return person.getName().contains(nameAttr.getName());
                } else {
                    return false;
                }
            }
        ).collect(Collectors.toList());
    }

    private List<Person> filterByMiddleName(
        List<Person> currentPersonList,
        NameAttribute nameAttr
    ) {
        return currentPersonList.stream().filter(
            person -> {
                if (person.getMiddleName() != null) {
                    if (nameAttr.getExact()) return person.getMiddleName().equals(nameAttr.getName());
                    else return person.getMiddleName().contains(nameAttr.getName());
                } else {
                    return false;
                }
            }
        ).collect(Collectors.toList());
    }

    private List<Person> filterByLastName(
        List<Person> currentPersonList,
        NameAttribute nameAttr
    ) {
        return currentPersonList.stream().filter(
            person -> {
                if (person.getLastName() != null) {
                    if (nameAttr.getExact()) return person.getLastName().equals(nameAttr.getName());
                    else return person.getLastName().contains(nameAttr.getName());
                } else {
                    return false;
                }
            }
        ).collect(Collectors.toList());
    }
}
