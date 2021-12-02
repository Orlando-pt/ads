package pt.up.fe.dates;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class IntervalDate implements IDate {
  private IDate startDate;
  private IDate endDate;

  public IntervalDate(IDate start, IDate end) {
    this.startDate = start;
    this.endDate = end;
  }

  public IDate getStartDate() {
    return startDate;
  }

  public void setStartDate(IDate startDate) {
    this.startDate = startDate;
  }

  public IDate getEndDate() {
    return endDate;
  }

  public void setEndDate(IDate endDate) {
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
    return "IntervalDate{" + "startDate=" + startDate + ", endDate=" + endDate + '}';
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
}
