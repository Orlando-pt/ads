package pt.up.fe.exports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.up.fe.events.Birth;
import pt.up.fe.events.Marriage;
import pt.up.fe.person.Gender;
import pt.up.fe.person.Person;

public class DotExportTest {

    private DotExport dotExport;
    private List<Person> listOfPeople;

    public DotExportTest() {
        listOfPeople = new ArrayList<>();
    }

    @BeforeEach
    void setUp() {
        this.generateFamilyTree();
        dotExport = new DotExport(this.listOfPeople);
    }

    @Test
    void createGraphImageTest() throws Exception{
        this.dotExport.createGraphHavingRoot(this.listOfPeople.get(0), "brenoOurFather");
    }

    private void generateFamilyTree() {
        Person breno = new Person();
        Person catia = new Person();
        breno.setName("Breno");
        catia.setName("Catia");
        Marriage brenoAndCatia = new Marriage(breno, catia);
        breno.addEvent(brenoAndCatia);
        catia.addEvent(brenoAndCatia);

        Person diogo = new Person();
        Person sofia = new Person();
        diogo.setName("Diogo");
        sofia.setName("Sofia");
        Marriage diogoAndSofia = new Marriage(diogo, sofia);
        diogo.addEvent(diogoAndSofia);
        sofia.addEvent(diogoAndSofia);

        Person hugo = new Person();
        Person carolina = new Person();
        hugo.setName("Hugo");
        carolina.setName("Carolina");
        Marriage hugoAndCarolina = new Marriage(hugo, carolina);
        hugo.addEvent(hugoAndCarolina);
        carolina.addEvent(hugoAndCarolina);

        Person orlando = new Person();

        orlando.setName("Orlando");
        orlando.setGender(Gender.MALE);
        breno.getChildren().addAll(Arrays.asList(diogo, hugo, orlando));

        Person antero = new Person();
        Person deolinda = new Person();
        Person bruno = new Person();
        Person francisco = new Person();

        antero.setName("Antero");
        deolinda.setName("Deolinda");
        bruno.setName("Bruno");
        francisco.setName("Francisco");

        diogo.addChild(antero);
        hugo.getChildren().addAll(Arrays.asList(deolinda, bruno, francisco));

        // The playboy life of Orlando
        Person cristiana = new Person();
        cristiana.setName("Cristiana");
        cristiana.setGender(Gender.FEMALE);
        Marriage orlandoAndCristiana = new Marriage(orlando, cristiana);
        orlando.addEvent(orlandoAndCristiana);
        cristiana.addEvent(orlandoAndCristiana);

        Person catarina = new Person();
        catarina.setName("Catarina");
        catarina.setGender(Gender.FEMALE);
        Marriage orlandoAndCatarina = new Marriage(orlando, catarina);
        orlando.addEvent(orlandoAndCatarina);
        catarina.addEvent(orlandoAndCatarina);

        // Childrens from the marriage with Cristiana
        Person joaozinnho = new Person();
        joaozinnho.setName("Joaozinho");
        orlando.addChild(joaozinnho);
        cristiana.addChild(joaozinnho);
        Birth birthOfJoaozinho = new Birth(orlando, cristiana, joaozinnho);
        orlando.addEvent(birthOfJoaozinho);
        cristiana.addEvent(birthOfJoaozinho);
        joaozinnho.addEvent(birthOfJoaozinho);


        // Childrens from the marriage with Catarina
        Person trambolho = new Person();
        trambolho.setName("Trambolho");
        orlando.addChild(trambolho);
        cristiana.addChild(trambolho);
        Birth birthOfTrambolho = new Birth(orlando, catarina, trambolho);
        orlando.addEvent(birthOfTrambolho);
        catarina.addEvent(birthOfTrambolho);
        trambolho.addEvent(birthOfTrambolho);

        this.listOfPeople = Arrays.asList(
            breno,
            catia,
            diogo,
            sofia,
            hugo,
            carolina,
            orlando,
            antero,
            deolinda,
            bruno,
            francisco
        );
    }
}
