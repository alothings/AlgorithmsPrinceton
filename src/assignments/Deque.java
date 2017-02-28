import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by alonso on 1/23/17.
 * Assignment of week 2 of the class Algorithms part 1, Princeton, Coursera
 *
 * Create a generic data type Deque that implements the following API:

 public class Deque<Item> implements Iterable<Item> {
     public Deque()                           // construct an empty deque
     public boolean isEmpty()                 // is the deque empty?
     public int size()                        // return the number of items on the deque
     public void addFirst(Item item)          // add the item to the front
     public void addLast(Item item)           // add the item to the end
     public Item removeFirst()                // remove and return the item from the front
     public Item removeLast()                 // remove and return the item from the end
     public Iterator<Item> iterator()         // return an iterator over items in order from front to end
     public static void main(String[] args)   // unit testing (optional)
 }
 *
 */

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Iterator<Item> iterator() { return new ListIterator(first); }

    private class ListIterator implements Iterator<Item>
    {
        private Node current;

        public ListIterator(Node initialNode) {
            current = initialNode;
        }

        public boolean hasNext() { return current != null; }
        public void remove()     { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (current == null) throw new NoSuchElementException("No such element");
            Item item = current.item;
            current   = current.next;
            return item;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("HERE");
        Node t = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            first.next = null;
            first.prev = null;
            last = first;
        }
        else {
            first.next = t;
            t.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node t = new Node();
        t.item = item;
        t.next = null;
        t.prev = last;
        if (isEmpty()) {
            last = t;
            first = t;
        }
        else {
            last.next = t;
        }
        last = t;
        size++;
    }

    public Item removeFirst() {
        if (first == null) throw new NoSuchElementException("Deque is empty.");
        Item item = first.item;
        if (size() > 1) {
            first = first.next;
            first.prev = null;
        }
        else {
            first = null;
            last = null;
        }
        size--;
        return item;

    }
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty.");
//        StdOut.println("Here");
        Item item = last.item;
        if (size() > 1) {
            last = last.prev;
            last.next = null;
        }
        else {
            first = null;
            last = null;
        }
        size--;
        return item;
    }

    public static void main(String[] args)
    {
        Deque<String> deck = new Deque<String>();
        deck.addFirst("3");
        deck.addFirst("2");
        deck.addFirst("1");
        deck.addLast("4");
        deck.addLast("5");

//        for(int i = 0; i < deck.size(); i++){
//            StdOut.println(deck.first.item + ", " + deck.last.item);
//            deck.first = deck.first.next;
//        }
//        deck.removeFirst();
        deck.removeFirst();
//        deck.removeFirst();
//        deck.removeFirst();
//        deck.removeFirst();
//        deck.removeLast();
        deck.removeLast();
//        for(int i = 0; i < deck.size(); i++) {
//            StdOut.println(deck.first.item + ", " + deck.last.item);
//            deck.first = deck.first.next;
//            StdOut.println(deck.size());
//        }

        for (String s: deck) {
            StdOut.println(s);
        }
        StdOut.println("Size: " + deck.size());

    }
}
