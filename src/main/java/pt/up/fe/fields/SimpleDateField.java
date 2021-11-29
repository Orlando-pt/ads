package pt.up.fe.fields;

import pt.up.fe.dates.SimpleDate;

public class SimpleDateField extends Field implements ExportFieldInterface{

    private SimpleDate fieldValue;

    public SimpleDateField(boolean isSensitive) {
        super(isSensitive);
    }

    public SimpleDateField(boolean isSensitive, SimpleDate fieldValue) {
        super(isSensitive);
        this.fieldValue = fieldValue;
    }

    public SimpleDate getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(SimpleDate fieldValue) {
        this.fieldValue = fieldValue;
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
        SimpleDateField other = (SimpleDateField) obj;
        if (fieldValue == null) {
            if (other.fieldValue != null)
                return false;
        } else if (!fieldValue.equals(other.fieldValue))
            return false;
        return true;
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
        return "SimpleDateField [fieldValue=" + fieldValue + ", isSensitive=" + super.isSensitive() + "]";
    }
    
}
