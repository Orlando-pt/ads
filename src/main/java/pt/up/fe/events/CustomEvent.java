package pt.up.fe.events;

import pt.up.fe.dates.IDate;
import pt.up.fe.places.Place;

public class CustomEvent extends Event {

    public CustomEvent(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Event type: " + super.getName());

        for (var field : super.getSpecialPurposeFields().entrySet()) {
            sBuilder.append("\n" + field.getKey() + ": " + field.getValue());
        }

        return sBuilder.toString();
    }
}
