package pt.up.fe.facades;

import java.util.Scanner;
import pt.up.fe.places.Place;
import pt.up.fe.places.PlaceBuilder;

public class PlaceFacade {

  private Scanner scanner;

  public PlaceFacade(Scanner scanner) {
    this.scanner = scanner;
  }

  public Scanner getScanner() {
    return scanner;
  }

  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  public Place choosePlace() {
    PlaceBuilder builder = new PlaceBuilder("Portugal");

    return builder
        .startCompound("Lisboa")
        .startCompound("Lisboa")
        .addParish("Benfica")
        .addParish("Parque das Nações")
        .endCompound()
        .startCompound("Sintra")
        .addParish("Mem Martins")
        .addParish("Mercês")
        .endCompound()
        .endCompound()
        .getResult();
  }
}
