package pt.up.fe.dates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    IDate date1 = new SimpleDate();
    IDate interval1 = new IntervalDate(null, null);

    System.out.println(
      interval1.getClass() == IntervalDate.class
    );
  }
}
