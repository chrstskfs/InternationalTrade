
package internationaltrade;

import model.Trans;
import model.Amount;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author PC
 */
public class TransReader {

    private Hashtable<String,ArrayList<Trans>> trans;
    private BufferedReader reader;
    private String file;

    public TransReader(String f) {
        this.trans = new Hashtable<String, ArrayList<Trans>>();
        this.reader = null;
        this.file = f;
    }

    /*
    reads the trsns file and stores data in a hashtable
    */
    public void readFile() throws FileNotFoundException, IOException {
        String sCurrentLine;
        String[] arr = null;
        Trans tmpTran = null;
        reader = new BufferedReader(new FileReader(file)); //TRANS-SAMPLES

        reader.readLine();
        while ((sCurrentLine = reader.readLine()) != null) {
            arr = sCurrentLine.split(",");
            tmpTran = new Trans(arr[0], arr[1], new Amount(Double.parseDouble(arr[2].split(" ")[0]), arr[2].split(" ")[1]));
            
            if(!this.trans.containsKey(tmpTran.getSku())){
                    this.trans.put(tmpTran.getSku(), new ArrayList<>());
                }
                
                this.trans.get(tmpTran.getSku()).add(tmpTran);            
        }
    }

    /**
     * accesses th e data structure    
     */
    public Hashtable<String, ArrayList<Trans>> getTrans() {
        return trans;
    }
    /**
     * auxiliary method . Prints the data. for testing
     */
    public void printTrans(){
        Iterator it = this.trans.keySet().iterator();
        String str;
        ArrayList<Trans> r;
        while (it.hasNext()){
            str = (String)it.next();
            System.out.print(str+" --> ");
            r = this.trans.get(str);
            for(Trans tr : r){
                System.out.print(tr.toString());
            }System.out.println("");
        }
    }
}
