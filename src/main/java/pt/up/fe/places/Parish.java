package pt.up.fe.places;

public class Parish extends Place {

    public Parish(String name, Double latitude, Double longitude, Double area) {
        super(name, latitude, longitude, area);
    }

    @Override
    public Boolean isComposite() {
        return false;
    }
}
