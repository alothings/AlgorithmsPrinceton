/**
 * Created by alonso on 12/12/16.
 * Stack implementation using:
 *  - Linked List
 *  - Iterator (to allow client to use for-each)
 *  - Generics
 */
import java.util.EmptyStackException;
import java.util.Iterator;

/* Iterable Interface requires a method that returns an iterable*/
public class Stack<Item> implements Iterable<Item>
{
    private Node first = null;

    private class Node
    {
        Item item;
        Node next;
    }

    /** Method that returns ITERATOR     */
    public Iterator<Item> iterator() { return new ListIterator(); }

    /** Iterator class that has methods hasNext() and next() */
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() { return current != null; }
        public void remove()     { /* Not supported */}
        public Item next()
        {
            Item item = current.item;
            current   = current.next;
            return item;
        }
    }

    public boolean isEmpty()
    { return first == null; }

    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop()
    {
        if (first == null) throw new EmptyStackException();
        Item item = first.item;
        first = first.next;
        return item;
    }

    public Item peek()
    {
        if (first == null) throw new EmptyStackException();
        return first.item;
    }


    /**
     * Stack Test Client
     */
//    public static void main(String[] args) {
//        Stack stack = new Stack();
//        while (!StdIn.isEmpty()) {
//            String s = StdIn.readString();
//            if (s.equals("-"))
//                StdOut.print(stack.pop());
//            else
//                stack.push(s);
//        }
//    }
/**
 * Input: tobe.txt
 * to be or not to - be - - that - - - is
 */
}
