package pt.up.fe.queries;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import pt.up.fe.person.Person;

public class GrandChildrenQuery implements QueryCommand{

    private QueryResultPersonList resultReceiver;
    private Person root;

    public GrandChildrenQuery(QueryResultPersonList receiver, Person root) {
        this.resultReceiver = receiver;
        this.root = root;
    }

    @Override
    public void setReceiver(QueryResultPersonList receiver) {
        this.resultReceiver = receiver;
    }

    @Override
    public void setPersonList(List<Person> personList) {
        
    }
    public void execute() {

        this.root.getChildren().forEach(
            (children) -> this.resultReceiver.addAllPersons(
                children.getChildren()
            )
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Searched by GrandChildren of Person [");

        if (this.root.getName() != null && !this.root.getName().isEmpty())
            sb.append("First Name = " + this.root.getName() + ", ");

        if (this.root.getMiddleName() != null && !this.root.getMiddleName().isEmpty())
            sb.append("Middle Name = " + this.root.getMiddleName() + ", ");

        if (this.root.getLastName() != null && !this.root.getLastName().isEmpty())
            sb.append("Last Name = " + this.root.getLastName() + ", ");

        return sb.substring(0, sb.length()  - 2) + "]";
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
            "person",
            new JSONObject()
                .put("uuid", this.root.getId())
                .put("name", this.root.getName())
        );
        return json;
    };
}
