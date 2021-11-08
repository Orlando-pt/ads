package pt.up.fe.dates;

public interface IBuilder {
    IBuilder reset();

    IBuilder switchDate();

    IBuilder setYear(int year);

    IBuilder setMonth(int month);

    IBuilder setDay(int day);

    IBuilder setHour(int hour);

    IBuilder setMinute(int minute);

    IBuilder setSecond(int second);
}
