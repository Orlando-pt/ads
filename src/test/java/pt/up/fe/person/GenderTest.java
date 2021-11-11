package pt.up.fe.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    @Test
    public void testGenderMale() {
        assertEquals(Gender.valueOf(1), Gender.MALE);
    }

    @Test
    public void testGenderFemale() {
        assertEquals(Gender.valueOf(2), Gender.FEMALE);
    }

}