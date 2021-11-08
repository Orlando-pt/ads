package pt.up.fe.dates;

public class IntervalDate implements IDate{
    private SimpleDate startDate;
    private SimpleDate endDate;

    public IntervalDate(SimpleDate start, SimpleDate end) {
        this.startDate = start;
        this.endDate = end;
    }

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IntervalDate other = (IntervalDate) obj;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "IntervalDate{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }


}
