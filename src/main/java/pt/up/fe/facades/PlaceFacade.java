package pt.up.fe.facades;

import pt.up.fe.Main;
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


}
