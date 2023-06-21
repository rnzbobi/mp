import java.util.*;

public class Inventory {
    private static HashMap<Ingredient,Integer> slots;
    private static HashMap<String,Integer> money;

    public void updateMoney(HashMap<String,Integer> money){
        Inventory.money = money;
    }

    public HashMap<Ingredient,Integer> getStocks() {
        return slots;
    }

    public HashMap<String,Integer> getMoney() {
        return money;
    }

    public void setStocks(HashMap<Ingredient,Integer> slots){
        Inventory.slots = slots;
    }
}