package pt.up.fe.exports;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import pt.up.fe.events.Birth;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

public class ExportTest {
  private List<Person> getRandomList() {
    List<Person> list = new ArrayList<>();

    Person breno = new Person();
    breno.setName("Breno");
    breno.setDescription("Breno happy.");
    breno.setGender(Gender.MALE);

    Person catia = new Person();
    catia.setName("Catia");
    breno.setDescription("Catia happy.");
    breno.setGender(Gender.FEMALE);

    Person diogo = new Person();
    diogo.setName("Diogo");
    diogo.setGender(Gender.MALE);
    Person sofia = new Person();
    sofia.setName("Sofia");
    sofia.setGender(Gender.FEMALE);

    Person hugo = new Person();
    hugo.setName("Hugo");
    hugo.setGender(Gender.MALE);
    hugo.setDescription("Tesla FTW");
    Person carolina = new Person();
    carolina.setName("Carolina");
    carolina.setGender(Gender.FEMALE);

    Person orlando = new Person();
    orlando.setName("Orlando");

    Birth brenoBirth = new Birth();
    Birth catiaBirth = new Birth();

    brenoBirth.addPeopleRelation("father", diogo);
    catiaBirth.addPeopleRelation("father", diogo);

    brenoBirth.addPeopleRelation("mother", sofia);
    catiaBirth.addPeopleRelation("mother", sofia);

    breno.addEvent(brenoBirth);
    catia.addEvent(catiaBirth);

    diogo.addChild(breno);
    diogo.addChild(catia);
    sofia.addChild(breno);
    sofia.addChild(catia);

    Birth orlandoBirth = new Birth();
    orlandoBirth.addPeopleRelation("father", hugo);
    orlandoBirth.addPeopleRelation("mother", carolina);

    orlando.addEvent(orlandoBirth);
    hugo.addChild(orlando);
    carolina.addChild(orlando);

    list.add(diogo);
    list.add(sofia);
    list.add(hugo);
    list.add(carolina);

    return list;
  }

  @Test
  public void testJSONExport() {
    List<Person> list = this.getRandomList();
    Exporter<Person> exp = new JsonExporter<Person>("./file");
    exp.setObject(list.iterator());
    assertDoesNotThrow(
        () -> {
          exp.export();
        });
  }

  @Test
  public void testYAMLExport() {
    List<Person> list = this.getRandomList();
    Exporter<Person> exp = new YamlExporter<Person>("./file");
    exp.setObject(list.iterator());
    assertDoesNotThrow(
        () -> {
          exp.export();
        });
  }
}
