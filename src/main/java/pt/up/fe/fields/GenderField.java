package pt.up.fe.fields;

import pt.up.fe.person.Gender;

public class GenderField extends Field implements ExportFieldInterface{

    private Gender fieldValue;

    public GenderField(boolean isSensitive) {
        super(isSensitive);
    }

    public GenderField(boolean isSensitive, Gender fieldValue) {
        super(isSensitive);
        this.fieldValue = fieldValue;
    }

    public Gender getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Gender fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String exportFieldValue() {
        if (super.isSensitive())
            return "";

        return this.fieldValue.toString();
    }

    @Override
    public String toString() {
        return "GenderField [fieldValue=" + fieldValue + ", isSensitive=" + super.isSensitive() + "]";
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
        GenderField other = (GenderField) obj;
        if (fieldValue != other.fieldValue)
            return false;
        return true;
    }

}
