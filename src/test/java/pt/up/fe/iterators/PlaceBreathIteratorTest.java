package pt.up.fe.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.up.fe.places.CompoundPlace;
import pt.up.fe.places.Parish;
import pt.up.fe.places.Place;

public class PlaceBreathIteratorTest {

    private PlaceIteratorInterface<Place> iterator;

    @BeforeEach
    void setUp() {
        Place root = this.buildPlacesTree();
        iterator = root.createIterator();
    }

    @Test
    void testHasNext() {
        // just advance one position
        iterator.next();

        assertTrue(
            iterator.hasNext()
        );
    }

    @Test
    void testNext() {
        // if the iterator advances one position
        // then it should return the district of Lisboa
        iterator.next();

        assertEquals(
            "Lisboa",
            iterator.next().getName()
        );

        // more two nexts to avoid the concelhos
        iterator.next();
        iterator.next();

        // now it should return Benfica
        assertEquals(
            "Benfica",
            iterator.next().getName()
        );
    }

    private Place buildPlacesTree() {
        CompoundPlace pais = new CompoundPlace("Portugal");
        CompoundPlace distrito = new CompoundPlace("Lisboa");
        CompoundPlace concelho1 = new CompoundPlace("Lisboa");
        CompoundPlace concelho2 = new CompoundPlace("Sintra");
        Parish freguesia1 = new Parish("Benfica");
        freguesia1.setArea(25d);
        Parish freguesia2 = new Parish("Parque das Nações");
        freguesia2.setArea(25d);
        Parish freguesia3 = new Parish("Mem Martins");
        freguesia3.setArea(75d);
        Parish freguesia4 = new Parish("Mercês");
        freguesia4.setArea(100d);

        concelho1.addChild(freguesia1);
        concelho1.addChild(freguesia2);
        concelho2.addChild(freguesia3);
        concelho2.addChild(freguesia4);

        distrito.addChild(concelho1);
        distrito.addChild(concelho2);

        pais.addChild(distrito);
        return pais;
    }
}
