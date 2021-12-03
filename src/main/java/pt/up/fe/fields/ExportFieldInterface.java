package pt.up.fe.fields;

public interface ExportFieldInterface {

    // when the user wants to export ignoring sensitive fields
    String exportFieldValue();

    // with sensitive fields and all
    String exportFieldValueIncludingSensitive();
}
