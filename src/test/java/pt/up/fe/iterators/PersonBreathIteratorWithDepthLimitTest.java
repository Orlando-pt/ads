package pt.up.fe.iterators;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.up.fe.person.Person;

public class PersonBreathIteratorWithDepthLimitTest {
    private PersonBreathIteratorWithDepthLimit iterator;

    @BeforeEach
    void setup() {
        Person root = this.buildGenealogyTree();

        // ignore all the great grandsons of root
        this.iterator = new PersonBreathIteratorWithDepthLimit(root, 2);
    }
    @Test
    void testNext() {
        // check that the iterator does not passed to level 3
        while (this.iterator.hasNext())
            assertNotEquals(
                3,
                this.iterator.next().left
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

        // add one that is after the treshold
        // after this comment all the childrens are greater than 2
        Person joao = new Person();
        joao.setName("Joao");

        sofia.addChild(joao);

        return diogo;
    }
}
