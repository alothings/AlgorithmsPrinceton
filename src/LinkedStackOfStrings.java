/**
 * Created by alonso on 12/11/16
 * Implementation of Stacks using Linked Lists
 */
public class LinkedStackOfStrings
{
    private Node first = null;

    /**
     * Node class
     */
    private class Node
    {
        String item;
        Node next;
    }

    public boolean isEmpty()
    { return first == null; }

    public void push(String item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop()
    {
        String item = first.item;
        first = first.next;
        return item;
    }


    /**
     * Stack Test Client
     */
    public static void main(String[] args){
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        while(!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
    }
}

/**
 * Input: tobe.txt
 * to be or not to - be - - that - - - is
 */



