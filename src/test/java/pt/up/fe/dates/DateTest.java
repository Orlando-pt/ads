package pt.up.fe.dates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateTest {

  @Test
  void dateTest() {
    IDate date =
        new IntervalDateBuilder()
            .reset()
            .setYear(2020)
            .setMonth(1)
            .setDay(1)
            .switchDate()
            .setYear(2021)
            .build();

    IDate expectedDate =
        new IntervalDate(
            (SimpleDate)
                new SimpleDateBuilder().reset().setYear(2020).setMonth(1).setDay(1).build(),
            (SimpleDate) new SimpleDateBuilder().reset().setYear(2021).build());

    assertEquals(expectedDate, date);
  }

  @Test
  void testEqualDates() {
    SimpleDate date1 = new SimpleDate();
    date1.setDay(1);
    date1.setMonth(1);
    date1.setYear(2022);

    SimpleDate date2 = new SimpleDate();
    date2.setDay(1);
    date2.setMonth(1);
    date2.setYear(2022);

    assertTrue(date1.equals(date2));

    date2.setHour(20);

    assertFalse(date1.equals(date2));

    IntervalDate interval1 = new IntervalDate(date1, date2);
    IntervalDate interval2 = new IntervalDate(date1, date2);

    assertTrue(interval1.equals(interval2));

    SimpleDate date3 = new SimpleDate();
    date3.setYear(2021);

    IntervalDate interval3 = new IntervalDate(
      date1,
      date3
    );

    assertFalse(interval1.equals(interval3));
  }

  @Test
  void testCompareDates() {
    SimpleDate date1 = new SimpleDate();
    date1.setYear(2021);
    date1.setMonth(12);
    date1.setDay(24);
    
    SimpleDate date2 = new SimpleDate();
    date2.setYear(2021);
    // date2.setMonth(12);
    date2.setDay(25);

    assertEquals(
      -1,
      date1.compareTo(date2)
    );

    assertEquals(
      1,
      date2.compareTo(date1)
    );

    date2.setDay(null);
    assertEquals(
      0,
      date1.compareTo(date2)
    );

    // compare date with interval
    IntervalDate interval1 = new IntervalDate(
      new SimpleDate(2021, 10, 1),
      new SimpleDate(2021, 10, 10)
    );

    // date greater than the interval
    assertEquals(
      1,
      date1.compareTo(interval1)
    );

    // date lower than the interval
    IntervalDate interval2 = new IntervalDate(
      new SimpleDate(2022, 10, 10),
      new SimpleDate(2022, 10, 15)
    );

    assertEquals(
      -1,
      date1.compareTo(interval2)
    );

    // verify incomplete intervals
    SimpleDate dateNull1 = new SimpleDate();
    dateNull1.setYear(2022);
    SimpleDate dateNull2 = new SimpleDate();
    dateNull2.setYear(2023);

    IntervalDate interval3 = new IntervalDate(
      dateNull1,
      dateNull2     
    );

    assertEquals(
      -1,
      date1.compareTo(interval3)
    );

    // check interval that contains date
    IntervalDate interval4 = new IntervalDate(
      new SimpleDate(2021, 11, 20),
      new SimpleDate(2022, 1, 1)
    );

    assertEquals(
      -10,
      date1.compareTo(interval4)
    );
  }
}
