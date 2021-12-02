package pt.up.fe.dtos.events;

public class FieldDTO {
    private String field;
    private String name;

    public FieldDTO(String field, String name) {
        this.field = field;
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
