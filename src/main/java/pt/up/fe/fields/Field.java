package pt.up.fe.fields;

public class Field {

    private boolean isSensitive;

    public Field(boolean isSensitive) {
        this.isSensitive = isSensitive;
    }

    public boolean isSensitive() {
        return isSensitive;
    }

    public void setSensitive(boolean isSensitive) {
        this.isSensitive = isSensitive;
    }

    public boolean switchSensitive() {
        if (this.isSensitive == false)
            this.isSensitive = true;
        else
            this.isSensitive = false;

        return this.isSensitive;
    }
}
