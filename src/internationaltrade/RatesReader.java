
package internationaltrade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author PC
 */
public class RatesReader {

    private Hashtable<String, ArrayList<Rate>> rates;
    private String file;

    public RatesReader(String f) {
        this.rates = new Hashtable<>();
        this.file = f;
    }

    /**
     * reads the xml file, and stores rates data in hashtable
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public void readXml() throws ParserConfigurationException, SAXException, IOException {
        
        File fXmlFile = new File(file); //RATES-SAMPLES
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);      
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("rate");
        Node nNode;
        Rate tmpRate = null;    
        for (int temp = 0; temp < nList.getLength(); temp++) {
            nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;                
                //System.out.println("from = "+eElement.getElementsByTagName("from").item(0).getTextContent());
                //System.out.println("to = "+eElement.getElementsByTagName("to").item(0).getTextContent());
                //System.out.println("conversion = "+eElement.getElementsByTagName("conversion").item(0).getTextContent());
                
                tmpRate = new Rate(eElement.getElementsByTagName("from").item(0).getTextContent(),
                        eElement.getElementsByTagName("to").item(0).getTextContent(),
                        Double.parseDouble(eElement.getElementsByTagName("conversion").item(0).getTextContent()));
                
                //this.rates.add(tmpRate);
                if(!this.rates.containsKey(tmpRate.getFrom())){
                    this.rates.put(tmpRate.getFrom(), new ArrayList<>());
                }
                
                this.rates.get(tmpRate.getFrom()).add(tmpRate);
                
            }
        }
    }

    /**
     * gets data structure
     * @return 
     */
    public Hashtable<String, ArrayList<Rate>> getRates() {
        return rates;
    }
    /**
     * prints data. for testing
     */
    public void printRates(){
        
        Iterator it = this.rates.keySet().iterator();
        String str;
        ArrayList<Rate> r;
        while (it.hasNext()){
            str = (String)it.next();
            System.out.print(str+" --> ");
            r = this.rates.get(str);
            for(Rate ra : r){
                System.out.print(ra.toString());
            }System.out.println("");
        }
    }

    
}
