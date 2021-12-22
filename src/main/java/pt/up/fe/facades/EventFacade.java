package pt.up.fe.facades;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import pt.up.fe.dates.IDate;
import pt.up.fe.dtos.events.FieldDTO;
import pt.up.fe.dtos.events.PersonEventDTO;
import pt.up.fe.events.Birth;
import pt.up.fe.events.CustomEvent;
import pt.up.fe.events.Death;
import pt.up.fe.events.Emigration;
import pt.up.fe.events.Event;
import pt.up.fe.events.Marriage;
import pt.up.fe.events.Residence;
import pt.up.fe.person.Person;

public class EventFacade {

  private PlaceFacade placeFacade;
  private DateFacade dateFacade;

  public EventFacade() {
    Scanner sc = new Scanner(System.in);
    this.placeFacade = new PlaceFacade(sc);
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

  public Event createBirthEvent(String maternity, String placeOfBirth, IDate dateOfBirth, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description) {
    Event birthEvent = new Birth();

    if(!maternity.isEmpty()) {
      birthEvent.addSpecialPurposeField("Maternity", maternity);
    }

    if(!placeOfBirth.isEmpty()) {
      birthEvent.addPlaceRelation("Place of Birth", this.getPlaceFacade().choosePlace());
    }

    if(dateOfBirth != null) {
      birthEvent.setDate(dateOfBirth);
    }

    persons.forEach((key, value) -> {
      birthEvent.addPeopleRelation(key, value);
    });

    specialFields.forEach((key, value) -> {
      birthEvent.addSpecialPurposeField(key, value);
    });

    if(!description.isEmpty()) {
      birthEvent.setDescription(description);
    }

    return birthEvent;
  }

  public Event createDeathEvent(String typeOfDeath, String placeOfDeath, IDate dateOfDeath, HashMap<String, Person> persons, HashMap<String, String> specialFields, String description) {
    Event deathEvent = new Death();

    if(!typeOfDeath.isEmpty()) {
      deathEvent.addSpecialPurposeField("Type of Death", typeOfDeath);
    }

    if(!placeOfDeath.isEmpty()) {
      deathEvent.addPlaceRelation("Place of Death", this.getPlaceFacade().choosePlace());
    }

    if(dateOfDeath != null) {
      deathEvent.setDate(dateOfDeath);
    }

    persons.forEach((key, value) -> {
      deathEvent.addPeopleRelation(key, value);
    });

    specialFields.forEach((key, value) -> {
      deathEvent.addSpecialPurposeField(key, value);
    });

    if(!description.isEmpty()) {
      deathEvent.setDescription(description);
    }

    return deathEvent;
  }
}
