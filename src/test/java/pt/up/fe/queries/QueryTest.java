package pt.up.fe.queries;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.up.fe.person.Person;

public class QueryTest {

    private Person root;
    private QueryResultPersonList queryReceiver;
    private QueryInvoker queryInvoker;
    private List<Person> listOfPeople;

    @BeforeEach
    void setUp() {
        this.generatePersonData();
        this.queryReceiver = new QueryResultPersonList();
        this.queryInvoker = new QueryInvoker();
        this.listOfPeople = new ArrayList<>();
    }

    @Test
    void testChildrenQuery() {
        ChildrenQuery query = new ChildrenQuery(this.queryReceiver, this.root);
        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        Assertions.assertThat(
            this.queryReceiver.getPersonList()
        ).hasSize(2).extracting(Person::getName)
            .contains("Breno", "Catia");
    }

    @Test
    void testGrandChildrenQuery() {
        GrandChildrenQuery query = new GrandChildrenQuery(this.queryReceiver, this.root);
        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        Assertions.assertThat(
            this.queryReceiver.getPersonList()
        ).hasSize(4).extracting(Person::getName)
            .containsOnly("Sofia", "Hugo", "Carolina", "Orlando");
    }

    @Test
    void testGrandGrandChildrenQuery() {
        GrandGrandChildrenQuery query = new GrandGrandChildrenQuery(this.queryReceiver, this.root);
        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        Assertions.assertThat(
            this.queryReceiver.getPersonList()
        ).hasSize(3).extracting(Person::getName)
            .containsOnly("Joao", "Ana", "Joana");
    }
    

    private void generatePersonData() {
        Person breno = new Person();
        breno.setName("Breno");

        Person catia = new Person();
        catia.setName("Catia");

        Person diogo = new Person();
        diogo.setName("Diogo");

        Person sofia = new Person();
        sofia.setName("Sofia");

        Person hugo = new Person();
        hugo.setName("Hugo");

        Person carolina = new Person();
        carolina.setName("Carolina");

        Person orlando = new Person();
        orlando.setName("Orlando");

        Person joao = new Person();
        joao.setName("Joao");

        Person ana = new Person();
        ana.setName("Ana");

        Person anaGrila = new Person();
        anaGrila.setName("Ana Grila");

        Person joana = new Person();
        joana.setName("Joana");

        this.root = diogo;
        diogo.addChild(breno);
        diogo.addChild(catia);

        breno.addChild(sofia);
        breno.addChild(hugo);

        catia.addChild(carolina);
        catia.addChild(orlando);

        sofia.addChild(joao);
        hugo.addChild(ana);
        hugo.addChild(joana);

        carolina.addChild(anaGrila);

        this.listOfPeople.add(diogo);
        this.listOfPeople.add(breno);
        this.listOfPeople.add(catia);
        this.listOfPeople.add(sofia);
        this.listOfPeople.add(hugo);
        this.listOfPeople.add(carolina);
        this.listOfPeople.add(orlando);
        this.listOfPeople.add(joao);
        this.listOfPeople.add(ana);
        this.listOfPeople.add(joana);
        this.listOfPeople.add(anaGrila);
    }
}
