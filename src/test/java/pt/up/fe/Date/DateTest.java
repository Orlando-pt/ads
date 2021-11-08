package pt.up.fe.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pt.up.fe.dates.IDate;
import pt.up.fe.dates.IntervalDate;
import pt.up.fe.dates.IntervalDateBuilder;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.dates.SimpleDateBuilder;

public class DateTest {

    @Test
    void dateTest() {
        IDate date = new IntervalDateBuilder()
                            .reset()
                            .setYear(2020)
                            .setMonth(1)
                            .setDay(1)
                            .switchDate()
                            .setYear(2021).build();

        IDate expectedDate = new IntervalDate(
            (SimpleDate) new SimpleDateBuilder().reset().setYear(2020)
                .setMonth(1).setDay(1).build(),
                (SimpleDate) new SimpleDateBuilder().reset().setYear(2021).build()
        );

        assertEquals(expectedDate, date);
    }
    
}
