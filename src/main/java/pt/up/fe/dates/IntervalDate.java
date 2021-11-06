package pt.up.fe.dates;

public class IntervalDate implements IDate{
    private SimpleDate startDate;
    private SimpleDate endDate;

    public SimpleDate getStartDate() {
        return startDate;
    }

    public void setStartDate(SimpleDate startDate) {
        this.startDate = startDate;
    }

    public SimpleDate getEndDate() {
        return endDate;
    }

    public void setEndDate(SimpleDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "IntervalDate{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
