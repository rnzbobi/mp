/**
 * Represents a log entry for an item.
 */
public class Log {
    private String name;
    private int itemprice;
    private int quantity;

    /**
     * Constructs a new Log object.
     *
     * @param name     the name of the item
     * @param itemprice the price of the item
     * @param quantity the quantity of the item
     */
    public Log(String name, int itemprice, int quantity){
        this.name = name;
        this.itemprice = itemprice;
        this.quantity = quantity;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the price of the item.
     *
     * @return the price of the item
     */
    public int getPrice(){
        return itemprice;
    }

    /**
     * Returns the quantity of the item.
     *
     * @return the quantity of the item
     */
    public int getQuan(){
        return quantity;
    }
}