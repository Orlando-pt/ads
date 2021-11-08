package pt.up.fe.dates;

public class IntervalDateBuilder implements IBuilder {
    private SimpleDate startDate;
    private SimpleDate endDate;

    private SimpleDate activeDate;

    @Override
    public IBuilder reset() {
        this.startDate = new SimpleDate();
        this.endDate = new SimpleDate();
        this.activeDate = this.startDate;
        return this;
    }

    @Override
    public IBuilder switchDate() {
        if (this.activeDate.equals(startDate))
            this.activeDate = this.endDate;
        else
            this.activeDate = this.startDate;

        return this;
    }

    @Override
    public IBuilder setYear(int year) {
        this.activeDate.setYear(year);
        return this;
    }

    @Override
    public IBuilder setMonth(int month) {
        this.activeDate.setMonth(month);
        return this;
    }

    @Override
    public IBuilder setDay(int day) {
        this.activeDate.setDay(day);
        return this;
    }

    @Override
    public IBuilder setHour(int hour) {
        this.activeDate.setHour(hour);
        return this;
    }

    @Override
    public IBuilder setMinute(int minute) {
        this.activeDate.setMinute(minute);
        return this;
    }

    @Override
    public IBuilder setSecond(int second) {
        this.activeDate.setSecond(second);
        return this;
    }

}
