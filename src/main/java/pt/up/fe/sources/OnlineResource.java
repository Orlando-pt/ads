package pt.up.fe.sources;

public class OnlineResource extends Source {
    private String link;

    public OnlineResource(String name) {
        super(name);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
