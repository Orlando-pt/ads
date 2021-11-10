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

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Source type: " + this.getClass().getSimpleName());
        sBuilder.append(super.toString());
        sBuilder.append("\n Link: " + this.getLink());
        return sBuilder.toString();
    }
}
