package pt.up.fe.iterators;

import java.util.Iterator;

public interface PersonIteratorInterface<T> extends Iterator<T>{
    
    // retrieves the level of the next node on the queue
    int levelOfNextPerson();
    
}
