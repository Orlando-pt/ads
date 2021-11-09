package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class Birth extends Event {
    public Birth() {
        this.setName(this.getClass().getSimpleName());
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Event type: " + super.getName());
        sBuilder.append("Place: " + super.getPlace());

        for (var field : super.getSpecialPurposeFields().entrySet()) {
            sBuilder.append("\n" + field.getKey() + ": " + field.getValue());
        }

        for (var place : super.getPlaceRelations().entrySet()) {
            sBuilder.append("\n" + place.getKey() + ": " + place.getValue().toString());
        }

        for (var people : super.getPeopleRelations().entrySet()) {
            sBuilder.append("\n" + people.getKey() + ": " + people.getValue().toString());
        }

        sBuilder.append("\nDescription: " + super.getDescription());
        return sBuilder.toString();
    }
}
