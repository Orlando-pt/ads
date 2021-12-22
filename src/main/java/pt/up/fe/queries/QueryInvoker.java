package pt.up.fe.queries;

public class QueryInvoker {

    private QueryCommand command;

    public QueryInvoker() {}

    public void setCommand(QueryCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        this.command.execute();
    }
    
}
