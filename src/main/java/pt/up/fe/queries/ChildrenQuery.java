package pt.up.fe.queries;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import pt.up.fe.person.Person;

public class ChildrenQuery implements QueryCommand{

    private QueryResultPersonList resultReceiver;
    private Person root;

    public ChildrenQuery(QueryResultPersonList receiver, Person root) {
        this.resultReceiver = receiver;
        this.root = root;
    }

    public void execute() {
        resultReceiver.addAllPersons(
            root.getChildren()
        );
    }

    @Override
    public void setReceiver(QueryResultPersonList receiver) {
        this.resultReceiver = receiver;
        
    }

    @Override
    public void setPersonList(List<Person> personList) {
        
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
