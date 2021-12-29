package pt.up.fe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.up.fe.events.Birth;
import pt.up.fe.events.CustomEvent;
import pt.up.fe.events.Event;
import pt.up.fe.facades.DateFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;
import pt.up.fe.sources.Book;
import pt.up.fe.sources.Source;

public class Main extends Application {

  public static List<Person> peopleList = new ArrayList<>();
  public static List<Source> sourcesList = new ArrayList<>();
  public static List<Event> eventsList = new ArrayList<>();

  private static Scene scene;
  private static Stage stage;


  public static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }


  @Override
  public void start(Stage stage) throws Exception {
    Person breno = new Person();
    Person catia = new Person();
    breno.setName("Breno");
    breno.setGender(Gender.MALE);
    catia.setName("Catia");
    catia.setGender(Gender.FEMALE);

    Person diogo = new Person();
    Person sofia = new Person();
    diogo.setName("Diogo");
    diogo.setMiddleName("Cão");
    diogo.setLastName("Costa");
    sofia.setName("Sofia");
    diogo.setGender(Gender.MALE);
    sofia.setGender(Gender.FEMALE);

    Person hugo = new Person();
    Person carolina = new Person();
    hugo.setName("Hugo");
    carolina.setName("Carolina");
    hugo.setGender(Gender.MALE);
    carolina.setGender(Gender.FEMALE);

    diogo.addChild(hugo);
    sofia.addChild(hugo);

    breno.addChild(carolina);
    catia.addChild(carolina);

    Main.peopleList.add(breno);
    Main.peopleList.add(catia);
    Main.peopleList.add(hugo);
    Main.peopleList.add(carolina);
    Main.peopleList.add(diogo);
    Main.peopleList.add(sofia);

    Book book = new Book("nome");
    book.setPages(2);
    Main.sourcesList.add(book);

    Event birthEvent = new Birth();
    birthEvent.addSpecialPurposeField("Maternity", "Bissaya Barreto");
    birthEvent.setDate(new DateFacade().createSimpleDate("2021", "", "", "", "",""));
    birthEvent.addPeopleRelation("Mother", catia);
    birthEvent.setDescription("Nascimento do Diogo");
    Main.eventsList.add(birthEvent);

    Event customEvent = new CustomEvent("Ginásio");
    customEvent.setDate(new DateFacade().createSimpleDate("2019", "09", "12", "", "",""));
    customEvent.addPeopleRelation("Personal Trainer", breno);
    customEvent.setDescription("Entrada no ginásio");
    Main.eventsList.add(customEvent);

    stage.initStyle(StageStyle.UNDECORATED);
    setPrimaryStage(stage);
    setPrimaryScene(scene);
    Parent root = loadFXML("fxml/alwaysdisplayed/main");

    scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
    CustomSceneHelper.contentAreaPaneController.setEventHandlers();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void setPrimaryStage(Stage stage) {
    Main.stage = stage;
  }

  public static Stage getMainStage() {
    return Main.stage;
  }

  private void setPrimaryScene(Scene scene) {
    Main.scene = scene;
  }

  public static Scene getMainScene() {
    return Main.scene;
  }
}
