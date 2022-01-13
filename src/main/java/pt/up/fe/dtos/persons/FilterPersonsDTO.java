package pt.up.fe.dtos.persons;

import pt.up.fe.dates.SimpleDate;
import pt.up.fe.person.Person;

public class FilterPersonsDTO {
  private String firstName;
  private String middleName;
  private String lastName;
  private FilterPersonType filterPersonType;
  private SimpleDate startDate;
  private SimpleDate endDate;
  private Person person;

  public FilterPersonType getFilterPersonType() {
    return filterPersonType;
  }

  public void setFilterPersonType(FilterPersonType filterPersonType) {
    this.filterPersonType = filterPersonType;
  }

  public SimpleDate getStartDate() {
    return startDate;
  }

  public void setStartDate(SimpleDate startDate) {
    this.startDate = startDate;
  }

  public SimpleDate getEndDate() {
    return endDate;
  }

  public void setEndDate(SimpleDate endDate) {
    this.endDate = endDate;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
