package pt.up.fe.queries;

public class SpecifiedPersonAttributes {

    private NameAttribute name;
    private NameAttribute middleName;
    private NameAttribute lastName;

    public SpecifiedPersonAttributes() {}

    public NameAttribute getName() {
        return this.name;
    }

    public void setName(NameAttribute name) {
        this.name = name;
    }

    public NameAttribute getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(NameAttribute middleName) {
        this.middleName = middleName;
    }
    
    public NameAttribute getLastName() {
        return this.lastName;
    }

    public void setLastName(NameAttribute lastName) {
        this.lastName = lastName;
    }
}
