package pt.up.fe.imports;

import java.util.List;

public interface Strategy<T, G> {
  List<T> doImport(List<G> data);
}

