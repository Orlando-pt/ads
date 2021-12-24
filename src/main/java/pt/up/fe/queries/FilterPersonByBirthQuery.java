package pt.up.fe.queries;

import java.util.List;
import java.util.stream.Collectors;

import pt.up.fe.person.Person;

public class FilterPersonByBirthQuery implements QueryCommand{

    private QueryResultPersonList resultReceiver;
    private DateAttribute date;
    private List<Person> personList;

    public FilterPersonByBirthQuery(
        QueryResultPersonList receiver,
        DateAttribute date,
        List<Person> personList
    ) {
        this.resultReceiver = receiver;
        this.date = date;
        this.personList = personList;
    }

    @Override
    public void execute() {
        this.resultReceiver.addAllPersons(
            this.personList.stream().filter(
            person -> {
                if (person.getBirth() == null)
                    return false;

                if (date.getDateQueryType() == DateQueryTypeEnum.EXACT)
                    return person.getBirth().getDate().equals(date.getDate());
                else if (date.getDateQueryType() == DateQueryTypeEnum.BEFORE)
                    return person.getBirth().getDate().compareTo(date.getDate()) == -1;
                else if (date.getDateQueryType() == DateQueryTypeEnum.AFTER)
                    return person.getBirth().getDate().compareTo(date.getDate()) == 1;
                else if (date.getDateQueryType() == DateQueryTypeEnum.CONTAINS)
                    return person.getBirth().getDate().compareTo(date.getDate()) == -10;
                
                return false;
            }
        ).collect(Collectors.toList()));
    }
    
}
