import java.util.*;
import java.text.DecimalFormat;
public class SpecialVendingMachine extends RegularVendingMachine{
    private Dish dish;
    private ArrayList<Dish> dishList;

	public SpecialVendingMachine(String name, String type, HashMap<Ingredient,Integer> slots2, Bank bank, ArrayList<Dish> dishList){
        super(name,type,slots2,bank);
        this.dishList = dishList;
    }
	
    public void createDish(String name, ArrayList<Ingredient> ingredients) {

    }

    public void prepareDish(Dish dish) {

    }

    @Override
    public void displayAvailableItem() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        int i = 1;
        int maxItemNameLength = 0;

        for (Dish dish : dishList) {
            // gets the length of the name of each dish
            int itemNameLength = dish.getName().length();
            if (itemNameLength > maxItemNameLength)
                maxItemNameLength = itemNameLength;
        }
        int itemInfoWidth = maxItemNameLength + 25;

        for (Dish dish : dishList) {
            int quantity = inventory[0].getStock(dish);
            String itemName = dish.getName();
            int itemPrice = dish.getPrice();
            i++;

            if (i % 2 == 0) {
                System.out.println("\n");
            }

            if (quantity >= 1) {
                // if the item is not out of stock
                String itemInfo = "[" + quantity + "]" + itemName + " | Price: Php " + decimalFormat.format(itemPrice);
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            } else {
                // if the item is out of stock
                String itemInfo = "[SOLD OUT]" + itemName + " | Price: Php " + decimalFormat.format(itemPrice);
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            }
        }

        System.out.println("\n\n");
    }

    @Override
    public void displayStartingInventory() {
        System.out.println("=== Your Starting Inventory ===");
        HashMap<Dish, Integer> startingStocks = inventory[0].getDishStocks();
        // for each dish, the name and the quantity is displayed
        for (Map.Entry<Dish, Integer> entry : startingStocks.entrySet()) {
            Dish dish = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Dish [" + dish.getName() + "]: [" + quantity + "]");
        }
}