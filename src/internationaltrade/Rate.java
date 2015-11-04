
package internationaltrade;

/**
 * 
 * @author PC
 */
public class Rate {
    private String from;
    private String to;
    private double conversion;

    public Rate(String from, String to, double conversion) {
        this.from = from;
        this.to = to;
        this.conversion = conversion;
    }

    public Rate() {
    }
    
    

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getConversion() {
        return conversion;
    }

    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    @Override
    public String toString() {
        return "Rate{" + "from=" + from + ", to=" + to + ", conversion=" + conversion + '}';
    }
    
    
    
}
