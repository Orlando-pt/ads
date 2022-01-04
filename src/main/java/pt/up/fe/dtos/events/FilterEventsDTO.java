package pt.up.fe.dtos.events;

import pt.up.fe.dates.IDate;

public class FilterEventsDTO {
    private String event;

    private String date;

    private String description;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
