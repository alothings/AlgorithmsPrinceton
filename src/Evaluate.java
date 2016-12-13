/**
 * Created by alonso on 12/12/16.
 * Implementation of Dijkstra Two-Stack algorithm
 * to evaluate operations
 */
public class Evaluate {
    public static void main(String[] args)
    {
        StdOut.println("Beginning\n");
//        StdOut.println(args);
        int i = 4;

        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if ( s.equals("q")){
                StdOut.println("Quitting");
                break;
            }
            StdOut.print("s: " + s + "\n");
            if (!ops.isEmpty()) StdOut.print("ops: " + ops.peek() + "\n");
            if (!ops.isEmpty()) StdOut.print("vals: " + vals.peek() + "\n");
            if      (s.equals("("));
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals(")"))
            {
                StdOut.print("Operation: ops: " + ops.peek() + "\n");
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
            }
            else vals.push(Double.parseDouble(s));
            i--;

        }
        StdOut.println("vals: " + vals.peek() + "\n");
        StdOut.println(vals.pop());
    }
}
//(1+((2+3)*(4*5)))

