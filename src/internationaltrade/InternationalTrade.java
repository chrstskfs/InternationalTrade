
package internationaltrade;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author PC
 */
public class InternationalTrade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        TransReader myTransReader = null;
        RatesReader myRatesReader = null;

        //String fileTrans = "SAMPLE_TRANS.csv";
        //String fileRates = "RATES-SAMPLES.txt"; 
        String fileTrans = "TRANS.csv";
        String fileRates = "RATES.txt";

        String sku = "DM1182";

        try {
            myTransReader = new TransReader(fileTrans);
            myTransReader.readFile();

            myRatesReader = new RatesReader(fileRates);
            myRatesReader.readXml();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.toString());
        } catch (SAXException ex) {
            System.out.println(ex.toString());
        }

        
        Calculator calc = new Calculator(myRatesReader.getRates());
        calc.getAmountOfSku(myTransReader.getTrans().get(sku));
        System.out.println(calc.getSolution().toString());
    }

}
