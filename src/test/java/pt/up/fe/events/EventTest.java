package pt.up.fe.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pt.up.fe.dates.SimpleDate;
import pt.up.fe.places.Parish;
import pt.up.fe.places.Place;

public class EventTest {
    
    @Test
    void testJsonParsingOfEvents() {
        /**
         * verify toString of birth event
         */
        SimpleDate date = new SimpleDate();
        date.setDay(25);
        date.setMonth(12);
        date.setYear(2020);

        Place nazare = new Parish("Nazaré", 0d, 0d, 1d);

        Event jesusBirth = new Birth(
            nazare,
            date
        );

        String expectedResult = "{\"name\":\"Birth\",\"description\":null,\"source\":null,\"place\":{\"name\":\"Nazaré\",\"description" + 
        "\":null,\"source\":null,\"latitude\":0.0,\"longitude\":0.0,\"area\":1.0,\"composite\":false},\"date\":{\"year\":2020,\"month\":12,\"day\":25,\"hour\":null," + 
        "\"minute\":null,\"second\":null},\"peopleRelations\":{},\"dateRelations\":{},\"placeRelations\":{},\"specialPurposeFields\":{}}";

        assertEquals(
            expectedResult,
            jesusBirth.toString()
        );

        /**
         * verify toString of death event
         */
        SimpleDate dateOfDeath = new SimpleDate();
        dateOfDeath.setDay(26);
        dateOfDeath.setMonth(12);
        dateOfDeath.setYear(2020);

        Place jerusalem = new Parish("Jerusalem", 1d, 1d, 2d);

        Event jesusDeath = new Death(
            jerusalem,
            dateOfDeath
        );

        String expectedResultDeath = "{\"name\":\"Death\",\"description\":null,\"source\":null,\"place\":{\"name\":\"Jerusalem\",\"description" + 
        "\":null,\"source\":null,\"latitude\":1.0,\"longitude\":1.0,\"area\":2.0,\"composite\":false},\"date\":{\"year\":2020,\"month\":12,\"day\":26,\"hour\":null," + 
        "\"minute\":null,\"second\":null},\"peopleRelations\":{},\"dateRelations\":{},\"placeRelations\":{},\"specialPurposeFields\":{}}";

        assertEquals(
            expectedResultDeath,
            jesusDeath.toString()
        );
    }
}
