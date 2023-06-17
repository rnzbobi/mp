import java.util.*;

public class RegularVendingMachine {
    private ArrayList<Slot> slots;
    private static HashMap<String,Integer> money;
    private HashMap<String, Integer> transactions;
    private Inventory[] inventory;

    public RegularVendingMachine(ArrayList<Slot> slots){
        this.slots = slots;
        this.inventory = new Inventory[2];
        inventory[0] = new Inventory();
        inventory[1] = new Inventory();
        inventory[0].setStocks(slots);
    }
    public void selectItem(Slot slots) {
        //
    }

    public boolean insertMoney(int m) {
        return true;
    }

    public void updateInventory(Slot slot, Inventory inventory) {

    }

    public void displayInventory() {
        System.out.println("=== Inventory ===");
        ArrayList<Slot> startingStocks = inventory[0].getStocks();
        ArrayList<Slot> endingStocks = inventory[1].getStocks();

        System.out.println("Starting Inventory:");
        for (Slot slot : startingStocks) {
           Ingredient ingredient = slot.getIngredient();
           System.out.println("Item [" + ingredient.getName() + "]" + ": [" + slot.getQuantity() + "]");
        }

        System.out.println("\nEnding Inventory:");
        for (Slot slot : endingStocks) {
            Ingredient ingredient = slot.getIngredient();
            System.out.println("Item [" + ingredient.getName() + "]" + ": [" + slot.getQuantity() + "]");
        }
}

    public void displayAvailableItem(){
        
    }

    public void maintenance() {

    }

    public void printTransaction() {

    }
}