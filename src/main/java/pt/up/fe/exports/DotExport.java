package pt.up.fe.exports;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Arrow.DirType;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.LinkTarget;
import guru.nidi.graphviz.model.MutableGraph;
import static guru.nidi.graphviz.model.Factory.*;
import pt.up.fe.person.Person;

public class DotExport {

    private List<Person> personList;

    public DotExport(List<Person> personList) {
        this.personList = personList;
    }

    public void experimentGraphViz() throws Exception{
        List<LinkTarget> children = Arrays.asList(mutNode("d"), mutNode("e"), mutNode("f"));

        MutableGraph g = mutGraph("example1").setDirected(true).add(
                mutNode("a").add(Color.RED).addLink(mutNode("b"), mutNode("c"))).add(
                    mutNode("b").addLink(children)
                );

        Graphviz.fromGraph(g).width(200).render(Format.PNG).toFile(new File("example/ex1m.png"));
    }

    public void experimentNumber2() throws Exception {
        Graph g = graph("example1").directed()
        .graphAttr().with(Rank.dir(RankDir.LEFT_TO_RIGHT))
        .nodeAttr().with(Font.name("arial"))
        .linkAttr().with("class", "link-class")
        .with(
                node("a").with(Color.RED).link(node("b")),
                node("a").link(node("d")),
                node("a").link(node("z")),
                node("b").link(node("c"))
            );
        Graphviz.fromGraph(g).height(100).render(Format.PNG).toFile(new File("example/ex1.png"));

    }

    public void createGraphHavingRoot(Person personRoot, String imageName) throws Exception {
        Graph g = graph("Family Tree of " + personRoot.getName()).directed()
        .graphAttr().with(Rank.dir(RankDir.TOP_TO_BOTTOM))
        .nodeAttr().with(Font.name("arial"))
        .linkAttr().with("class", "link-class")
        .with(
                getLinkSourcesTree(personRoot)
            );
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new File("graphs/" + imageName + ".png"));
    }

    /**
     * create the tree
     */

    public List<LinkSource> getLinkSourcesTree(Person root) {
        List<LinkSource> linkSources = new ArrayList<>();

        LinkedList<Person> personsWaitingQueue = new LinkedList<>();
        personsWaitingQueue.add(root);
        
        Person currentNode = null;
        // loop for all the childrens
        while (!personsWaitingQueue.isEmpty()) {
            currentNode = personsWaitingQueue.pollFirst();

            // add children
            personsWaitingQueue.addAll(currentNode.getChildren());

            // make links to all the childrens
            for (Person children : currentNode.getChildren())
                linkSources.add(
                    node(
                        currentNode.getNumberOfMarriages() == 1 ? 
                        currentNode.getName() + " and " + currentNode.getPartner().getName() :
                        currentNode.getName()
                    )
                    .link(
                        node(
                            children.getNumberOfMarriages() == 1 ?
                            children.getName() + " and " + children.getPartner().getName() :
                            children.getName()
                        )
                    )
                );
            
        }


        return linkSources;
    }

    /**
     * export using dot language
     */

     /**
      * export image
      */
    
}
