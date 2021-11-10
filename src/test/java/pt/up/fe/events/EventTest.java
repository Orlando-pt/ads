package pt.up.fe.events;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Place nazare = new Parish("Nazaré");

        Event jesusBirth = new Birth();
        jesusBirth.setDate(date);
        jesusBirth.setPlace(nazare);

        String expectedResult = "{\"name\":\"Birth\",\"description\":null,\"source\":null,\"place\":{\"name\":\"Nazaré\",\"description\"" + 
        ":null,\"source\":null,\"latitude\":null,\"longitude\":null,\"altitude\":null,\"area\":0.0,\"composite\":false},\"date\"" +
        ":{\"year\":2020,\"month\":12,\"day\":25,\"hour\":null,\"minute\":null,\"second\":null},\"peopleRelations\":{},\"dateRelations\":{},\"placeRelations\"" +
        ":{},\"specialPurposeFields\":{}}";

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

        Place jerusalem = new Parish("Jerusalem");

        Event jesusDeath = new Death();
        jesusDeath.setDate(dateOfDeath);
        jesusDeath.setPlace(jerusalem);

        String expectedResultDeath = "{\"name\":\"Death\",\"description\":null,\"source\":null,\"place\":{\"name\":\"Jerusalem\",\"description\"" + 
        ":null,\"source\":null,\"latitude\":null,\"longitude\":null,\"altitude\":null,\"area\":0.0,\"composite\":false},\"date\"" +
        ":{\"year\":2020,\"month\":12,\"day\":26,\"hour\":null,\"minute\":null,\"second\":null},\"peopleRelations\":{},\"dateRelations\":{},\"placeRelations\"" +
        ":{},\"specialPurposeFields\":{}}";

        assertEquals(
            expectedResultDeath,
            jesusDeath.toString()
        );
    }

}
