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
    book.setPages(bookDTO.getPages());
    book.setPublisher(bookDTO.getPublisher());
    book.setDateOfPublication(bookDTO.getDateOfPublication());
    book.setAuthors(bookDTO.getAuthors());
    Main.sourcesList.add(book);
    return book;
  }

  public static CustomSource createCustomSource(CustomSourceDTO customSourceDTO) {
    CustomSource customSource = new CustomSource(customSourceDTO.getName());
    customSource.setAuthors(customSourceDTO.getAuthors());
    customSource.setDateOfPublication(customSourceDTO.getDateOfPublication());
    Main.sourcesList.add(customSource);
    return customSource;
  }

  public static HistoricalRecord createHistoricalRecord(HistoricalRecordDTO historicalRecordDTO) {
    HistoricalRecord historicalRecord = new HistoricalRecord(historicalRecordDTO.getName());
    historicalRecord.setAuthors(historicalRecordDTO.getAuthors());
    historicalRecord.setDateOfPublication(historicalRecordDTO.getDateOfPublication());
    historicalRecord.setNationalArchiveCountry(historicalRecordDTO.getNationalArchiveCountry());
    Main.sourcesList.add(historicalRecord);
    return historicalRecord;
  }

  public static OnlineResource createOnlineResource(OnlineResourceDTO onlineResourceDTO) {
    OnlineResource onlineResource = new OnlineResource(onlineResourceDTO.getName());
    onlineResource.setAuthors(onlineResourceDTO.getAuthors());
    onlineResource.setDateOfPublication(onlineResourceDTO.getDateOfPublication());
    onlineResource.setLink(onlineResourceDTO.getLink());
    Main.sourcesList.add(onlineResource);
    return onlineResource;
  }

  public static OrallyTransmitted createOrallyTransmitted(
      OrallyTransmittedDTO orallyTransmittedDTO) {
    OrallyTransmitted orallyTransmitted = new OrallyTransmitted(orallyTransmittedDTO.getName());
    orallyTransmitted.setAuthors(orallyTransmittedDTO.getAuthors());
    orallyTransmitted.setDateOfPublication(orallyTransmittedDTO.getDateOfPublication());
    orallyTransmitted.setPlace(orallyTransmittedDTO.getPlace());
    Main.sourcesList.add(orallyTransmitted);
    return orallyTransmitted;
  }


  public static List<Source> filterSources(FilterSourcesDTO filterSourcesDTO) {

    Predicate<Source> byName = source -> filterSourcesDTO.getName().isEmpty()
        || source.getName() != null && source.getName().toLowerCase()
        .contains(filterSourcesDTO.getName().toLowerCase());

    List<Source> result = Main.sourcesList.stream()
        .filter(byName)
        .collect(Collectors.toList());

    return result;

  }
}
