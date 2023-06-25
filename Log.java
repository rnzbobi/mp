public class Log {
    private String name;
    private int itemprice;
    private int quantity;

    public Log(String name, int itemprice, int quantity){
        this.name = name;
        this.itemprice = itemprice;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return itemprice;
    }

    public int getQuan(){
        return quantity;
    }
}
