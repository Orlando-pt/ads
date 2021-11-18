package pt.up.fe.exports;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
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

    /**
     * create the tree
     */

    /**
     * export using dot language
     */

     /**
      * export image
      */
    
}
