package pt.up.fe.places;

import java.util.Stack;

public class PlaceBuilder implements IPlaceBuilder {
  private Stack<CompoundPlace> stack = new Stack<>();
  private CompoundPlace currentCompound;
  private Place currentPlace;

  public PlaceBuilder() {
    this.setRoot("root");
  }

  public PlaceBuilder(String name) {
    this.setRoot(name);
  }

  private void setRoot(String name) {
    CompoundPlace root = new CompoundPlace(name);
    this.currentCompound = root;
  }

  @Override
  public PlaceBuilder startCompound(String name) {
    CompoundPlace newCompound = new CompoundPlace(name);
    this.currentCompound.addChild(newCompound);
    this.stack.push(this.currentCompound);
    this.currentCompound = newCompound;
    this.currentPlace = newCompound;
    return this;
  }

  @Override
  public PlaceBuilder endCompound() {
    if (this.stack.isEmpty() == true) {
      return this;
    }
    CompoundPlace previousCompound = this.stack.pop();
    this.currentCompound = previousCompound;
    this.currentPlace = previousCompound;
    return this;
  }

  @Override
  public PlaceBuilder addParish(String name) {
    Parish parish = new Parish(name);
    this.currentCompound.addChild(parish);
    this.currentPlace = parish;
    return this;
  }

  @Override
  public Place getResult() {
    while (this.stack.isEmpty() == false) {
      this.endCompound();
    }
    return this.currentCompound;
  }

  @Override
  public PlaceBuilder setLatitude(Double latitude) {
    this.currentPlace.setLatitude(latitude);
    return this;
  }

  @Override
  public PlaceBuilder setLongitude(Double longitude) {
    this.currentPlace.setLongitude(longitude);
    return this;
  }

  @Override
  public PlaceBuilder setAltitude(Double altitude) {
    this.currentPlace.setAltitude(altitude);
    return this;
  }

  @Override
  public PlaceBuilder setArea(Double area) {
    if (this.currentPlace.isComposite() == false) {
      Parish place = (Parish) this.currentPlace;
      place.setArea(area);
      this.currentPlace = place;
    }
    return this;
  }
}
