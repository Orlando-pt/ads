package pt.up.fe.facades;

import pt.up.fe.places.Place;
import pt.up.fe.places.PlaceBuilder;

public class PlaceFacade {
    public static Place choosePlace() {
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
