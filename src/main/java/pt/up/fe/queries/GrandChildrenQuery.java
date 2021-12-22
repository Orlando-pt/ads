package pt.up.fe.queries;

import pt.up.fe.person.Person;

public class GrandChildrenQuery implements QueryCommand{

    private QueryResultPersonList resultReceiver;
    private Person root;

    public GrandChildrenQuery(QueryResultPersonList receiver, Person root) {
        this.resultReceiver = receiver;
        this.root = root;
    }

    public void execute() {

        this.root.getChildren().forEach(
            (children) -> this.resultReceiver.addAllPersons(
                children.getChildren()
            )
        );
    }
    
}
