package pt.up.fe.queries;

import java.util.List;

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
        
    };
    
}
