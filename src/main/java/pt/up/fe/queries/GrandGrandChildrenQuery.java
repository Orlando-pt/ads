package pt.up.fe.queries;

import pt.up.fe.person.Person;

public class GrandGrandChildrenQuery implements QueryCommand{

    private QueryResultPersonList resultReceiver;
    private Person root;

    public GrandGrandChildrenQuery(QueryResultPersonList receiver, Person root) {
        this.resultReceiver = receiver;
        this.root = root;
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
    
}
