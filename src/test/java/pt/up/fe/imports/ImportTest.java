package pt.up.fe.imports;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import pt.up.fe.person.Person;

public class ImportTest {
  private String writeDummy() throws IOException {
    String path = "people.json";
    String str =
        "[{\"gender\":\"MALE\",\"children\":[\"b1a33fc1-22a5-4350-92d9-f06d04067101\",\"631b3659-8127-4804-9ce6-d34b6842fac3\"],\"name\":\"Diogo\",\"id\":\"9ae856ce-26c3-41bd-8cdf-1b5d857de3c2\",\"events\":[]},{\"gender\":\"FEMALE\",\"children\":[\"b1a33fc1-22a5-4350-92d9-f06d04067101\",\"631b3659-8127-4804-9ce6-d34b6842fac3\"],\"name\":\"Sofia\",\"id\":\"1ee13dfd-2bf4-47aa-a844-a05e1fc7b13e\",\"events\":[]},{\"gender\":\"MALE\",\"children\":[\"62c45756-ee48-4f37-9c3c-8c1b795ef808\"],\"name\":\"Hugo\",\"description\":\"Tesla"
            + " FTW\",\"id\":\"72e4c32c-280f-41c1-86d3-a808061a621e\",\"events\":[]},{\"gender\":\"FEMALE\",\"children\":[\"62c45756-ee48-4f37-9c3c-8c1b795ef808\"],\"name\":\"Carolina\",\"id\":\"8b34d101-b866-4d00-b2c2-9c8a65e3d56b\",\"events\":[]}]";
    FileWriter file = new FileWriter(path);
    file.write(str);
    file.close();

    return path;
  }

  @Test
  public void testJSONImport() throws IOException {
    String path = this.writeDummy();
    JsonImporter<Person> importer = new JsonImporter<>(new PersonJsonStrategy(), path);
    assertDoesNotThrow(
        () -> {
          importer.doImport();
        });
  }

  @Test
  public void testYAMlimport() {}
}
