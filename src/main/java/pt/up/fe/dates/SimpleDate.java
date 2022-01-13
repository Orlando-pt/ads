package pt.up.fe.dates;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SimpleDate implements IDate {

  private Integer year;
  private Integer month;
  private Integer day;
  private Integer hour;
  private Integer minute;
  private Integer second;

  public SimpleDate() {
  }

  public SimpleDate(int year, int month, int day) {
    this.year = Integer.valueOf(year);
    this.month = Integer.valueOf(month);
    this.day = Integer.valueOf(day);
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  public Integer getHour() {
    return hour;
  }

  public void setHour(Integer hour) {
    this.hour = hour;
  }

  public Integer getMinute() {
    return minute;
  }

  public void setMinute(Integer minute) {
    this.minute = minute;
  }

  public Integer getSecond() {
    return second;
  }

  public void setSecond(Integer second) {
    this.second = second;
  }

  @Override
  public String toString() {
    StringBuilder sBuilder = new StringBuilder();

    sBuilder.append(this.checkDateOrDay(year, true) + "/");
    sBuilder.append(this.checkDateOrDay(month, false) + "/");
    sBuilder.append(this.checkDateOrDay(day, false));

    return sBuilder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((day == null) ? 0 : day.hashCode());
    result = prime * result + ((hour == null) ? 0 : hour.hashCode());
    result = prime * result + ((minute == null) ? 0 : minute.hashCode());
    result = prime * result + ((month == null) ? 0 : month.hashCode());
    result = prime * result + ((second == null) ? 0 : second.hashCode());
    result = prime * result + ((year == null) ? 0 : year.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    SimpleDate other = (SimpleDate) obj;
    if (day == null) {
      if (other.day != null) {
        return false;
      }
    } else if (!day.equals(other.day)) {
      return false;
    }
    if (hour == null) {
      if (other.hour != null) {
        return false;
      }
    } else if (!hour.equals(other.hour)) {
      return false;
    }
    if (minute == null) {
      if (other.minute != null) {
        return false;
      }
    } else if (!minute.equals(other.minute)) {
      return false;
    }
    if (month == null) {
      if (other.month != null) {
        return false;
      }
    } else if (!month.equals(other.month)) {
      return false;
    }
    if (second == null) {
      if (other.second != null) {
        return false;
      }
    } else if (!second.equals(other.second)) {
      return false;
    }
    if (year == null) {
      if (other.year != null) {
        return false;
      }
    } else if (!year.equals(other.year)) {
      return false;
    }
    return true;
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = new JSONObject();
    obj.put("year", this.getYear());
    obj.put("month", this.getMonth());
    obj.put("day", this.getDay());
    obj.put("hour", this.getHour());
    obj.put("minute", this.getMinute());
    obj.put("second", this.getSecond());
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = new HashMap<>();
    obj.put("year", this.getYear());
    obj.put("month", this.getMonth());
    obj.put("day", this.getDay());
    obj.put("hour", this.getHour());
    obj.put("minute", this.getMinute());
    obj.put("second", this.getSecond());
    return obj;
  }

  private String checkDateOrDay(Integer dateOrDay, Boolean isYear) {
    if (dateOrDay != null) {
      return dateOrDay.toString();
    }

    if (isYear == true) {
      return "????";
    }

    return "??";
  }

  @Override
  public int compareTo(IDate date) {
    if (this.equals(date)) {
      return 0;
    }

    if (date.getClass() == IntervalDate.class) {
      return compareWithIntervalDate((IntervalDate) date);
    }

    // compare simple date
    SimpleDate simpleDate = (SimpleDate) date;
    int comparationResult = this.getYear() == null || simpleDate.getYear() == null ? 0
        : this.getYear().compareTo(simpleDate.getYear());
    if (comparationResult != 0) {
      return comparationResult;
    }

    comparationResult = this.getMonth() == null || simpleDate.getMonth() == null ? 0
        : this.getMonth().compareTo(simpleDate.getMonth());
    if (comparationResult != 0) {
      return comparationResult;
    }

    comparationResult = this.getDay() == null || simpleDate.getDay() == null ? 0
        : this.getDay().compareTo(simpleDate.getDay());
    if (comparationResult != 0) {
      return comparationResult;
    }

    comparationResult = this.getHour() == null || simpleDate.getHour() == null ? 0
        : this.getHour().compareTo(simpleDate.getHour());
    if (comparationResult != 0) {
      return comparationResult;
    }

    comparationResult = this.getMinute() == null || simpleDate.getMinute() == null ? 0
        : this.getMinute().compareTo(simpleDate.getMinute());
    if (comparationResult != 0) {
      return comparationResult;
    }

    return this.getSecond() == null || simpleDate.getSecond() == null ? 0
        : this.getSecond().compareTo(simpleDate.getSecond());
  }

  private int compareWithIntervalDate(IntervalDate date) {
    int compareWithStartDate = this.compareTo(date.getStartDate());
    int compareWithEndDate = this.compareTo(date.getEndDate());

    // if the interval contains this date
    if (compareWithStartDate == 1 && compareWithEndDate == -1) {
      return -10;
    }

    // if the interval is before this date
    if (compareWithEndDate > 0) {
      return compareWithEndDate;
    }

    // the other possible scenario is if the interval is after this date
    return compareWithStartDate;
  }

  @Override
  public boolean isEmpty() {
    return this.getDay() == null && this.getHour() == null && this.getMinute() == null
        && this.getMonth() == null && this.getSecond() == null && this.getYear() == null;
  }
}
