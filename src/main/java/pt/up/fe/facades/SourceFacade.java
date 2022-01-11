package pt.up.fe.facades;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import pt.up.fe.Main;
import pt.up.fe.dtos.sources.BookDTO;
import pt.up.fe.dtos.sources.CustomSourceDTO;
import pt.up.fe.dtos.sources.FilterSourcesDTO;
import pt.up.fe.dtos.sources.HistoricalRecordDTO;
import pt.up.fe.dtos.sources.OnlineResourceDTO;
import pt.up.fe.dtos.sources.OrallyTransmittedDTO;
import pt.up.fe.sources.Book;
import pt.up.fe.sources.CustomSource;
import pt.up.fe.sources.HistoricalRecord;
import pt.up.fe.sources.OnlineResource;
import pt.up.fe.sources.OrallyTransmitted;
import pt.up.fe.sources.Source;

public class SourceFacade {

  public static Book createBook(BookDTO bookDTO) {
    Book book = new Book(bookDTO.getName());
    book = setBookProperties(book, bookDTO);
    Main.sourcesList.add(book);
    return book;
  }

  private static Book setBookProperties(Book book, BookDTO bookDTO) {
    book.setName(bookDTO.getName());
    book.setPages(bookDTO.getPages());
    book.setPublisher(bookDTO.getPublisher());
    book.setDateOfPublication(bookDTO.getDateOfPublication());
    book.setAuthors(bookDTO.getAuthors());
    return book;
  }

  public static Book editBook(Book book, BookDTO bookDTO) {
    Book bookEdited = setBookProperties(book, bookDTO);
    return bookEdited;
  }

  public static CustomSource createCustomSource(CustomSourceDTO customSourceDTO) {
    CustomSource customSource = new CustomSource(customSourceDTO.getName());
    customSource = setCustomSourceProperties(customSource, customSourceDTO);
    Main.sourcesList.add(customSource);
    return customSource;
  }

  private static CustomSource setCustomSourceProperties(CustomSource customSource,
      CustomSourceDTO customSourceDTO) {
    customSource.setName(customSourceDTO.getName());
    customSource.setAuthors(customSourceDTO.getAuthors());
    customSource.setDateOfPublication(customSourceDTO.getDateOfPublication());
    return customSource;
  }

  public static CustomSource editCustomSource(CustomSource customSource,
      CustomSourceDTO customSourceDTO) {
    CustomSource customSourceEdited = setCustomSourceProperties(customSource, customSourceDTO);
    return customSourceEdited;
  }


  public static HistoricalRecord createHistoricalRecord(HistoricalRecordDTO historicalRecordDTO) {
    HistoricalRecord historicalRecord = new HistoricalRecord(historicalRecordDTO.getName());
    historicalRecord = setHistoricalRecordProperties(historicalRecord, historicalRecordDTO);
    Main.sourcesList.add(historicalRecord);
    return historicalRecord;
  }

  private static HistoricalRecord setHistoricalRecordProperties(HistoricalRecord historicalRecord,
      HistoricalRecordDTO historicalRecordDTO) {
    historicalRecord.setName(historicalRecordDTO.getName());
    historicalRecord.setAuthors(historicalRecordDTO.getAuthors());
    historicalRecord.setDateOfPublication(historicalRecordDTO.getDateOfPublication());
    historicalRecord.setNationalArchiveCountry(historicalRecordDTO.getNationalArchiveCountry());
    return historicalRecord;
  }

  public static HistoricalRecord editHistoricalRecord(HistoricalRecord historicalRecord,
      HistoricalRecordDTO historicalRecordDTO) {
    HistoricalRecord historicalRecordEdited = setHistoricalRecordProperties(historicalRecord,
        historicalRecordDTO);
    return historicalRecordEdited;
  }

  public static OnlineResource createOnlineResource(OnlineResourceDTO onlineResourceDTO) {
    OnlineResource onlineResource = new OnlineResource(onlineResourceDTO.getName());
    onlineResource = setOnlineResource(onlineResource, onlineResourceDTO);
    Main.sourcesList.add(onlineResource);
    return onlineResource;
  }

  private static OnlineResource setOnlineResource(OnlineResource onlineResource,
      OnlineResourceDTO onlineResourceDTO) {
    onlineResource.setName(onlineResourceDTO.getName());
    onlineResource.setAuthors(onlineResourceDTO.getAuthors());
    onlineResource.setDateOfPublication(onlineResourceDTO.getDateOfPublication());
    onlineResource.setLink(onlineResourceDTO.getLink());
    return onlineResource;
  }

  public static OnlineResource editOnlineResource(OnlineResource onlineResource,
      OnlineResourceDTO onlineResourceDTO) {
    OnlineResource onlineResourceEdited = setOnlineResource(onlineResource, onlineResourceDTO);
    return onlineResourceEdited;
  }

  public static OrallyTransmitted createOrallyTransmitted(
      OrallyTransmittedDTO orallyTransmittedDTO) {
    OrallyTransmitted orallyTransmitted = new OrallyTransmitted(orallyTransmittedDTO.getName());
    orallyTransmitted = setOrallyTransmitted(orallyTransmitted, orallyTransmittedDTO);
    Main.sourcesList.add(orallyTransmitted);
    return orallyTransmitted;
  }

  private static OrallyTransmitted setOrallyTransmitted(OrallyTransmitted orallyTransmitted,
      OrallyTransmittedDTO orallyTransmittedDTO) {
    orallyTransmitted.setName(orallyTransmittedDTO.getName());
    orallyTransmitted.setAuthors(orallyTransmittedDTO.getAuthors());
    orallyTransmitted.setDateOfPublication(orallyTransmittedDTO.getDateOfPublication());
    orallyTransmitted.setPlace(orallyTransmittedDTO.getPlace());
    return orallyTransmitted;
  }

  public static OrallyTransmitted editOrallyTransmitted(OrallyTransmitted orallyTransmitted,
      OrallyTransmittedDTO orallyTransmittedDTO) {
    OrallyTransmitted orallyTransmittedEdited = setOrallyTransmitted(orallyTransmitted,
        orallyTransmittedDTO);
    return orallyTransmittedEdited;
  }

  public static List<Source> filterSources(FilterSourcesDTO filterSourcesDTO) {

    Predicate<Source> byName =
        source ->
            filterSourcesDTO.getName().isEmpty()
                || source.getName() != null
                && source
                .getName()
                .toLowerCase()
                .contains(filterSourcesDTO.getName().toLowerCase());

    List<Source> result = Main.sourcesList.stream().filter(byName).collect(Collectors.toList());

    return result;
  }
}
