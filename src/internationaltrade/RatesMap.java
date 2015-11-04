
package internationaltrade;

import model.Rate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

/**
 * Construct Pair-like structure for each conversion for every currency to USD.
 * @author PC
 */
public class RatesMap {

    // matrix representation of rates
    double[][] rates;
    private Hashtable<String, Double> toUSD;
    private Hashtable<String, Integer> curr;

    public RatesMap() {
        toUSD = new Hashtable<>();        
    }

    /**
     * constructs the matrix graph for finding the paths from each curency to USD
     * @param r 
     */
    public void build(Hashtable<String, ArrayList<Rate>> r) {

        rates = new double[r.size()][r.size()];
        curr = new Hashtable<>(r.size());

        // create a mapping of currency and position in matrix        
        Iterator i = r.keySet().iterator();
        int index = 0;
        while (i.hasNext()) {
            curr.put((String) i.next(), index);
            index++;
        }

        /**
         * add the edges of the matrix - graph, where conection is
         * represented by the muktiplication number for each converion
         */
        i = r.keySet().iterator();
        String tmp = "";
        ArrayList<Rate> rl = null;
        while (i.hasNext()) {
            tmp = (String) i.next();
            rl = r.get(tmp);
            for (Rate x : rl) {
                rates[curr.get(x.getFrom())][curr.get(x.getTo())] = x.getConversion();
            }
        }
        constrConvs2USD(r);
        /*
        Iterator myIt = toUSD.keySet().iterator();
        String mytmp = "";
        while (myIt.hasNext()) {
            mytmp = (String) myIt.next();
            System.out.print(mytmp + " => ");
            System.out.println(toUSD.get(mytmp));
        }
        */

    }

    /**
     * constructs the pairing for the conversions
     * @param r 
     */
    public void constrConvs2USD(Hashtable<String, ArrayList<Rate>> r) {
        Iterator itCurr = curr.keySet().iterator();
        String tmp = "";
        while (itCurr.hasNext()) {
            tmp = (String) itCurr.next();
            if (!tmp.equalsIgnoreCase("USD")) {
                toUSD.put(tmp, search(tmp));
            }
        }

    }

    int[][] visited;
    Stack<String> stack = new Stack<>();
    double d;

    /**
     * Applies a DFS-like search to matrix multiplying the edges value for rach path to USD, 
     * as we know that every currency can be converted to USD
     * @param s
     * @return  
     */
    public double search(String s) {
        d = 1;
        visited = new int[rates[1].length][rates[1].length]; // all positions are = 0, unvisited
        stack.clear();
        String tmpCurName;
        stack.push(s); // push from currency in stack
        while (!stack.isEmpty()) {
            tmpCurName = stack.pop();
            if (rates[curr.get(tmpCurName)][curr.get("USD")] != 0) {
                d = d * rates[curr.get(tmpCurName)][curr.get("USD")];
            } 
            else {
                for (int j = 0; j < rates[0].length; j++) {
                    if ((rates[curr.get(tmpCurName)][j] != 0) && (visited[curr.get(tmpCurName)][j] != 1)) {
                        d = d * rates[curr.get(tmpCurName)][j];
                        stack.push(find(j));
                        visited[curr.get(s)][j] = 1;
                        break;
                    }
                }
            }
        }
        resetvisited();
        return d;
    }

    private void print() {
        for (int i = 0; i < rates[0].length; i++) {
            for (int j = 0; j < rates[1].length; j++) {
                System.out.print(rates[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * finds the currency name for each integer from the mapping 
     * @param i
     * @return 
     */
    private String find(int i) {
        Iterator t = curr.keySet().iterator();
        String s = "";
        while (t.hasNext()) {
            s = (String) t.next();
            if (i == curr.get(s)) {
                break;
            }
        }

        return s;
    }

    private void resetvisited() {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[1].length; j++) {
                visited[i][j] = 0;
            }
        }
    }

    public Hashtable<String, Double> getToUSD() {
        return toUSD;
    }

}
