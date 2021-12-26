package pt.up.fe.queries;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

import pt.up.fe.person.Person;

public class FilterPersonByNameQuery implements QueryCommand{
    
    private QueryResultPersonList resultReceiver;
    private SpecifiedPersonAttributes specifiedFields;
    private List<Person> personList;

    public FilterPersonByNameQuery(QueryResultPersonList receiver, SpecifiedPersonAttributes specifiedFields, List<Person> personList) {
        this.resultReceiver = receiver;
        this.specifiedFields = specifiedFields;
        this.personList = personList;
    }

    @Override
    public void setReceiver(QueryResultPersonList receiver) {
        this.resultReceiver = receiver;
    }

    @Override
    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
    public void execute() {
        List<Person> tempPersonsList = this.personList;

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

    @Override
    public Map<String, Object> toYAMLObject() {
        return null;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("query_command", this.getClass().getName());
        json.put(
            "name_fields",
            new JSONObject()
                .put(
                    "name", this.specifiedFields.getName() == null ? JSONObject.NULL : new JSONObject()
                        .put("name", this.specifiedFields.getName().getName())
                        .put("exact", this.specifiedFields.getName().getExact())
                )
                .put(
                    "middle_name", this.specifiedFields.getMiddleName() == null ? JSONObject.NULL : new JSONObject()
                        .put("name", this.specifiedFields.getMiddleName().getName())
                        .put("exact", this.specifiedFields.getMiddleName().getExact())
                )
                .put(
                    "last_name", this.specifiedFields.getLastName() == null ? JSONObject.NULL : new JSONObject()
                        .put("name", this.specifiedFields.getLastName().getName())
                        .put("exact", this.specifiedFields.getLastName().getExact())
                )
        );
        return json;
    };
}
