import java.util.*;
import java.text.DecimalFormat;
public class SpecialVendingMachine extends RegularVendingMachine{
    private Dish dish;
    private static ArrayList<Dish> dishList;

	public SpecialVendingMachine(String name, String type, HashMap<Ingredient,Integer> slots2, Bank bank, ArrayList<Dish> dishList){
        super(name,type,slots2,bank);
        this.dishList = dishList;
        setStocksDish(dishList);
    }

    public boolean selectDish(String name) throws InterruptedException {
        boolean found = false;
        int price, total = 0, totalowner;
        ArrayList<Dish> availableItems = inventory[1].getDishStocks();

        for (Dish dish : availableItems) { // for-each loop to iterate over all items
            String dishName = dish.getName();
            int quantity = dish.getQuantity();
            price = dish.getPrice();

            if (name.equalsIgnoreCase(dishName) && price <= bank.getUserTotalMoney() && quantity > 0) {
                // Item found and user has sufficient funds and quantity is available
                System.out.println("You have selected the item [" + name + "]\n");
                System.out.println("The calories of this item is: " + dish.getCalories() + "\n");
                // Update totals and quantities
                total = bank.getUserTotalMoney() - price;
                totalowner = bank.getTotalMoney() + price;

                // Decrement the quantities of ingredients used in the dish
                for (Map.Entry<Ingredient, Integer> entry : dish.getIngredients().entrySet()) {
                    Ingredient dishIngredient = entry.getKey();
                    int dishIngredientQuantity = entry.getValue();

                    for (Map.Entry<Ingredient, Integer> slotEntry : slots.entrySet()) {
                        Ingredient slotIngredient = slotEntry.getKey();
                        int slotIngredientQuantity = slotEntry.getValue();

                        if (slotIngredient.getName().equalsIgnoreCase(dishIngredient.getName())) {
                            int remainingQuantity = slotIngredientQuantity - dishIngredientQuantity;
                            slots.put(slotIngredient, remainingQuantity);
                            break;
                        }
                    }
                }

                // Calculate change using the VendingMachine class
                VendingMachine vendingMachine = new VendingMachine();
                int amountPaid = total + price;
                int amountToPay = price;
                Map<Integer, Integer> change = vendingMachine.dispenseChange(amountPaid, amountToPay);
                if (change != null) {
                    // Sufficient change available
                    // Update quantities and money
                    if(name.equalsIgnoreCase("Caramel Macchiato")){
                        for(Map.Entry<Ingredient, Integer> entry : slots.entrySet()){
                            Ingredient ingredient = entry.getKey();
                            String ingredientname = ingredient.getName();
                            int ingredientquantity = entry.getValue();
                            if(ingredientname.equalsIgnoreCase("Espresso Roast")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Milk")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Vanilla Syrup")){
                                ingredientquantity -= 2;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Caramel Syrup")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                        }
                    }
                    else if(name.equalsIgnoreCase("White Chocolate Mocha")){
                        for(Map.Entry<Ingredient, Integer> entry : slots.entrySet()){
                            Ingredient ingredient = entry.getKey();
                            String ingredientname = ingredient.getName();
                            int ingredientquantity = entry.getValue();
                            if(ingredientname.equalsIgnoreCase("Espresso Roast")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Chocolate Chips")){
                                ingredientquantity -= 3;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Milk")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Whipped Cream")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                        }
                    }
                    else if(name.equalsIgnoreCase("Caffe Americano")){
                        for(Map.Entry<Ingredient, Integer> entry : slots.entrySet()){
                            Ingredient ingredient = entry.getKey();
                            String ingredientname = ingredient.getName();
                            int ingredientquantity = entry.getValue();
                            if(ingredientname.equalsIgnoreCase("Hot Water")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Espresso Roast")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                        }
                    }
                    else if(name.equalsIgnoreCase("Caffe Latte")){
                        for(Map.Entry<Ingredient, Integer> entry : slots.entrySet()){
                            Ingredient ingredient = entry.getKey();
                            String ingredientname = ingredient.getName();
                            int ingredientquantity = entry.getValue();
                            if(ingredientname.equalsIgnoreCase("Espresso Roast")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Milk")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Milk Whisk")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Vanilla Syrup")){
                                ingredientquantity -= 2;
                                slots.put(ingredient, ingredientquantity);
                            }
                        }
                    }
                    else if(name.equalsIgnoreCase("Cappucino")){
                        for(Map.Entry<Ingredient, Integer> entry : slots.entrySet()){
                            Ingredient ingredient = entry.getKey();
                            String ingredientname = ingredient.getName();
                            int ingredientquantity = entry.getValue();
                            if(ingredientname.equalsIgnoreCase("Espresso")){
                                ingredientquantity -= 2;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Milk")){
                                ingredientquantity -= 1;
                                slots.put(ingredient, ingredientquantity);
                            }
                            else if(ingredientname.equalsIgnoreCase("Cinnamon")){
                                ingredientquantity -= 2;
                                slots.put(ingredient, ingredientquantity);
                            }
                        }
                    }
                    bank.updateUserTotalMoney(total);
                    bank.updateTotalMoney(totalowner);

                    System.out.println("Generating receipt");
                    Thread.sleep(500);
                    System.out.print(". ");
                    Thread.sleep(500);
                    System.out.print(". ");
                    Thread.sleep(500);
                    System.out.print(". \n");

                    found = true; //updates found to true if item is found
                    Log sale = new Log(dish.getName(), price, 1);
                    Sales.add(sale); //adds the sale to log

                    System.out.println("------------------------------");
                    System.out.println("         RECEIPT");
                    System.out.println("------------------------------");
                    System.out.println("Item: " + dish.getName());
                    System.out.println("Price: Php " + price);
                    System.out.println("Tendered: " + (total + price));
                    System.out.println("Change: " + total);
                    for (int denomination : change.keySet()) { //for-each loop to get the specified change
                        int numNotes = change.get(denomination);
                        if(numNotes != 0){
                            System.out.println("Change Denomination: " + denomination + ", Quantity: " + numNotes);
                        }
                    }
                    bank.updateUserMoney(1000, 0);
                    bank.updateUserMoney(500, 0);
                    bank.updateUserMoney(200, 0);
                    bank.updateUserMoney(100, 0);
                    bank.updateUserMoney(50, 0);
                    bank.updateUserMoney(10, 0);
                    bank.updateUserMoney(5, 0);
                    bank.updateUserMoney(1, 0);
                    bank.updateUserTotalMoney(0);
                } else {
                    // Insufficient change available
                    System.out.println("Cannot make exact change. Please contact the operator.");
                    bank.updateUserMoney(1000, 0);
                    bank.updateUserMoney(500, 0);
                    bank.updateUserMoney(200, 0);
                    bank.updateUserMoney(100, 0);
                    bank.updateUserMoney(50, 0);
                    bank.updateUserMoney(10, 0);
                    bank.updateUserMoney(5, 0);
                    bank.updateUserMoney(1, 0);
                    bank.updateUserTotalMoney(0);
                }
                System.out.println("------------------------------");
                System.out.println("Thank you for your purchase!\n");
                inventory[1].setStocks(slots); //updates the ending inventory
                return true;
            } else if (name.equalsIgnoreCase(dish.getName()) && price > bank.getUserTotalMoney()) {
                // Item found but user does not have sufficient funds
                System.out.println("[Error Processing: INSUFFICIENT FUNDS] Please insert money.");
                insertMoney();
                return false;
            } else if (name.equalsIgnoreCase(dish.getName()) && quantity <= 0) {
                // Item found but quantity is not available
                System.out.println("[Error Processing: INSUFFICIENT QUANTITY]");
                bank.updateUserMoney(1000, 0);
                bank.updateUserMoney(500, 0);
                bank.updateUserMoney(200, 0);
                bank.updateUserMoney(100, 0);
                bank.updateUserMoney(50, 0);
                bank.updateUserMoney(10, 0);
                bank.updateUserMoney(5, 0);
                bank.updateUserMoney(1, 0);
                bank.updateUserTotalMoney(0);
                return false;
            }
        }

        if (!found) { //error message if item is invalid
            System.out.println("[ERROR] The item you have selected is invalid!");
            return false;
        }

        return false;
    }

    public void createDish(String name, ArrayList<Ingredient> ingredients) {

    }

    public void prepareDish(Dish dish) {

    }


    public void displaySpecialAvailableItem() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        int i = 1;
        boolean soldout = false;
        int maxItemNameLength = 0;

        // Get the maximum length of the dish name to format the output nicely
        for (Dish dish : dishList) {
            int dishNameLength = dish.getName().length();
            if (dishNameLength > maxItemNameLength)
                maxItemNameLength = dishNameLength;
        }

        int itemInfoWidth = maxItemNameLength + 25;

        for (Dish dish : dishList) {
            int quantity = dish.getQuantity(); // Get the quantity from the inventory
            String itemName = dish.getName();
            int itemPrice = dish.getPrice();

            int maxDishes = Integer.MAX_VALUE;

            for (Map.Entry<Ingredient, Integer> entry : dish.getIngredients().entrySet()) {
                Ingredient ingredient = entry.getKey();
                int requiredQuantity = entry.getValue();

                if (getSlots().containsKey(ingredient)) {
                    int availableQuantity = getSlots().get(ingredient);
                    int maxDishesFromIngredient = availableQuantity / requiredQuantity;
                    maxDishes = Math.min(maxDishes, maxDishesFromIngredient);
                } else {
                    // If any required ingredient is missing in the vending machine, set maxDishes to 0
                    maxDishes = 0;
                    break;
                }
            }

            i++;

            if (i % 2 == 0) {
                System.out.println("\n");
            }

            if (maxDishes >= 1 && !soldout) {
                // If the item is not out of stock
                String itemInfo = itemName + " | Price: Php " + decimalFormat.format(itemPrice) + " | Available: " + maxDishes;
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            } else {
                // If the item is out of stock
                String itemInfo = "[SOLD OUT]" + itemName;
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            }
        }

        System.out.println("\n\n");
    }

}