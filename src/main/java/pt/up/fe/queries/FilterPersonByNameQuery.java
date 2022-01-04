package pt.up.fe.queries;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.json.JSONObject;

import pt.up.fe.person.Person;

public class FilterPersonByNameQuery implements QueryCommand{

    private QueryResultPersonList resultReceiver;
    private SpecifiedPersonAttributes specifiedFields;
    private List<Person> personList;

    private Predicate<Person> firstNameFilter;
    private Predicate<Person> middleNameFilter;
    private Predicate<Person> lastNameFilter;

    public FilterPersonByNameQuery(QueryResultPersonList receiver, SpecifiedPersonAttributes specifiedFields, List<Person> personList) {
        this.resultReceiver = receiver;
        this.specifiedFields = specifiedFields;
        this.personList = personList;

        this.firstNameFilter = this.createFirstNameFilter();
        this.middleNameFilter = this.createMiddleNameFilter();
        this.lastNameFilter = this.createLastNameFilter();
    }

    @Override
    public void setReceiver(QueryResultPersonList receiver) {
        this.resultReceiver = receiver;
    }

    @Override
    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public void execute() {

        this.resultReceiver.addAllPersons(
            this.personList.stream().filter(
                this.firstNameFilter.and(this.middleNameFilter.and(this.lastNameFilter))
            ).collect(Collectors.toList())
        );
    }

    private Predicate<Person> createFirstNameFilter() {
        return new Predicate<Person>() {

            @Override
            public boolean test(Person person) {
                if (specifiedFields.getName() == null || specifiedFields.getName().getName().isBlank())
                    return true;

                if (person.getName() != null) {
                    if (specifiedFields.getName().getExact()) return person.getName().equals(specifiedFields.getName().getName());
                    else return person.getName().toLowerCase().contains(specifiedFields.getName().getName().toLowerCase());
                }

                return false;
            }

        };
    }

    private Predicate<Person> createMiddleNameFilter() {
        return new Predicate<Person>() {

            @Override
            public boolean test(Person person) {
                if(specifiedFields.getMiddleName() == null || specifiedFields.getMiddleName().getName().isBlank())
                    return true;

                if (person.getMiddleName() != null) {
                    if (specifiedFields.getMiddleName().getExact()) return person.getMiddleName().equals(specifiedFields.getMiddleName().getName());
                    else return person.getMiddleName().toLowerCase().contains(specifiedFields.getMiddleName().getName().toLowerCase());
                }

                return false;
            }
        };
    }

    private Predicate<Person> createLastNameFilter() {
        return new Predicate<Person>() {

            @Override
            public boolean test(Person person) {
                if (specifiedFields.getLastName() == null || specifiedFields.getLastName().getName().isBlank())
                    return true;

                if (person.getLastName() != null) {
                    if (specifiedFields.getLastName().getExact()) return person.getLastName().equals(specifiedFields.getLastName().getName());
                    else return person.getLastName().toLowerCase().contains(specifiedFields.getLastName().getName().toLowerCase());
                }

                return false;
            }
        };
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