package pt.up.fe.queries;

public class QueryInvoker {

    private QueryCommand command;
    private QueryMementoCaretaker queryCaretaker;

    public QueryInvoker(
        QueryMementoCaretaker caretaker
    ) {
        this.queryCaretaker = caretaker;
    }

    public void setCommand(QueryCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        // save command into memento caretaker
        this.queryCaretaker.notifySave();

        this.command.execute();
    }
    
    public QueryMemento save() {
        return new QueryMemento(this.command);
    }

    public void restore(QueryMemento memento) {
        this.setCommand(memento.getQuery());
    }

    public QueryCommand getCurrentCommand() {
        return this.command;
    }

}
