package pt.up.fe.places;

public class Parish extends Place {

    public Parish(Double latitude, Double longitude, Double area) {
        super(latitude, longitude, area);
    }

    @Override
    public Boolean isComposite() {
        return false;
    }
}
