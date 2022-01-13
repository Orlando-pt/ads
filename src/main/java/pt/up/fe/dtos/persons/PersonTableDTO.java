package pt.up.fe.dtos.persons;

import pt.up.fe.dates.IDate;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

public class PersonTableDTO {

  private String firstName;

  private String lastName;

  private String middleName;

  private Gender gender;

  private IDate birthDate;

  private Integer children;

  private Person person;

  public PersonTableDTO(
      String firstName,
      String middleName,
      String lastName,
      Gender gender,
      IDate birthDate,
      Integer children,
      Person person) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = gender;
    this.birthDate = birthDate;
    this.children = children;
    this.person = person;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public IDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(IDate birthDate) {
    this.birthDate = birthDate;
  }

  public Integer getChildren() {
    return children;
  }

  public void setChildren(Integer children) {
    this.children = children;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
}
