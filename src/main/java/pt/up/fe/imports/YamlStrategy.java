package pt.up.fe.imports;

import java.util.Map;
import pt.up.fe.exports.IExportObject;

public abstract class YamlStrategy<T extends IExportObject>
    implements Strategy<T, Map<String, Object>> {}
