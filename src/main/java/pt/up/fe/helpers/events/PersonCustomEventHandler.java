package pt.up.fe.helpers.events;

import javafx.event.EventHandler;

public abstract class PersonCustomEventHandler implements EventHandler<PersonCustomEvent> {
    private String relation;

    public PersonCustomEventHandler(String relation) {
        this.relation = relation;
    }

    public String getRelation() {
        return relation;
    }

}
