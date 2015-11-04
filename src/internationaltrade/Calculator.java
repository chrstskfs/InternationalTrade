
package internationaltrade;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author PC
 */
public class Calculator {

    private ArrayList<Amount> amountList;
    private Hashtable<String, ArrayList<Rate>> ratesList;
    private RatesMap g;
    private Hashtable<String, Double> toUSD;

    
    public Calculator(Hashtable<String, ArrayList<Rate>> r) {
        this.amountList = new ArrayList<>();
        this.ratesList = r;
        g = new RatesMap();
        init();
    }

    /**
     * gets the amounts of sku sold, in any currency
     * @param transList 
     */
    public void getAmountOfSku(ArrayList<Trans> transList) {

        for (Trans t : transList) {
            this.amountList.add(t.getAmount());
        }
    }
    
    /**
     * initializes the conversions mapping
     */
    public void init(){
        this.g.build(ratesList);
        this.toUSD = this.g.getToUSD();
    }

    /**
     * returns the solution. It adds the rounded up to usd costs of the sku
     * @return 
     */
    public BigDecimal getSolution() {
        BigDecimal res = new BigDecimal("0");
        BigDecimal tmpDecim;
        
        for (Amount a : amountList) {
            if (a.getCurrency().equalsIgnoreCase("USD")) {
                res = res.add(a.getMoneyAmount().setScale(2, RoundingMode.HALF_EVEN));
            } else {          
                
                tmpDecim = new BigDecimal(toUSD.get(a.getCurrency()));
                tmpDecim = tmpDecim.multiply(a.getMoneyAmount());
                tmpDecim = tmpDecim.setScale(2, RoundingMode.HALF_EVEN);
                res = res.add(tmpDecim);
            }
        }        
        return res;
    }

    public void printAmountList() {
        for (Amount a : amountList) {
            System.out.println(a.toString());
        }
    }    

}
