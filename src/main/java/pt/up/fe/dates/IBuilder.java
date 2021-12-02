package pt.up.fe.dates;

public interface IBuilder {

  IBuilder reset();

  IBuilder switchDate();

  IBuilder setYear(Integer year);

  IBuilder setMonth(Integer month);

  IBuilder setDay(Integer day);

  IBuilder setHour(Integer hour);

  IBuilder setMinute(Integer minute);

  IBuilder setSecond(Integer second);

  IDate build();
}
