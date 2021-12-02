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
    return "SimpleDate{"
        + "year="
        + year
        + ", month="
        + month
        + ", day="
        + day
        + ", hour="
        + hour
        + ", minute="
        + minute
        + ", second="
        + second
        + '}';
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
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    SimpleDate other = (SimpleDate) obj;
    if (day == null) {
      if (other.day != null) return false;
    } else if (!day.equals(other.day)) return false;
    if (hour == null) {
      if (other.hour != null) return false;
    } else if (!hour.equals(other.hour)) return false;
    if (minute == null) {
      if (other.minute != null) return false;
    } else if (!minute.equals(other.minute)) return false;
    if (month == null) {
      if (other.month != null) return false;
    } else if (!month.equals(other.month)) return false;
    if (second == null) {
      if (other.second != null) return false;
    } else if (!second.equals(other.second)) return false;
    if (year == null) {
      if (other.year != null) return false;
    } else if (!year.equals(other.year)) return false;
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
}
