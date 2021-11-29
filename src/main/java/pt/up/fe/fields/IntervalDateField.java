package pt.up.fe.fields;

import pt.up.fe.dates.IntervalDate;

public class IntervalDateField extends Field implements ExportFieldInterface{

    private IntervalDate fieldValue;

    public IntervalDateField(boolean isSensitive) {
        super(isSensitive);
    }

    public IntervalDateField(boolean isSensitive, IntervalDate fieldValue) {
        super(isSensitive);
        this.fieldValue = fieldValue;
    }

    public IntervalDate getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(IntervalDate fieldValue) {
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
        IntervalDateField other = (IntervalDateField) obj;
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
        return "IntervalDateField [fieldValue=" + fieldValue + ", isSensitive=" + super.isSensitive() + "]";
    }

}
