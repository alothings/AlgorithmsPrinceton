import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by alonso on 12/12/16.
 * Implementation of Stacks using:
 *  - Generics
 *  - Linked List
 */
public class GenericStack<Item>
{
        private Node first = null;

        private class Node
        {
            Item item;
            Node next;
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
            Item item = first.item;
            first = first.next;
            return item;
        }


        /**
         * Stack Test Client
         */
        public static void main(String[] args) {
            GenericStack stack = new GenericStack();
            while (!StdIn.isEmpty()) {
                String s = StdIn.readString();
                if (s.equals("-"))
                    StdOut.print(stack.pop());
                else
                    stack.push(s);
            }
        }
/**
 * Input: tobe.txt
 * to be or not to - be - - that - - - is
 */




}
