package pt.up.fe.dates;

import pt.up.fe.exports.IExportObject;

public interface IDate extends IExportObject, Comparable<IDate> {
  boolean isEmpty();
}
