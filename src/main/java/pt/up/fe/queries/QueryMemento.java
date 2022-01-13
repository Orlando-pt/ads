package pt.up.fe.queries;

import java.util.Map;

import org.json.JSONObject;

import pt.up.fe.exports.IExportObject;

public class QueryMemento implements IExportObject {
    
    private QueryCommand query;

    public QueryMemento(QueryCommand query) {
        this.query = query;
    }

    public QueryCommand getQuery() {
        return this.query;
    }

    @Override
    public Map<String, Object> toYAMLObject() {
        return null;
    }

    @Override
    public JSONObject toJSONObject() {
        return this.query.toJSONObject();
    }
}
