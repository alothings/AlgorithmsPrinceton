import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by alonso on 1/23/17.
 *
 *  A randomized queue is similar to a stack or queue, except that the
 *  item removed is chosen uniformly at random from items in the data
 *  structure. Create a generic data type RandomizedQueue that implements
 *  the following API:

 public class RandomizedQueue<Item> implements Iterable<Item> {
     public RandomizedQueue()                 // construct an empty randomized queue
     public boolean isEmpty()                 // is the queue empty?
     public int size()                        // return the number of items on the queue
     public void enqueue(Item item)           // add the item
     public Item dequeue()                    // remove and return a random item
     public Item sample()                     // return (but do not remove) a random item
     public Iterator<Item> iterator()         // return an independent iterator over items in random order
     public static void main(String[] args)   // unit testing (optional)
 }

 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int n = 0;

    /**
     * Initialize empty queue as array
     */
    public RandomizedQueue()
    {
        items = (Item[]) new Object[2];
        n = 0;
    }

    /**
     * Returns is queue empty?
     * @return boolean true if empty, false otherwise
     */
    public boolean isEmpty() { return n == 0; }

    public int size() { return n; }

    public void enqueue(Item item)
    {
        if (item == null) throw new NullPointerException();
        if (n == items.length) resize(2*items.length);
        items[n++] = item;
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = items[i];
        items = copy;
    }

    /**
     * Chooses element at random. Deletes it from array. Put last el of array in its place
     * @return popped element
     */
    public Item dequeue()
    {
        if (n <= 0) throw new NoSuchElementException();
        int index = StdRandom.uniform(0, n);
        Item temp = items[index];
        if (index != n-1)
            items[index] = items[--n];
        else
            n--;
        items[n] = null;
        if (n > 0 && n == items.length/4) resize(items.length/2);
        return temp;
    }

    public Item sample()
    {
        if (n <= 0) throw new NoSuchElementException();
        int index = StdRandom.uniform(n);
        return items[index];
    }

    public Iterator<Item> iterator() {
//        if (n == 0) return null;
        Item[] el = (Item[]) new Object[items.length];
        for (int i = 0; i < items.length; i++) {
            el[i] = items[i];
        }
        int tempN = n;

        return new ArrayIterator(el, tempN);
    }
//    public Iterator<Item> iterator() { return new ArrayIterator(); }

    private class ArrayIterator implements Iterator<Item>
    {
        private Item[] elements;
        private int currN;

        public ArrayIterator(Item[] items, int n)
        {
            elements = items;
            currN = n;
            if (currN > 1)
                StdRandom.shuffle(elements, 0, currN-1);
        }
        public boolean hasNext() { return currN > 0; }
        public void remove()     { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException("No such element");
            Item el = elements[currN - 1];
//            elements[currN - 1] = null;
            currN--;
            return el;
        }
    }
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> deck = new RandomizedQueue<Integer>();
        deck.enqueue(1);
        deck.enqueue(2);
        deck.enqueue(3);
        deck.enqueue(4);
        deck.enqueue(5);
        deck.enqueue(6);
        deck.enqueue(7);
        deck.enqueue(8);
        deck.enqueue(9);
        deck.enqueue(10);
//        deck.dequeue();
//        deck.dequeue();
//        deck.dequeue();
//        deck.dequeue();
//        deck.dequeue();
        for (Integer o : deck) {
            StdOut.println("\nOuter loop: " + o);
//            StdOut.println("Size: " + deck.size());

//            for (Integer i : deck) {
//                StdOut.print("Inner loop: " + i + " ");
//            }
        }
        StdOut.println("\nSize: " + deck.size());
//        for (int i = 0; i < deck.size(); i++){
//            StdOut.println(deck.items[i]);
//        }

    }
}
