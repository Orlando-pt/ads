package pt.up.fe.exports;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;

import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;
import static guru.nidi.graphviz.model.Factory.*;

import pt.up.fe.person.Person;

public class DotExport {

    private List<Person> personList;

    public DotExport(List<Person> personList) {
        this.personList = personList;
    }

    public void createGraphHavingRoot(Person personRoot, String imageName) throws Exception {
        Graph g = graph("Family Tree of " + personRoot.getName()).directed()
        .graphAttr().with(Rank.dir(RankDir.TOP_TO_BOTTOM))
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

        LinkedList<ImmutablePair<Person, Person>> personsWaitingQueue = new LinkedList<>();
        personsWaitingQueue.add(new ImmutablePair<Person,Person>(root, null));
        
        ImmutablePair<Person, Person> currentNode = null;
        // loop to traverse all nodes of the tree
        while (!personsWaitingQueue.isEmpty()) {
            currentNode = personsWaitingQueue.pollFirst();

            // loop through all childrens
            for (Person children : currentNode.left.getChildren()) {

                ImmutablePair<Person, Person> child = new ImmutablePair<Person,Person>(children, currentNode.left);
                // add child to linked list
                personsWaitingQueue.add(child);

                // add link between father and son
                linkSources.add(
                    node(
                        computeNodeString(currentNode)
                    )
                    .link(
                        computeNodeString(child)
                    )
                );
            }
            
        }

        return linkSources;
    }

    private String computeNodeString(ImmutablePair<Person, Person> nodePair) {
        Person node = nodePair.left;
        Person father = nodePair.right;

        switch (node.getNumberOfMarriages()) {
            case 0:
                return node.getName() + this.addIfParentHasMultipleMarriages(node, father);
            case 1:
                return node.getName() + " and " + node.getPartner().getName() +
                                this.addIfParentHasMultipleMarriages(node, father);
            default:
                List<String> partnerNames = node.getPartners()
                                                .stream().map((person) -> person.getName())
                                                    .collect(Collectors.toList());

                return node.getName() + " and [" + String.join(", ", partnerNames) + "]" +
                            this.addIfParentHasMultipleMarriages(node, father);
        }
    }

    private String addIfParentHasMultipleMarriages(Person child, Person parent) {
        if (parent == null)
            return "";
        
        if (!parent.marriedMoreThanOnce())
            return "";

        return "\nFrom marriage with: " + child.getOppositeParent(parent).getName();

    }

}
