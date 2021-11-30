package pt.up.fe.iterators;

import java.util.LinkedList;

import org.apache.commons.lang3.tuple.ImmutablePair;

import pt.up.fe.person.Person;

public class PersonBreathIterator implements PersonIteratorInterface<ImmutablePair<Integer, Person>> {
    
    /**
     * the linked list contain a tuple with the level and the person
     * the level is referent to where in the tree the person is placed
     * 
     * for example, if we want to get the grandsons of a person
     * those nodes should have level == 2
     * because we had to go trough all the root childrens
     * and then get all it's childrens
     */
    private LinkedList<ImmutablePair<Integer, Person>> waitingNodeQueue;

    public PersonBreathIterator(Person root) {
        this.waitingNodeQueue = new LinkedList<>();
        this.waitingNodeQueue.add(new ImmutablePair<Integer,Person>(0, root));
    }

    @Override
    public boolean hasNext() {
        return !this.waitingNodeQueue.isEmpty();
    }

    @Override
    public ImmutablePair<Integer, Person> next() {
        ImmutablePair<Integer, Person> currentPerson = this.waitingNodeQueue.pollFirst();
        currentPerson.right.getChildren().forEach(
            (child) -> this.waitingNodeQueue.add(
                new ImmutablePair<Integer,Person>(currentPerson.left + 1, child)
            )
        );
        return currentPerson;
    }
    
}
