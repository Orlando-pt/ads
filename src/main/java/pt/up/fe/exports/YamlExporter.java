package pt.up.fe.exports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlExporter<T extends IExportObject> extends Exporter<T> {
  public YamlExporter(String path) {
    super(path);
  }

  @Override
  protected String appendExtension(String path) {
    if (path.endsWith(".yaml") == true) {
      return path;
    }
    if (path.endsWith(".yml") == true) {
      return path;
    }
    return path += ".yaml";
  }

  @Override
  public String buildOutputString(Iterator<T> iterator) {
    List<Map<String, Object>> obj = new ArrayList<>();
    while (iterator.hasNext()) {
      T c = iterator.next();
      obj.add(c.toYAMLObject());
    }
    Yaml yaml = new Yaml();
    return yaml.dumpAll(obj.iterator());
  }
}
