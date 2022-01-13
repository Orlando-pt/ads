package pt.up.fe.queries;

public class NameAttribute {

    private String name;
    private boolean exact;

    public NameAttribute(String name) {
        this.name = name;
        this.exact = false;
    }
    
    public NameAttribute(String name, boolean exact) {
        this.name = name;
        this.exact = exact;
    }

    public void setExact(boolean exact) {
        this.exact = exact;
    }

    public boolean getExact() {
        return this.exact;
    }

    public String getName() {
        return this.name;
    }
}
