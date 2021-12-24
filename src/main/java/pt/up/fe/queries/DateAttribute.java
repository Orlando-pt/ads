package pt.up.fe.queries;

import pt.up.fe.dates.IDate;

public class DateAttribute{

    private IDate date;
    private DateQueryTypeEnum dateQueryType;

    public DateAttribute(
        IDate date,
        DateQueryTypeEnum dateQueryType
    ) {
        this.date = date;
        this.dateQueryType = dateQueryType;
    }

    public IDate getDate() {
        return this.date;
    }
    
    public DateQueryTypeEnum getDateQueryType() {
        return this.dateQueryType;
    }
}
