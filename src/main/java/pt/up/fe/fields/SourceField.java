package pt.up.fe.fields;

import pt.up.fe.sources.Source;

public class SourceField extends Field implements ExportFieldInterface{

    private Source fieldValue;

    public SourceField(boolean isSensitive) {
        super(isSensitive);
    }

    public SourceField(boolean isSensitive, Source fieldValue) {
        super(isSensitive);
        this.fieldValue = fieldValue;
    }

    public Source getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Source fieldValue) {
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
        return "SourceField [fieldValue=" + fieldValue + ", isSensitive=" + super.isSensitive() + "]";
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
        SourceField other = (SourceField) obj;
        if (fieldValue == null) {
            if (other.fieldValue != null)
                return false;
        } else if (!fieldValue.equals(other.fieldValue))
            return false;
        return true;
    }

    
    
}
