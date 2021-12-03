package pt.up.fe.dtos.sources;

import java.util.List;
import pt.up.fe.dates.IDate;

public abstract class SourceDTO {

  private IDate dateOfPublication;
  private String name;
  private List<String> authors;

  public IDate getDateOfPublication() {
    return dateOfPublication;
  }

  public void setDateOfPublication(IDate dateOfPublication) {
    this.dateOfPublication = dateOfPublication;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }
}
