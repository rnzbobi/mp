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
					else{
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
	
	/**
     * Performs maintenance operations on the vending machine.
     * It allows replenishing stocks, setting item prices, collecting money,
     * replenishing money, printing a summary of transactions, and displaying
     * the inventory.
     * @throws InterruptedException if the thread is interrupted during sleep
     */
    public void specialMaintenance() throws InterruptedException{
        HashMap<Ingredient, Integer> availableitems = inventory[1].getStocks();
        String itemname;
        int choice, replenish, setprice, i;
        boolean found = false;
        do{
            System.out.println("Vending Machine [" + getName() + "]" + " is under MAINTENANCE");
            displayAvailableItem();
            System.out.println("[1] Replenish Stocks");
            System.out.println("[2] Set Item Price");
            System.out.println("[3] Collect Money");
            System.out.println("[4] Replenish Money");
            System.out.println("[5] Print Summary of Transactions");
            System.out.println("[6] Display Inventory (Starting and Final)");
			System.out.println("[7] Add Ingredient");
            System.out.println("[8] Add Dish");
            System.out.println("[9] Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    //if the user wishes to choose replenish money option
                    found = false;
                    displayAvailableItem();
                    System.out.print("\nEnter the item that you wish to replenish: ");
                    itemname = sc.nextLine();

                    for (Map.Entry<Ingredient, Integer> items : availableitems.entrySet()) {
                        //locates the item that wishes to replenish
                        Ingredient ingredient = items.getKey();
                        int quantity = items.getValue();
                        int price = ingredient.getPrice();
                        if (itemname.equalsIgnoreCase(ingredient.getName())) {
                            System.out.println("How much would you like to replenish?");
                            replenish = Integer.parseInt(sc.nextLine());
                            quantity += replenish;
                            slots.put(ingredient,quantity);
                            found = true;
                        }
                    }
                    if(!found){
                        //if item does not exist
                        System.out.println("[ERROR] Item does not exist");
                    }
                    break;
                case 2:
                    //if the user wishes to change the price
                    found = false;
                    displayAvailableItem();
                    System.out.print("\nEnter the item that you wish to change price: ");
                    itemname = sc.nextLine();

                    for (Map.Entry<Ingredient, Integer> items : availableitems.entrySet()) {
                        //locates the item that wishes to replenish
                        Ingredient ingredient = items.getKey();
                        int quantity = items.getValue();
                        int price = ingredient.getPrice();
                        if (itemname.equalsIgnoreCase(ingredient.getName())) {
                            System.out.print("How much would you like to set this product? ");
                            setprice = Integer.parseInt(sc.nextLine());
                            ingredient.setPrice(setprice);
                            found = true;
                        }
                    }
                    if(!found){
                        //if the item does not exist
                        System.out.println("[ERROR] Item does not exist");
                    }
                    break;

                case 3:
                    System.out.print("\nCollecting money");
                    Thread.sleep(1000);
                    System.out.print(". ");
                    Thread.sleep(1000);
                    System.out.print(". ");
                    Thread.sleep(1000);
                    System.out.print(". \n");
                    System.out.println("[BANK]");
                    System.out.println("[Quantity - Bill/Coins]");
                    HashMap<Integer, Integer> money = bank.getMoney();
                    i = 1;
                    for (int denomination : denominations) {
                        //displays the quantity of each denomination of the owner's current balance
                        System.out.println("(" + i + ") " + "[" + money.get(denomination) + " - " + denomination + "]");
                        i++;
                    }
                    System.out.println("Choose Bill/Coins you will collect!");

                    int billIndex = -1;
                    int total2 = bank.getTotalMoney();
                    int updatedTotal = 0;
                    int newQuantity = 0;
                    while (billIndex < 0 || billIndex > denominations.length) {
                        //enter a number to collect between from 1- denomination's quantity
                        System.out.println("Enter the index of the bill/coin (1 - " + (denominations.length) + "): ");
                        billIndex = Integer.parseInt(sc.nextLine());
                    }

                    int chosenDenomination = denominations[billIndex - 1];

                    int currentQuantity = bank.getMoney().get(chosenDenomination);
                    if (currentQuantity == 0) {
                        //if the quantity for a specific denomination is 0
                        System.out.println("No more bills/coins available.");
                    } else {
                        System.out.println("Enter the quantity to collect (1 - " + currentQuantity + "): ");

                        int quantityToCollect = -1;
                        while (quantityToCollect < 1 || quantityToCollect > currentQuantity) {
                            //collects the quantity
                            quantityToCollect = Integer.parseInt(sc.nextLine());
                        }

                        newQuantity = currentQuantity - quantityToCollect;
                        bank.updateMoney(chosenDenomination, newQuantity);
                        updatedTotal = total2 - (chosenDenomination * quantityToCollect);
                        bank.updateTotalMoney(updatedTotal);
                        total2 = updatedTotal;

                        System.out.println("Successfully collected " + quantityToCollect + " bills/coins of [" + chosenDenomination + "]");
                        System.out.println("Updated quantity for " + chosenDenomination + ": " + newQuantity);
                    }
                    System.out.print("\nMoney has been dispensed.");
                    break;

                case 4:
                    replenishMoney();
                    break;

                case 5:
                    System.out.print("\nPrinting Summary");
                    Thread.sleep(500);
                    System.out.print(". ");
                    Thread.sleep(500);
                    System.out.print(". ");
                    Thread.sleep(500);
                    System.out.print(". ");
                    System.out.println("");
                    printSummaryTransaction();
                    break;

                case 6:
                    displayStartingInventory();
                    System.out.println("");
                    displayEndingInventory();
                    break;

                case 7:
					displayAvailableItem();
					HashSet<String> ingredientNames = new HashSet<>();
					String ingredientName;
					for (Map.Entry<Ingredient, Integer> entry : slots.entrySet()) {
						String name = entry.getKey().getName();
						ingredientNames.add(name);
					}
					do{
						System.out.print("Enter the name of the ingredient you want to add: ");
						ingredientName = sc.nextLine();
						if(ingredientNames.contains(ingredientName)){
							System.out.println("Ingredient with the same name already exists!");
						}
					}while(ingredientNames.contains(ingredientName));
					
					System.out.print("Enter the price for ingredient " + ingredientName + ": ");
                    int ingredientPrice = sc.nextInt();
                    sc.nextLine(); // Clear the input buffer

                    System.out.print("Enter the number of calories for ingredient " + ingredientName + ": ");
                    int ingredientCalories = sc.nextInt();
                    sc.nextLine(); // Clear the input buffer

                    System.out.print("Enter the quantity for ingredient " + ingredientName + ": ");
                    int ingredientQuantity;
                    while (!sc.hasNextInt() || (ingredientQuantity = sc.nextInt()) < 10) {
                        System.out.println("Invalid input. Please enter a number greater than or equal to 10.");
                        sc.nextLine(); // Clear the input buffer
                    }
                    sc.nextLine();

                    Ingredient ingredient = new Ingredient(ingredientName, ingredientPrice, ingredientCalories);
					slots.put(ingredient, ingredientQuantity);
                    break;
				
				case 8:
					displayAvailableItem();
					System.out.println("");
					displaySpecialAvailableItem();
					
					HashSet<String> dishNames = new HashSet<>();
					String dishName;
					for (Dish dish : dishList){
						String name = dish.getName();
						dishNames.add(name);
					}
					do{
						System.out.print("Enter the name of the dish you want to add: ");
						dishName = sc.nextLine();
						if(dishNames.contains(dishName)){
							System.out.println("Dish with the same name already exists!");
						}
					}while(dishNames.contains(dishName));
					
					HashMap<Ingredient, Integer> dishCombination = new HashMap<>();
                    int ingredientCount = 0;

                    do {
                        System.out.print("Enter the name of ingredient for slot " + (ingredientCount + 1) + " for the dish " + dishName + ": ");
                        String newIngredientName = sc.nextLine();

                        // Find the ingredient in slots based on the name
                        Ingredient findIngredient = null;
                        for (Ingredient existingIngredient : slots.keySet()) {
                            if (existingIngredient.getName().equalsIgnoreCase(newIngredientName)) {
                                findIngredient = existingIngredient;
                                break;
                            }
                        }

                        if (findIngredient == null) {
                            System.out.println("Ingredient not found. Please enter a valid ingredient.");
                            continue;
                        }

                        System.out.print("Enter the quantity of " + findIngredient.getName() + " for the dish " + dishName + ": ");
                        int quantity;
                        while (!sc.hasNextInt() || (quantity = sc.nextInt()) < 1) {
                            System.out.println("Invalid input. Please enter a number greater than or equal to 1.");
                            sc.nextLine(); // Clear the input buffer
                        }
                        sc.nextLine();

						// Check if there are enough stocks of the ingredient for the given quantity
                        if (quantity > slots.get(findIngredient)) {
                            System.out.println("Not enough stocks of " + findIngredient.getName() + ". Available stocks: " + slots.get(findIngredient));
                            continue;
                        }

                        dishCombination.put(findIngredient, quantity);
                        ingredientCount++;

                        if (ingredientCount >= 2) {
                            System.out.print("Do you want to add more ingredients to the dish? [Y | N]: ");
                            String answer = sc.nextLine();
                            if (!answer.equalsIgnoreCase("Y")) {
                                break;
                            }
                        }
                    } while (true);

                    // Ask for the price of the dish
                    System.out.print("Enter the price for the dish " + dishName + ": ");
                    int dishPrice;
                    while (!sc.hasNextInt() || (dishPrice = sc.nextInt()) <= 0) {
                        System.out.println("Invalid input. Please enter a positive number for the price.");
                        sc.nextLine(); // Clear the input buffer
                    }
                    sc.nextLine();

                    // Calculate the maximum quantity of the dish that can be created based on ingredient availability
                    int maxQuantity = Integer.MAX_VALUE;
                    for (Map.Entry<Ingredient, Integer> entry : dishCombination.entrySet()) {
                        Ingredient getIngredient = entry.getKey();
                        int quantity = entry.getValue();
                        int availableStocks = slots.get(getIngredient);
                        maxQuantity = Math.min(maxQuantity, availableStocks / quantity);
                    }

                    // Calculate the total calories and price of the dish based on its ingredients
                        int totalCalories = 0;
                        int totalPrice = 0;
                        for (Map.Entry<Ingredient, Integer> entry : dishCombination.entrySet()) {
                            Ingredient getIngredient = entry.getKey();
                            int quantity = entry.getValue();
                            totalCalories += (getIngredient.getCalories() * quantity);
                            totalPrice += (getIngredient.getPrice() * quantity);
                        }

                    // Add the new dish to the list with the calculated max quantity
                    Dish customDish = new Dish(dishName, maxQuantity, dishPrice, totalCalories, dishCombination);
                    dishList.add(customDish);     
					break;
				
				case 9:
					break;

                default: System.out.println("INVALID CHOICE!");
            }
        }while(choice != 9);
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
                String itemInfo = " " + itemName + " | Price: Php " + decimalFormat.format(itemPrice) + " | Available: " + maxDishes;
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            } else {
                // If the item is out of stock
                String itemInfo = " [SOLD OUT]" + itemName;
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            }
        }

        System.out.println("\n\n");
    }

}