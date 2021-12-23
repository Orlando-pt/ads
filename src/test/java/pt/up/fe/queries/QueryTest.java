package pt.up.fe.queries;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.up.fe.person.Person;

public class QueryTest {

    private Person root;
    private QueryInvoker queryInvoker;
    private List<Person> listOfPeople;

    @BeforeEach
    void setUp() {
        this.queryInvoker = new QueryInvoker();
        this.listOfPeople = new ArrayList<>();
        this.generatePersonData();
    }

    @Test
    void testChildrenQuery() {
        QueryResultPersonList queryReceiver = new QueryResultPersonList();
        ChildrenQuery query = new ChildrenQuery(queryReceiver, this.root);
        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        Assertions.assertThat(
            queryReceiver.getPersonList()
        ).hasSize(2).extracting(Person::getName)
            .contains("Breno", "Catia");
    }

    @Test
    void testGrandChildrenQuery() {
        QueryResultPersonList queryReceiver = new QueryResultPersonList();
        GrandChildrenQuery query = new GrandChildrenQuery(queryReceiver, this.root);
        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        Assertions.assertThat(
            queryReceiver.getPersonList()
        ).hasSize(4).extracting(Person::getName)
            .containsOnly("Sofia", "Hugo", "Carolina", "Orlando");
    }

    @Test
    void testGrandGrandChildrenQuery() {
        QueryResultPersonList queryReceiver = new QueryResultPersonList();
        GrandGrandChildrenQuery query = new GrandGrandChildrenQuery(queryReceiver, this.root);
        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        Assertions.assertThat(
            queryReceiver.getPersonList()
        ).hasSize(4).extracting(Person::getName)
            .containsOnly("Joao", "Ana", "Joana", "Ana Grila");
    }

    @Test
    void testSpecifiedPersonAttributesQuery_firstNameOnly() {
        QueryResultPersonList queryReceiver = new QueryResultPersonList();
        SpecifiedPersonAttributes personAttributes = new SpecifiedPersonAttributes();

        // test if the name is not exact
        personAttributes.setName(
            new NameAttribute("Ana")
        );
        FilterPersonListByAttributes query = new FilterPersonListByAttributes(
            queryReceiver,
            personAttributes,
            this.listOfPeople
        );
        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        Assertions.assertThat(
            queryReceiver.getPersonList()
        ).extracting(Person::getName).containsOnly("Ana", "Ana Grila");

        // test exact name
        personAttributes.setName(
            new NameAttribute("Ana", true)
        );

        queryReceiver.emptyList();
        query = new FilterPersonListByAttributes(
            queryReceiver,
            personAttributes,
            this.listOfPeople
        );

        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        Assertions.assertThat(
            queryReceiver.getPersonList()
        ).extracting(Person::getName).containsOnly("Ana");
    }

    @Test
    void testSpecifiedPersonAttributesQuery_allNameOnly() {
        QueryResultPersonList queryReceiver = new QueryResultPersonList();
        SpecifiedPersonAttributes personAttributes = new SpecifiedPersonAttributes();
        personAttributes.setMiddleName(
            new NameAttribute("Jorge")
        );

        FilterPersonListByAttributes query = new FilterPersonListByAttributes(
            queryReceiver,
            personAttributes,
            this.listOfPeople
        );

        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        Assertions.assertThat(
            queryReceiver.getPersonList()
        ).extracting(Person::getMiddleName).containsOnly("Jorge Magalhães", "Jorge Ribeiro");

        queryReceiver.emptyList();
        personAttributes.setLastName(
            new NameAttribute("Gonçalves", true)
        );
        
        query = new FilterPersonListByAttributes(
            queryReceiver,
            personAttributes,
            this.listOfPeople
        );

        this.queryInvoker.setCommand(query);
        this.queryInvoker.executeCommand();

        // System.out.println(this.queryReceiver.getPersonList());

        Assertions.assertThat(
            queryReceiver.getPersonList()
        ).extracting(Person::getName).containsOnly("Orlando");
    }
    

    private void generatePersonData() {
        Person breno = new Person();
        breno.setName("Breno");

        Person catia = new Person();
        catia.setName("Catia");

        Person diogo = new Person();
        diogo.setName("Diogo");
        diogo.setMiddleName("Jorge Magalhães");

        Person sofia = new Person();
        sofia.setName("Sofia");

        Person hugo = new Person();
        hugo.setName("Hugo");

        Person carolina = new Person();
        carolina.setName("Carolina");

        Person orlando = new Person();
        orlando.setName("Orlando");
        orlando.setMiddleName("Jorge Ribeiro");
        orlando.setLastName("Gonçalves");

        Person joao = new Person();
        joao.setName("Joao");

        Person ana = new Person();
        ana.setName("Ana");

        Person anaGrila = new Person();
        anaGrila.setName("Ana Grila");

        Person joana = new Person();
        joana.setName("Joana");
        joana.setLastName("Gonçalves");

        Person goncalves = new Person();
        goncalves.setLastName("Gonçalves");

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
        this.listOfPeople.add(goncalves);
    }
}
