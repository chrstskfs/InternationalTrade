
package internationaltrade;

import java.math.BigDecimal;

/**
 * Class for holding cost and currency
 * @author PC
 */

class Amount {
    
    private BigDecimal moneyAmount;
    private String currency;

    public Amount(double moneyAmount, String currency) {
        this.moneyAmount = new BigDecimal(moneyAmount);
        this.currency = currency;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Amount{" + "moneyAmount=" + moneyAmount.toString() + ", currency=" + currency + '}';
    }
    
    
    
}
