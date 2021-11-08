package pt.up.fe.places;

interface IPlaceBuilder {

  IPlaceBuilder startCompound(String name);

  IPlaceBuilder endCompound();

  IPlaceBuilder addParish(String name);

  Place getResult();

  IPlaceBuilder setLatitude(Double latitude);

  IPlaceBuilder setLongitude(Double longitude);

  IPlaceBuilder setAltitude(Double altitude);

  IPlaceBuilder setArea(Double area);
}
