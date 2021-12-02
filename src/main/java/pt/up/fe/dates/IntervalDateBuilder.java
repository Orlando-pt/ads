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
    if (this.activeDate.equals(startDate)) {
      this.activeDate = this.endDate;
    } else {
      this.activeDate = this.startDate;
    }
    return this;
  }

  @Override
  public IBuilder setYear(Integer year) {
    this.activeDate.setYear(year);
    return this;
  }

  @Override
  public IBuilder setMonth(Integer month) {
    this.activeDate.setMonth(month);
    return this;
  }

  @Override
  public IBuilder setDay(Integer day) {
    this.activeDate.setDay(day);
    return this;
  }

  @Override
  public IBuilder setHour(Integer hour) {
    this.activeDate.setHour(hour);
    return this;
  }

  @Override
  public IBuilder setMinute(Integer minute) {
    this.activeDate.setMinute(minute);
    return this;
  }

  @Override
  public IBuilder setSecond(Integer second) {
    this.activeDate.setSecond(second);
    return this;
  }

  @Override
  public IDate build() {
    return new IntervalDate(startDate, endDate);
  }
}
