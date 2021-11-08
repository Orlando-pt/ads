package pt.up.fe.sources;

import pt.up.fe.dates.IDate;

import java.util.ArrayList;
import java.util.List;

public abstract class Source {
    private IDate dateOfPublication;
    private String name;
    private List<String> authors;

    public Source(String name) {
        this.name = name;
        this.authors = new ArrayList<>();
    }

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
