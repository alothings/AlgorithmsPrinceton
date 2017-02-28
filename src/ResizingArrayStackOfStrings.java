import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by alonso on 12/12/16.
 */
public class ResizingArrayStackOfStrings
{
    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings()
    { s = new String[1]; }

    public boolean isEmpty()
    { return N==0; }

    /**
     * Push method that doubles array size when capacity
     * is reached. Amortizing Push to O(N) in worst case
     * and O(1) in best and average case
     */
    public void push(String item)
    {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    /**
     * Copying elements to an array of double size
     * @param capacity: doubled size of current array
     */
    private void resize(int capacity)
    {
        String[] copy = new String[capacity];
        for(int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }

    /**
     * Pop method with 2 considerations:
     * 1: Sets unused element to null for Garbage Collection
     * 2: Halves size of array when only 1/4 is used
     */
    public String pop()
    {
        String item = s[--N];
        s[N] = null;        // Helping Garbage Collection
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }

    /**
     * Stack Test Client
     */
    public static void main(String[] args){
        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
        while(!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
//                StdOut.print("Pushing: " + s + "\n");
        }
    }

}

/**
 * Input: tobe.txt
 * to be or not to - be - - that - - - is
 *
 */