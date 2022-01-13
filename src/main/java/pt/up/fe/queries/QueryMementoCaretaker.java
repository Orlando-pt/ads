package pt.up.fe.queries;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import pt.up.fe.exports.JsonExporter;

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

    public void exportCommands() {
        JsonExporter<QueryMemento> jsonExporter = new JsonExporter<QueryMemento>("queries/query_history.json");
        String res = jsonExporter.buildOutputString(this.commandHistory.iterator());

        JSONArray resLoaded = new JSONArray(res);
        
        // TODO remove this experiment
        System.out.println(resLoaded.getJSONObject(0));

        try {
            Class<?> command = Class.forName(resLoaded.getJSONObject(0).getString("query_command"));
            Constructor<?> commandConstructor = command.getConstructor(
                QueryResultPersonList.class,
                SpecifiedPersonAttributes.class,
                List.class
            );
            SpecifiedPersonAttributes specifiedPersonAttributes = new SpecifiedPersonAttributes();
            specifiedPersonAttributes.setLastName(
                new NameAttribute("Orlando", true)
            );
            FilterPersonByNameQuery commandObject = (FilterPersonByNameQuery) commandConstructor.newInstance(null, specifiedPersonAttributes, null);
            
            System.out.println(commandObject.toJSONObject());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
