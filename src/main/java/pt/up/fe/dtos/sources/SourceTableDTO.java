package pt.up.fe.dtos.sources;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;
import pt.up.fe.sources.Source;

public class SourceTableDTO {

  private String type;

  private String name;

  private IDate dateOfPublication;

  private String authors;

  // Book
  private Integer pages;
  private String publisher;

  // Historical Record
  private Place nationalArchiveCountry;

  // Online Resource
  private String link;

  // OrallyTransmitted
  private Place place;

  private Source source;

  public SourceTableDTO(String type, String name, IDate dateOfPublication,
      String authors, Source source) {
    this.type = type;
    this.name = name;
    this.dateOfPublication = dateOfPublication;
    this.authors = authors;
    this.source = source;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Source getSource() {
    return source;
  }

  public void setSource(Source source) {
    this.source = source;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public IDate getDateOfPublication() {
    return dateOfPublication;
  }

  public void setDateOfPublication(IDate dateOfPublication) {
    this.dateOfPublication = dateOfPublication;
  }

  public String getAuthors() {
    return authors;
  }

  public void setAuthors(String authors) {
    this.authors = authors;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Place getNationalArchiveCountry() {
    return nationalArchiveCountry;
  }

  public void setNationalArchiveCountry(Place nationalArchiveCountry) {
    this.nationalArchiveCountry = nationalArchiveCountry;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }
}
