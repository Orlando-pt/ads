package pt.up.fe.fields;

import pt.up.fe.person.Person;

public class PersonField extends Field implements ExportFieldInterface{

    private Person fieldValue;

    public PersonField(boolean isSensitive) {
        super(isSensitive);
    }

    public PersonField(boolean isSensitive, Person fieldValue) {
        super(isSensitive);
        this.fieldValue = fieldValue;
    }

    public Person getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Person fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String exportFieldValue() {
        if (super.isSensitive())
            return "";

        return this.fieldValue.toString();
    }
    
    @Override
    public String exportFieldValueIncludingSensitive() {
        return this.fieldValue.toString();
    }

    @Override
    public String toString() {
        return "PersonField [fieldValue=" + fieldValue + ", isSensitive=" + super.isSensitive() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fieldValue == null) ? 0 : fieldValue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonField other = (PersonField) obj;
        if (fieldValue == null) {
            if (other.fieldValue != null)
                return false;
        } else if (!fieldValue.equals(other.fieldValue))
            return false;
        return true;
    }
    
}
