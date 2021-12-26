package pt.up.fe.queries;

import java.util.LinkedList;
import java.util.List;

public class QueryMementoCaretaker {

    private LinkedList<QueryMemento> commandHistory;
    private QueryInvoker queryInvoker;

    public QueryMementoCaretaker() {
        this.commandHistory = new LinkedList<>();
    }

    public void setOriginator(QueryInvoker originator) {
        this.queryInvoker = originator;
    }

    public void notifySave() {
        this.commandHistory.addFirst(
            queryInvoker.save()
        );
    }

    public void restoreCommand(int position) {
        queryInvoker.restore(
            this.commandHistory.get(position)
        );
    }

    public List<QueryMemento> getCommandsHistory() {
        // TODO return a list
        return this.commandHistory;
    }
    
}
