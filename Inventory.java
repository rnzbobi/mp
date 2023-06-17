import java.util.*;

public class Inventory {
    private ArrayList<Slot> stocks;
    private static HashMap<String,Integer> money;

    public void updateMoney(HashMap<String,Integer> money){
        Inventory.money = money;
    }

    public ArrayList<Slot> getStocks() {
        return stocks;
    }

    public HashMap<String,Integer> getMoney() {
        return money;
    }

    public void setStocks(ArrayList<Slot> stocks){
        this.stocks = stocks;
    }
}