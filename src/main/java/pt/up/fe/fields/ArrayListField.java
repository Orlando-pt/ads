package pt.up.fe.fields;

import java.util.ArrayList;
import java.util.List;

public class ArrayListField<T> extends Field implements ExportFieldInterface{

    private List<T> fieldValue;

    public ArrayListField(boolean isSensitive, List<T> fieldValue) {
        super(isSensitive);
        this.fieldValue = fieldValue;
    }

    public ArrayListField(boolean isSensitive) {
        super(isSensitive);
        this.fieldValue = new ArrayList<>();
    }

    public List<T> getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(List<T> fieldValue) {
        this.fieldValue = fieldValue;
    }

    private String transformArrayToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        String prefix = "";
        for (T val : this.fieldValue) {
            sb.append(prefix);
            
            if (val instanceof ExportFieldInterface) {
                String exportString = ((ExportFieldInterface) val).exportFieldValue();
                sb.append(exportString);
                if (exportString.equals(""))
                    continue;
            } else {
                sb.append(val.toString());
            }

            prefix = ", ";
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String exportFieldValue() {
        if (super.isSensitive())
            return "";
        
        return this.transformArrayToString();
    }

    @Override
    public String exportFieldValueIncludingSensitive() {
        return this.transformArrayToString();
    }

    @Override
    public String toString() {
        return "ArrayListField [fieldValue=" + fieldValue + ", isSensitive=" + super.isSensitive() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fieldValue == null) ? 0 : fieldValue.hashCode());
        return result;
    }
}
