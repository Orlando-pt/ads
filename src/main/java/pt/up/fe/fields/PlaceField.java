package pt.up.fe.fields;

import pt.up.fe.places.Place;

public class PlaceField extends Field implements ExportFieldInterface {

    private Place fieldValue;

    public PlaceField(boolean isSensitive) {
        super(isSensitive);
    }

    public PlaceField(boolean isSensitive, Place fieldValue) {
        super(isSensitive);
        this.fieldValue = fieldValue;
    }

    public Place getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Place fieldValue) {
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
        return "PlaceField [fieldValue=" + fieldValue + ", isSensitive=" + super.isSensitive() + "]";
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
        PlaceField other = (PlaceField) obj;
        if (fieldValue == null) {
            if (other.fieldValue != null)
                return false;
        } else if (!fieldValue.equals(other.fieldValue))
            return false;
        return true;
    }
    
}
