package pt.up.fe.facades;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import pt.up.fe.Main;
import pt.up.fe.dtos.places.FilterPlacesDTO;
import pt.up.fe.dtos.places.PlaceDTO;
import pt.up.fe.dtos.places.PlaceType;
import pt.up.fe.places.CompoundPlace;
import pt.up.fe.places.Parish;
import pt.up.fe.places.Place;

public class PlaceFacade {

  public static Place createPlace(PlaceDTO placeDTO) {
    Place place;
    if (placeDTO.getType() == PlaceType.PARISH) {
      place = new Parish(placeDTO.getName());
      ((Parish) place).setArea(placeDTO.getArea());
    } else {
      place = new CompoundPlace(placeDTO.getName());
    }

    place.setDescription(placeDTO.getDescription());
    place.setSource(placeDTO.getSource());
    place.setAltitude(placeDTO.getAltitude());
    place.setLongitude(placeDTO.getLongitude());
    place.setLatitude(placeDTO.getLatitude());

    Main.placesList.add(place);
    return place;
  }

  public static List<Place> filterPlaces(FilterPlacesDTO filterPlacesDTO) {

    Predicate<Place> byName = place -> filterPlacesDTO.getName().isEmpty()
        || place.getName() != null && place.getName().toLowerCase()
        .contains(filterPlacesDTO.getName().toLowerCase());

    Predicate<Place> byType = place -> filterPlacesDTO.getType() == null
        || place.getClass().getSimpleName().toLowerCase()
        .contains(filterPlacesDTO.getType().toString().toLowerCase());

    List<Place> result = Main.placesList.stream()
        .filter(byName.and(byType))
        .collect(Collectors.toList());

    return result;

  }


}
