package pt.up.fe.facades;

import pt.up.fe.dates.IBuilder;
import pt.up.fe.dates.IDate;
import pt.up.fe.dates.IntervalDate;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.dates.SimpleDateBuilder;


public class DateFacade {
  public DateFacade() {}


  public IDate createSimpleDate(String year, String month, String day, String hour, String minute, String second) {
    IBuilder dateBuilder = new SimpleDateBuilder().reset();

    if(!year.isEmpty()) {
      dateBuilder.setYear(Integer.parseInt(year));
    }

    if(!month.isEmpty()) {
      dateBuilder.setMonth(Integer.parseInt(month));
    }

    if(!day.isEmpty()) {
      dateBuilder.setDay(Integer.parseInt(day));
    }

    if(!hour.isEmpty()) {
      dateBuilder.setHour(Integer.parseInt(hour));
    }

    if(!minute.isEmpty()) {
      dateBuilder.setMinute(Integer.parseInt(minute));
    }

    if(!second.isEmpty()) {
      dateBuilder.setSecond(Integer.parseInt(second));
    }

    return dateBuilder.build();
  }

  public IDate createIntervalDate(IDate date1, IDate date2) {
    return  new IntervalDate((SimpleDate) date1, (SimpleDate) date2);
  }
}
