package pt.up.fe.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ArrayListFieldTest {

    @Test
    void toStringOfArrayListFieldTest() {
        ArrayListField<String> notSensitiveArray = new ArrayListField<>(false);

        notSensitiveArray.getFieldValue().add("Hello");
        notSensitiveArray.getFieldValue().add("How");
        notSensitiveArray.getFieldValue().add("you");
        notSensitiveArray.getFieldValue().add("doin?");

        assertEquals("[Hello, How, you, doin?]", notSensitiveArray.exportFieldValue());

        ArrayListField<String> sensitiveArray = new ArrayListField<>(true, notSensitiveArray.getFieldValue());
        assertEquals("", sensitiveArray.exportFieldValue());
    }
    
    @Test
    void stringFieldAndArrayListFieldTogetherTest() {
        StringField joao = new StringField("Joao", true);
        StringField joana = new StringField("Joana", false);

        ArrayListField<StringField> arrayWithName = new ArrayListField<>(
            false,
            Arrays.asList(joao, joana)
        );

        assertEquals(
            "[Joana]",
            arrayWithName.exportFieldValue()
        );
    }
}
