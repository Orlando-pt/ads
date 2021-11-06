package pt.up.fe.places;

import java.util.ArrayList;
import java.util.List;

public class CompoundPlace extends Place {
    private List<Place> children;

    public CompoundPlace(String name, Double latitude, Double longitude, Double area) {
        super(name, latitude, longitude, area);
        this.children = new ArrayList<>();
    }


    @Override
    public Boolean isComposite() {
        return null;
    }

    public List<Place> getChildren() {
        return children;
    }

    public void addChild(Place child) {
        this.children.add(child);
    }

    public void removeChild(Place child) {
        this.children.remove(child);
    }
}
