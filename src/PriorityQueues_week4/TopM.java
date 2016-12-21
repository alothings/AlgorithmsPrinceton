/**
 * Created by alonso on 12/19/16.
 * Given an integer M from the command line and an input stream
 * where each line contains a trans-
 action, this MinPQ client prints the M lines whose numbers are
 the highest.
 */
public class TopM {

//    public static void main(String[] args)
//    {
//        // Print the top M lines in the input stream
//        int M = Integer.parseInt(args[0]);
//        MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);
//        while (StdIn.hasNextLine())
//        {   // Create an entry from the next line and put in PQ
//            pq.insert(new Transaction(StdIn.readLine()));
//            if (pq.size() > M)
//                pq.delMin();
//        }
//        Stack<Transaction> stack = new Stack<Transaction>();
//        while (!pq.isEmpty()) stack.push(pq.delMin());
//        for (Transaction t : stack) StdOut.println(t);
//    }
}
