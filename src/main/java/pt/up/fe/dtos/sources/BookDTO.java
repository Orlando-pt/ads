package pt.up.fe.dtos.sources;

public class BookDTO extends SourceDTO{

  private Integer pages;
  private String publisher;


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
}
