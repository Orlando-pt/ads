package pt.up.fe.imports;

import org.json.JSONObject;
import pt.up.fe.exports.IExportObject;

public abstract class JsonStrategy<T extends IExportObject> implements Strategy<T, JSONObject> {}
