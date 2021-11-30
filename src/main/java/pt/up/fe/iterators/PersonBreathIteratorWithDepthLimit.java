package pt.up.fe.iterators;

import org.apache.commons.lang3.tuple.ImmutablePair;

import pt.up.fe.person.Person;

public class PersonBreathIteratorWithDepthLimit extends PersonBreathIterator{

    private int maxDepthLimit;

    public PersonBreathIteratorWithDepthLimit(Person root, int maxDepthLimit) {
        super(root);
        this.maxDepthLimit = maxDepthLimit;
    }

    // retrieves the next person but only updates the queue with its
    // children if the level is still adequate
    @Override
    public ImmutablePair<Integer, Person> next() {
        ImmutablePair<Integer, Person> currentNode = super.getWaitingQueue().pollFirst();

        if (currentNode.left + 1 <= this.maxDepthLimit) 
            this.addChildrenToWaitingQueue(currentNode);

        return currentNode;
    }
    
}
