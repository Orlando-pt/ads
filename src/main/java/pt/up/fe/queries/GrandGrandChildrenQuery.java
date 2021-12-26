package pt.up.fe.queries;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import pt.up.fe.person.Person;

public class GrandGrandChildrenQuery implements QueryCommand{

    private QueryResultPersonList resultReceiver;
    private Person root;

    public GrandGrandChildrenQuery(QueryResultPersonList receiver, Person root) {
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
            (children) -> children.getChildren().forEach(
                (grandChildren) -> this.resultReceiver.addAllPersons(
                    grandChildren.getChildren()
                )
            )
        );
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
