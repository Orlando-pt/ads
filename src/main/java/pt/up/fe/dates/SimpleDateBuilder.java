package pt.up.fe.dates;

public class SimpleDateBuilder implements IBuilder {
    private SimpleDate date;

    @Override
    public IBuilder reset() {
        this.date = new SimpleDate();
        return this;
    }

    @Override
    public IBuilder switchDate() {
        return this;
    }

    @Override
    public IBuilder setYear(int year) {
        this.date.setYear(year);
        return this;
    }

    @Override
    public IBuilder setMonth(int month) {
        this.date.setMonth(month);
        return this;
    }

    @Override
    public IBuilder setDay(int day) {
        this.date.setDay(day);
        return this;
    }

    @Override
    public IBuilder setHour(int hour) {
        this.date.setHour(hour);
        return this;
    }

    @Override
    public IBuilder setMinute(int minute) {
        this.date.setMinute(minute);
        return this;
    }

    @Override
    public IBuilder setSecond(int second) {
        this.date.setSecond(second);
        return this;
    }
    
    @Override
    public IDate build() {
        return this.date;
    }   
}
