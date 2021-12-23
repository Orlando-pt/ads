package pt.up.fe.facades;

import java.util.HashMap;
import java.util.Scanner;
import pt.up.fe.dates.IDate;
import pt.up.fe.events.Birth;
import pt.up.fe.events.Event;
import pt.up.fe.person.Person;

public class EventFacade {

  private PlaceFacade placeFacade;
  private DateFacade dateFacade;

  public EventFacade() {
    this.placeFacade = new PlaceFacade();
    this.dateFacade = new DateFacade();
  }

  public PlaceFacade getPlaceFacade() {
    return placeFacade;
  }

  public void setPlaceFacade(PlaceFacade placeFacade) {
    this.placeFacade = placeFacade;
  }

  public DateFacade getDateFacade() {
    return dateFacade;
  }

  public void setDateFacade(DateFacade dateFacade) {
    this.dateFacade = dateFacade;
  }

  public Event createBirthEvent(String maternity, String placeOfBirth, IDate dateOfBirth,
      HashMap<String, Person> persons, HashMap<String, String> specialFields, String description) {
    Event birthEvent = new Birth();

    if (!maternity.isEmpty()) {
      birthEvent.addSpecialPurposeField("Maternity", maternity);
    }

    /*
    if(!placeOfBirth.isEmpty()) {
      birthEvent.addPlaceRelation("Place of Birth", this.getPlaceFacade().choosePlace());
    }
     */

    if (dateOfBirth != null) {
      birthEvent.setDate(dateOfBirth);
    }

    persons.forEach((key, value) -> {
      birthEvent.addPeopleRelation(key, value);
    });

    specialFields.forEach((key, value) -> {
      birthEvent.addSpecialPurposeField(key, value);
    });

    if (!description.isEmpty()) {
      birthEvent.setDescription(description);
    }

    return birthEvent;
  }
}
