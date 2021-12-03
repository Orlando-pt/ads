package pt.up.fe.fields;

public class StringField extends Field implements ExportFieldInterface{

    private String fieldValue;

    public StringField(String fieldValue, boolean isSensitive) {
        super(isSensitive);
        this.fieldValue = fieldValue;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
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
        StringField other = (StringField) obj;
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

        return this.fieldValue;
    }
    
    @Override
    public String exportFieldValueIncludingSensitive() {
        return this.fieldValue;
    }

    @Override
    public String toString() {
        return "StringField [fieldValue=" + fieldValue + ", isSensitive=" + super.isSensitive() + "]";
    }

    
}
