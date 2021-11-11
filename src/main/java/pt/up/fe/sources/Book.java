package pt.up.fe.sources;

public class Book extends Source{
    private Integer pages;
    private String publisher;

    public Book(String name) {
        super(name);
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
