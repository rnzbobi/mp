import java.text.DecimalFormat;
import java.util.*;

public class RegularVendingMachine {
    private static HashMap<Ingredient, Integer> slots;
    private static HashMap<String,Integer> money;
    private HashMap<String, Integer> transactions;
    private Inventory[] inventory;

    public RegularVendingMachine(HashMap<Ingredient,Integer> slots2){
        RegularVendingMachine.slots = slots2;
        this.inventory = new Inventory[2];
        inventory[0] = new Inventory();
        inventory[1] = new Inventory();
        inventory[0].setStocks(slots2);
        inventory[1].setStocks(slots2);
    }

    public void selectItem(String name) {
        boolean found = false;
        HashMap<Ingredient, Integer> availableitems = inventory[1].getStocks();
        for(Map.Entry<Ingredient, Integer> items : availableitems.entrySet()){
            Ingredient ingredient = items.getKey();
            int quantity = items.getValue();
            if(name.equalsIgnoreCase(ingredient.getName()) == true){
                System.out.println("You have selected the item [" + ingredient.getName() + "]");
                System.out.println("The calories of this item is: " + ingredient.getCalories());
                quantity--;
                slots.put(ingredient,quantity);
                found = true;
            }
        }

        if(found == false) {
            System.out.println("[ERROR] The item you have selected is invalid!");
        }
    }

    public boolean insertMoney(int m) {
        return true;
    }

    public void updateInventory(HashMap<Ingredient,Integer> slots2) {

    }

    public void displayStartingInventory() {
        System.out.println("=== Your Starting Inventory ===");
        HashMap<Ingredient, Integer> startingStocks = inventory[0].getStocks();
        for (Map.Entry<Ingredient, Integer> entry : startingStocks.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Item [" + ingredient.getName() + "]: [" + quantity + "]");
        }
    }
    
    public void displayEndingInventory() {
        HashMap<Ingredient, Integer> endingStocks = inventory[1].getStocks();
        System.out.println("=== Your Ending Inventory ===");
        for (Map.Entry<Ingredient, Integer> entry : endingStocks.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Item [" + ingredient.getName() + "]: [" + quantity + "]");
            }
    }

 public void displayAvailableItem() {
    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    int i = 0;
    for (Map.Entry<Ingredient, Integer> entry : slots.entrySet()) {
        Ingredient item = entry.getKey();
        int quantity = entry.getValue();
        String itemName = item.getName();
        int itemPrice = item.getPrice();
        System.out.println("[" + quantity + "] x " + itemName + " | Price: Php " + decimalFormat.format(itemPrice));
        }
    }
}

    public void maintenance() {

    }

    public void printTransaction() {

    }
}