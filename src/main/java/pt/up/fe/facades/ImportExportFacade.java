package pt.up.fe.facades;

import java.util.List;
import pt.up.fe.Main;
import pt.up.fe.events.Event;
import pt.up.fe.exports.Exporter;
import pt.up.fe.exports.JsonExporter;
import pt.up.fe.imports.EventJsonStrategy;
import pt.up.fe.imports.ImportUtils;
import pt.up.fe.imports.ImportUtilsInputDto;
import pt.up.fe.imports.ImportUtilsOutputDto;
import pt.up.fe.imports.JsonImporter;
import pt.up.fe.imports.PersonJsonStrategy;
import pt.up.fe.imports.PlaceJsonStrategy;
import pt.up.fe.imports.SourceJsonStrategy;
import pt.up.fe.person.Person;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

public class ImportExportFacade {

  public static void exportContent() {
    Exporter<Place> expPlaces = new JsonExporter<Place>("./places");
    expPlaces.setObject(Main.placesList.iterator());
    expPlaces.export();

    Exporter<Person> expPeople = new JsonExporter<Person>("./people");
    expPeople.setObject(Main.peopleList.iterator());
    expPeople.export();

    Exporter<Event> expEvents = new JsonExporter<Event>("./events");
    expEvents.setObject(Main.eventsList.iterator());
    expEvents.export();

    Exporter<Source> expSources = new JsonExporter<Source>("./sources");
    expSources.setObject(Main.sourcesList.iterator());
    expSources.export();
  }

  public static void importContent() {
    JsonImporter<Place> importerPlaces = new JsonImporter<>(new PlaceJsonStrategy(), "./places");
    List<Place> importedPlaces = importerPlaces.doImport();

    JsonImporter<Person> importerPeople = new JsonImporter<>(new PersonJsonStrategy(), "./people");
    List<Person> importedPeople = importerPeople.doImport();

    JsonImporter<Event> importerEvents = new JsonImporter<>(new EventJsonStrategy(), "./events");
    List<Event> importedEvents = importerEvents.doImport();

    JsonImporter<Source> importerSources = new JsonImporter<>(new SourceJsonStrategy(),
        "./sources");
    List<Source> importedSources = importerSources.doImport();

    ImportUtilsOutputDto importUtilsOutputDto = ImportUtils.link(
        new ImportUtilsInputDto(importedPeople, importedSources, importedPlaces, importedEvents));
    Main.eventsList = importUtilsOutputDto.getEvents();
    Main.peopleList = importUtilsOutputDto.getPeople();
    Main.placesList = importUtilsOutputDto.getPlaces();
    Main.sourcesList = importUtilsOutputDto.getSources();
  }

}
