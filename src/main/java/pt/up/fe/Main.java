package pt.up.fe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.up.fe.dates.IntervalDate;
import pt.up.fe.dates.SimpleDate;
import pt.up.fe.events.Birth;
import pt.up.fe.events.CustomEvent;
import pt.up.fe.events.Death;
import pt.up.fe.events.Emigration;
import pt.up.fe.events.Event;
import pt.up.fe.events.Marriage;
import pt.up.fe.events.Residence;
import pt.up.fe.facades.DateFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;
import pt.up.fe.places.CompoundPlace;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Book;
import pt.up.fe.sources.CustomSource;
import pt.up.fe.sources.HistoricalRecord;
import pt.up.fe.sources.OnlineResource;
import pt.up.fe.sources.OrallyTransmitted;
import pt.up.fe.sources.Source;

public class Main extends Application {

  public static List<Person> peopleList = new ArrayList<>();
  public static List<Source> sourcesList = new ArrayList<>();
  public static List<Place> placesList = new ArrayList<>();
  public static List<Event> eventsList = new ArrayList<>();
  public static boolean editMode = false;


  private static Scene scene;
  private static Stage stage;


  public static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }


  @Override
  public void start(Stage stage) throws Exception {
    Person breno = new Person("eb6ad4a6-4cba-450d-be0f-baae7d06643a");
    breno.setName("Breno");
    breno.setMiddleName("Fonseca");
    breno.setLastName("Salles");
    breno.setDescription("Breno is cool");
    breno.setGender(Gender.MALE);

    Book book = new Book("Tim Cook Biography");
    book.setPages(250);
    book.setPublisher("Porto Editora");
    book.setAuthors(Arrays.asList("João Manel"));
    book.setDateOfPublication(new SimpleDate(2000, 5, 22));
    breno.setSource(book);

    Main.peopleList.add(breno);
    Main.sourcesList.add(book);

    Person catia = new Person("eb6ad4a6-4cba-450d-be0f-baae7d06643b");
    catia.setName("Catia");
    catia.setMiddleName("Susana da Silva");
    catia.setLastName("Condez");
    catia.setGender(Gender.FEMALE);
    catia.setDescription("Catia is nice");
    CustomSource customSource = new CustomSource("TVI");
    customSource.setAuthors(Arrays.asList("Cristina Ferreira"));
    catia.setSource(customSource);

    Main.peopleList.add(catia);
    Main.sourcesList.add(customSource);

    Person diogo = new Person("eb6ad4a6-4cba-450d-be0f-baae7d06643c");
    diogo.setName("Diogo");
    diogo.setMiddleName("Alexandre");
    diogo.setLastName("Costa");
    diogo.setGender(Gender.MALE);
    diogo.setDescription("Diogo is awful");

    HistoricalRecord historicalRecord = new HistoricalRecord("Constituição da Républica");
    CompoundPlace portugal = new CompoundPlace("Portugal");
    historicalRecord.setNationalArchiveCountry(portugal);
    diogo.setSource(historicalRecord);

    Main.peopleList.add(diogo);
    Main.sourcesList.add(historicalRecord);
    Main.placesList.add(portugal);

    Person sofia = new Person("eb6ad4a6-4cba-450d-be0f-baae7d06643d");
    sofia.setGender(Gender.FEMALE);
    sofia.setName("Sofia");
    sofia.setMiddleName("Isabel");
    sofia.setLastName("Antunes");

    OnlineResource onlineResource = new OnlineResource("JPGStore");
    onlineResource.setLink("www.jpg.pt");
    sofia.setSource(onlineResource);

    Main.peopleList.add(sofia);
    Main.sourcesList.add(onlineResource);

    Person hugo = new Person("eb6ad4a6-4cba-450d-be0f-baae7d06643e");
    hugo.setName("Hugo");
    hugo.setMiddleName("Paiva");
    hugo.setLastName("Almeida");
    hugo.setGender(Gender.MALE);

    OrallyTransmitted orallyTransmitted = new OrallyTransmitted("Tasca do Quim Joaquim");
    CompoundPlace vouzela = new CompoundPlace("Vouzela");
    vouzela.setLongitude(45.2);
    vouzela.setLatitude(40.0);
    vouzela.setAltitude(200.0);
    vouzela.setDescription("Beautiful Land");
    orallyTransmitted.setPlace(vouzela);
    hugo.setSource(orallyTransmitted);

    Main.peopleList.add(hugo);
    Main.sourcesList.add(orallyTransmitted);
    Main.placesList.add(vouzela);

    Person carolina = new Person("eb6ad4a6-4cba-450d-be0f-baae7d06643f");
    carolina.setName("Carolina");
    carolina.setMiddleName("Marques");
    carolina.setLastName("Albuquerque");
    carolina.setGender(Gender.FEMALE);
    Main.peopleList.add(carolina);

    Event birthEvent = new Birth();
    birthEvent.addSpecialPurposeField("Maternity", "Bissaya Barreto");
    birthEvent.addPeopleRelation("Mother", catia);
    birthEvent.addPeopleRelation("Father", breno);
    birthEvent.setDescription("Nascimento do Diogo");
    birthEvent.setDate(
        new IntervalDate(
            new SimpleDate(1800, 1, 1),
            new SimpleDate(1800, 1, 3)
        )
    );
    diogo.addEvent(birthEvent);
    breno.addChild(diogo);
    catia.addChild(diogo);
    Main.eventsList.add(birthEvent);

    Event birthEvent1 = new Birth();
    birthEvent1.addSpecialPurposeField("Maternity", "Hospital de Viseu");
    birthEvent1.addPeopleRelation("Mother", sofia);
    birthEvent1.addPeopleRelation("Father", diogo);
    birthEvent1.setDescription("Nascimento do Hugo");
    birthEvent1.setDate(
        new DateFacade().createSimpleDate("2000", "09", "12", "2", "5", "1")
    );
    hugo.addEvent(birthEvent1);
    diogo.addChild(hugo);
    sofia.addChild(hugo);
    Main.eventsList.add(birthEvent1);

    Event customEvent = new CustomEvent("Ginásio");
    customEvent.setDate(new DateFacade().createSimpleDate("2019", "09", "12", "", "5", ""));
    customEvent.addPeopleRelation("Personal Trainer", breno);
    customEvent.addSpecialPurposeField("Type of Custom Event", "Fitness Hut");
    customEvent.setDescription("Entrada no ginásio");
    Main.eventsList.add(customEvent);
    diogo.addEvent(customEvent);

    Event deathEvent = new Death();
    deathEvent.addSpecialPurposeField("Type of Death", "Death by Age");
    deathEvent.setDate(new DateFacade().createSimpleDate("2103", "09", "25", "", "", "10"));
    deathEvent.addPeopleRelation("Coveiro", hugo);
    deathEvent.setDescription("Morte do Diogo");
    Main.eventsList.add(deathEvent);
    diogo.addEvent(deathEvent);

    Event emigrationEvent = new Emigration();
    emigrationEvent.addSpecialPurposeField("Type of Emigration", "Emigração para Paris");
    emigrationEvent.setDate(
        new DateFacade().createSimpleDate("2050", "05", "25", "22", "15", "10"));
    emigrationEvent.addPeopleRelation("Motorista", hugo);
    emigrationEvent.addSpecialPurposeField("Push factor",
        "Lack of freedom to choose religion, or to choose no religion");
    emigrationEvent.addSpecialPurposeField("Pull factor", "Promise of higher pay");
    emigrationEvent.setDescription("Emigração para Paris");
    Main.eventsList.add(emigrationEvent);
    diogo.addEvent(emigrationEvent);

    Event marriageEvent = new Marriage(sofia, diogo);
    marriageEvent.addSpecialPurposeField("Marriage Name", "Casamento com a Sofia");
    marriageEvent.setDate(new DateFacade().createSimpleDate("2030", "07", "31", "", "", ""));
    marriageEvent.addPeopleRelation("Bride", sofia);
    marriageEvent.addSpecialPurposeField("Type Of Marriage", "Secret marriage");
    marriageEvent.setDescription("Casamento realizado em Copa Cabana");
    Main.eventsList.add(marriageEvent);
    diogo.addEvent(marriageEvent);

    Event residenceEvent = new Residence();
    residenceEvent.addSpecialPurposeField("Residence Name", "Embaixador do Brasil");
    residenceEvent.setDate(new DateFacade().createSimpleDate("2045", "02", "18", "11", "", ""));
    residenceEvent.addPeopleRelation("Empregado", breno);
    residenceEvent.addSpecialPurposeField("Type Of Place", "Villa");
    residenceEvent.setDescription("Embaixador do Brasil durante 2 anos");
    Main.eventsList.add(residenceEvent);
    diogo.addEvent(residenceEvent);

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
