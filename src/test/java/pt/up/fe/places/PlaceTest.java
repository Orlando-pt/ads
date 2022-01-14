package pt.up.fe.places;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaceTest {
  private Place p;

  @BeforeEach
  public void setUp() {
    CompoundPlace pais = new CompoundPlace("Portugal");
    CompoundPlace distrito = new CompoundPlace("Lisboa");
    CompoundPlace concelho1 = new CompoundPlace("Lisboa");
    CompoundPlace concelho2 = new CompoundPlace("Sintra");
    Parish freguesia1 = new Parish("Benfica");
    freguesia1.setArea(25d);
    Parish freguesia2 = new Parish("Parque das Nações");
    freguesia2.setArea(25d);
    Parish freguesia3 = new Parish("Mem Martins");
    freguesia3.setArea(75d);
    Parish freguesia4 = new Parish("Mercês");
    freguesia4.setArea(100d);

    concelho1.addChild(freguesia1);
    concelho1.addChild(freguesia2);
    concelho2.addChild(freguesia3);
    concelho2.addChild(freguesia4);

    distrito.addChild(concelho1);
    distrito.addChild(concelho2);

    pais.addChild(distrito);
    this.p = pais;
  }

  @Test
  public void testOutput() {
    PrintStream originalOut = System.out;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    System.out.print(this.p);

    StringBuilder sBuilder = new StringBuilder();
    sBuilder.append("Portugal");

    String expected = sBuilder.toString();

    assertEquals(expected, outContent.toString());

    System.setOut(originalOut);
  }

  @Test
  public void testArea() {
    Double expected = 225d;
    assertEquals(expected, this.p.getArea(), 0.00001);
  }
}
