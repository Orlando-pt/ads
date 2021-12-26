package pt.up.fe.queries;

public class QueryMemento {
    
    private QueryCommand query;

    public QueryMemento(QueryCommand query) {
        this.query = query;
    }

    public QueryCommand getQuery() {
        return this.query;
    }
}
