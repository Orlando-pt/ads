package pt.up.fe.iterators;

import java.util.LinkedList;

import pt.up.fe.places.CompoundPlace;
import pt.up.fe.places.Place;

public class PlaceBreathIterator implements PlaceIteratorInterface<Place>{

    private LinkedList<Place> placesWaitingQueue;

    public PlaceBreathIterator(Place root) {
        this.placesWaitingQueue = new LinkedList<>();
        this.placesWaitingQueue.add(root);
    }
    @Override
    public boolean hasNext() {
        return !this.placesWaitingQueue.isEmpty();
    }

    @Override
    public Place next() {
        Place currentPlace = this.placesWaitingQueue.pollFirst();

        if (currentPlace.isComposite()) {
            CompoundPlace compountPlace = (CompoundPlace) currentPlace;
            this.placesWaitingQueue.addAll(compountPlace.getChildren());
        }
        return currentPlace;
    }
    
    
}
