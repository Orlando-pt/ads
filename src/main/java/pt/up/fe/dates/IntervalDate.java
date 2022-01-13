package pt.up.fe.dates;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

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
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    IntervalDate other = (IntervalDate) obj;
    if (endDate == null) {
      if (other.endDate != null) return false;
    } else if (!endDate.equals(other.endDate)) return false;
    if (startDate == null) {
      if (other.startDate != null) return false;
    } else if (!startDate.equals(other.startDate)) return false;
    return true;
  }

  @Override
  public String toString() {
    return startDate.toString() + " - " + endDate.toString();
  }

  @Override
  public JSONObject toJSONObject() {
    JSONObject obj = new JSONObject();
    if (this.getStartDate() != null) {
      obj.put("startDate", this.getStartDate().toJSONObject());
    }
    if (this.getEndDate() != null) {
      obj.put("endDate", this.getEndDate().toJSONObject());
    }
    return obj;
  }

  @Override
  public Map<String, Object> toYAMLObject() {
    Map<String, Object> obj = new HashMap<>();
    if (this.getStartDate() != null) {
      obj.put("startDate", this.getStartDate().toYAMLObject());
    }
    if (this.getEndDate() != null) {
      obj.put("endDate", this.getEndDate().toYAMLObject());
    }
    return obj;
  }

  @Override
  public int compareTo(IDate date) {
    if (this.equals(date))
      return 0;

    if (date.getClass() == SimpleDate.class)
      return this.compareWithSimpleDate((SimpleDate) date);

    IntervalDate intervalDate = (IntervalDate) date;
    int compareWithStartDate = this.startDate.compareTo(intervalDate);
    int compareWithEndDate = this.endDate.compareTo(intervalDate);

    if ((compareWithStartDate == 1 || compareWithStartDate == 0) && (compareWithEndDate == -1 || compareWithEndDate == 0))
      return -10;

    if (compareWithStartDate == -1 && compareWithEndDate == 1)
      return -10;

    if (compareWithStartDate == 1)
      return compareWithStartDate;

    return compareWithEndDate;
  }

  private int compareWithSimpleDate(SimpleDate date) {
    int comparationResult = date.compareTo(this);
    if (comparationResult == -10) return comparationResult;

    return -comparationResult;
  }

  @Override
  public boolean isEmpty() {
    return this.endDate.isEmpty() && this.startDate.isEmpty();
  }

}
