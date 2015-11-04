
package model;

import model.Amount;

/**
 * Holds the data for a transaction
 * @author PC
 */
public class Trans {
    private String store;
    private String sku;
    private Amount amount;
   

    public Trans(String store, String sku, Amount amount_) {
        this.store = store;
        this.sku = sku;
        this.amount = amount_;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount_) {
        this.amount = amount_;
    }

        
    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "Trans{" + "store=" + store + ", sku=" + sku + ", amount_=" + amount.toString() + '}';
    }
    
    
    
}
