import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by alonso on 12/12/16.
 * Linked List implementation of Queues
 */
public class LinkedQueueOfStrings
{
    private Node first, last;

    private class Node
    {
        String item;
        Node next;
    }

    public boolean isEmpty()
    { return first == null; }

    public String dequeue()
    {
        if(isEmpty()) return "Empty";
        String item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }

    public void enqueue(String item)
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
    }

    /**
     * Queue Test Client
     */
    public static void main(String[] args){
        LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
        while(!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(queue.dequeue());
            else
                queue.enqueue(s);
//                StdOut.print("Pushing: " + s + "\n");
        }
    }
}
