package pt.up.fe.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.up.fe.person.Person;

public class PersonBreathIteratorTest {

    private PersonIteratorInterface<ImmutablePair<Integer, Person>> iterator;

    @BeforeEach
    void setUp() {
        Person root = this.buildGenealogyTree();
        iterator = root.createIterator();
    }

    @Test
    void testHasNext() {
        // true because iterator has the root node in the queue
        assertTrue(
            iterator.hasNext()
        );
    }

    @Test
    void testNext() {
        // the first node returned should be diogo and level equal to 0
        ImmutablePair<Integer, Person> node = iterator.next();
        assertEquals(
            0,
            node.left
        );

        assertEquals(
            "Diogo",
            node.right.getName()
        );

        // the second node is breno
        node = iterator.next();
        assertEquals(
            1,
            node.left
        );

        assertEquals(
            "Breno",
            node.right.getName()
        );
    }

    @Test
    void testLevelOfNextPerson() {
        // make the iterator pass to second level
        for (int i = 0; i < 4; i++)
            this.iterator.next();

        assertEquals(
            2,
            this.iterator.levelOfNextPerson()
        );
    }

    private Person buildGenealogyTree() {
        Person breno = new Person();
        Person catia = new Person();
        breno.setName("Breno");
        catia.setName("Catia");

        Person diogo = new Person();
        Person sofia = new Person();
        diogo.setName("Diogo");
        sofia.setName("Sofia");

        Person hugo = new Person();
        Person carolina = new Person();
        hugo.setName("Hugo");
        carolina.setName("Carolina");

        Person orlando = new Person();
        orlando.setName("Orlando");

        diogo.addChild(breno);
        diogo.addChild(catia);

        breno.addChild(sofia);
        breno.addChild(hugo);

        catia.addChild(carolina);
        catia.addChild(orlando);

        return diogo;
    }
}
