package pt.up.fe.places;

import pt.up.fe.BaseClass;

public abstract class Place extends BaseClass {
    private Double latitude;
    private Double longitude;
    private Double area;

    public Place(Double latitude, Double longitude, Double area) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
    }

    public abstract Boolean isComposite();

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
