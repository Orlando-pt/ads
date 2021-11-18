package pt.up.fe.traversers;

import java.util.LinkedList;

import pt.up.fe.person.Person;

public class PersonsBreadthTraverser {

    private Person root;
    private LinkedList<Person> listOne;
    private LinkedList<Person> listTwo;
    private LinkedList<Person> currentList;

    public PersonsBreadthTraverser(Person root) {
        this.root = root;
        this.listOne = new LinkedList<>();
        this.listTwo = new LinkedList<>();
        this.currentList = this.listOne;
    }

    private void switchList() {
        if (this.currentList == this.listOne) this.currentList = this.listTwo;
        else this.currentList = this.listOne;
    }

    public boolean hasNextAtThisLevel() {
        
        return true;
    }

    // public 
    
}
