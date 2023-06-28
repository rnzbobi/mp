import java.util.*;

public class SpecialVendingMachine {
    private HashMap<String, Dish> choices;
    private static HashMap<String, Integer> money;
    private HashMap<String, Integer> transactions;
    private HashMap<Ingredient,Integer> slots;
    private Inventory[] inventory;

    public boolean insertMoney(int m){
        return true;
    }


    public void displayInventory() {
        System.out.println("This is the starting inventory: ");
        System.out.println(inventory[1].getStocks());
        System.out.println("\nThis is the ending inventory: ");
        System.out.println(inventory[2].getStocks());
    }

    public void maintenance() {

    }

    public void printTransaction() {

    }

    public void createDish(String name, ArrayList<Ingredient> ingredients) {

    }

    public void prepareDish(Dish dish) {

    }
}