import java.util.*;

/**
 * Represents an inventory that stores the stocks of ingredients and the amount of money available.
 */
public class Inventory {
    private HashMap<Ingredient, Integer> slots;
    private HashMap<Dish, Integer> slotsdish;
    private ArrayList<Dish> dishList;
    private static HashMap<String, Integer> money;

    /**
     * Updates the amount of money available in the inventory.
     *
     * @param money a HashMap representing the amount of money available, with currency as keys and the corresponding amounts as values
     */
    public void updateMoney(HashMap<String, Integer> money) {
        Inventory.money = money; //updates the whole denomination and the quantity for each denomination
    }

    /**
     * Retrieves the stocks of ingredients in the inventory.
     *
     * @return a HashMap containing the ingredients as keys and the corresponding quantities as values
     */
    public HashMap<Ingredient, Integer> getStocks() {
        return slots;
    }

    /**
     * Retrieves the amount of money available in the inventory.
     *
     * @return a HashMap representing the amount of money available, with currency as keys and the corresponding amounts as values
     */
    public HashMap<String, Integer> getMoney() {
        return money;
    }

    /**
     * Sets the stocks of ingredients in the inventory.
     *
     * @param slots a HashMap representing the ingredients as keys and the corresponding quantities as values
     */
    public void setStocks(HashMap<Ingredient, Integer> slots, ArrayList<Dish> dishes) {
        this.slots = slots;
        this.dishList = dishes;
    }
}