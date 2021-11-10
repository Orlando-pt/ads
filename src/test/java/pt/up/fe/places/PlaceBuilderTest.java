package pt.up.fe.places;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class PlaceBuilderTest {

  @Test
  public void testOutput() {
    PrintStream originalOut = System.out;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    IPlaceBuilder builder = new PlaceBuilder("Portugal");

    Place place =
        builder
            .startCompound("Lisboa")
            .startCompound("Lisboa")
            .addParish("Benfica")
            .addParish("Parque das Nações")
            .endCompound()
            .startCompound("Sintra")
            .addParish("Mem Martins")
            .addParish("Mercês")
            .endCompound()
            .endCompound()
            .getResult();

    System.out.print(place);

    StringBuilder sBuilder = new StringBuilder();
    sBuilder.append("Portugal(");
    sBuilder.append("Lisboa(");
    sBuilder.append("Lisboa(");
    sBuilder.append("Benfica+Parque das Nações");
    sBuilder.append(")");
    sBuilder.append("+");
    sBuilder.append("Sintra(");
    sBuilder.append("Mem Martins+Mercês");
    sBuilder.append(")");
    sBuilder.append(")");
    sBuilder.append(")");

    String expected = sBuilder.toString();

    assertEquals(expected, outContent.toString());

    System.setOut(originalOut);
  }

  @Test
  public void testArea() {
    PlaceBuilder builder = new PlaceBuilder("Portugal");

    Place place =
        builder
            .startCompound("Lisboa")
            .startCompound("Lisboa")
            .addParish("Benfica")
            .setArea(25d)
            .addParish("Parque das Nações")
            .setArea(25d)
            .endCompound()
            .startCompound("Sintra")
            .addParish("Mem Martins")
            .setArea(75d)
            .addParish("Mercês")
            .setArea(100d)
            .endCompound()
            .endCompound()
            .getResult();

    Double expected = 225d;
    assertEquals(expected, place.getArea(), 0.00001);
  }
}
