package pt.up.fe.queries;

import java.util.List;

import pt.up.fe.exports.IExportObject;
import pt.up.fe.person.Person;

public interface QueryCommand extends IExportObject{
    
    void execute();
    void setReceiver(QueryResultPersonList receiver);
    void setPersonList(List<Person> personList);
}
