import java.text.DecimalFormat;
import java.util.*;
/**
 * The RegularVendingMachine class represents a regular vending machine.
 * It allows users to select items, insert money, and perform various operations.
 */
public class RegularVendingMachine {
    private final String name;
    private final String type;
    private static HashMap<Ingredient, Integer> slots;
    private Bank bank;
    private Inventory[] inventory;
    private ArrayList<Log> Sales;
    private final static int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
    Scanner sc = new Scanner(System.in);
    /**
     * Constructs a RegularVendingMachine object with the given name, slots, and bank.
     *
     * @param name   the name of the vending machine
     * @param slots2 the available slots in the vending machine
     * @param bank   the bank associated with the vending machine
     */
    public RegularVendingMachine(String name, String type, HashMap<Ingredient,Integer> slots2, Bank bank){
        this.name = name;
        this.bank = bank;
        this.type = type;
        RegularVendingMachine.slots = slots2;
        this.inventory = new Inventory[2];
        inventory[0] = new Inventory();
        inventory[1] = new Inventory();
        inventory[0].setStocks(new HashMap<>(slots2));
        inventory[1].setStocks(slots2);
        this.Sales = new ArrayList<>();
    }
    /**
     * Selects an item from the vending machine based on the provided item name.
     *
     * @param name the name of the item to select
     * @return true if the item was successfully selected, false otherwise
     * @throws InterruptedException if the thread is interrupted during the process
     */
    public boolean selectItem(String name) throws InterruptedException {
        boolean found = false;
        int price, total = 0, totalowner;
        HashMap<Ingredient, Integer> availableItems = inventory[1].getStocks();

        for (Map.Entry<Ingredient, Integer> items : availableItems.entrySet()) { // for-each loop to iterate over all items
            Ingredient ingredient = items.getKey();
            int quantity = items.getValue();
            price = ingredient.getPrice();

            if (name.equalsIgnoreCase(ingredient.getName()) && price <= bank.getUserTotalMoney() && quantity > 0) {
                // Item found and user has sufficient funds and quantity is available
                System.out.println("You have selected the item [" + ingredient.getName() + "]\n");
                System.out.println("The calories of this item is: " + ingredient.getCalories() + "\n");
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
                    quantity--;
                    slots.put(ingredient, quantity);
                    bank.updateUserTotalMoney(total);
                    bank.updateTotalMoney(totalowner);
                    // Display progress messages
                    System.out.println("Dispensing item");
                    Thread.sleep(500);
                    System.out.print(". ");
                    Thread.sleep(500);
                    System.out.print(". ");
                    Thread.sleep(500);
                    System.out.print(". \n");
                    System.out.println("Generating receipt");
                    Thread.sleep(500);
                    System.out.print(". ");
                    Thread.sleep(500);
                    System.out.print(". ");
                    Thread.sleep(500);
                    System.out.print(". \n");

                    found = true; //updates found to true if item is found
                    Log sale = new Log(ingredient.getName(), price, 1);
                    Sales.add(sale); //adds the sale to log

                    System.out.println("------------------------------");
                    System.out.println("         RECEIPT");
                    System.out.println("------------------------------");
                    System.out.println("Item: " + ingredient.getName());
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
            } else if (name.equalsIgnoreCase(ingredient.getName()) && price > bank.getUserTotalMoney()) {
                // Item found but user does not have sufficient funds
                System.out.println("[Error Processing: INSUFFICIENT FUNDS] Please insert money.");
                insertMoney();
                return false;
            } else if (name.equalsIgnoreCase(ingredient.getName()) && quantity <= 0) {
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
     * Inserts money into the vending machine.
     */
    public void insertMoney() {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int usertotal = 0;
    
        do {
        i = 0;
        i2 = 0;
        usertotal = bank.getUserTotalMoney();
        System.out.println("[Choose a number that corresponds to your Bills/Coins] [Balance: " + bank.getUserTotalMoney() + "]");
        System.out.println("|1| 1000 Pesos |2| 500 Pesos |3| 200 Pesos");
        System.out.println("|4| 100 Pesos  |5| 50 Pesos  |6| 20 Pesos");
        System.out.println("|7| 10 Pesos   |8| 5 Pesos   |9| 1 Pesos");
        System.out.println("|10| [EXIT]");
        System.out.print("[Enter] ");

        do{
            i = Integer.parseInt(sc.nextLine());
            if(i < 0 || i > 10){
                System.out.print("[Error Insert Valid Number] ");
            }
        }while(i < 0 || i > 10);
        
        if (i == 10) {
            break;
        }
        
        System.out.print("How many: ");
        i2 = Integer.parseInt(sc.nextLine());

        switch (i) {
            case 1 -> {
                //if the user inputted "1000"
                i3 = bank.getMoney().get(1000) + i2;
                i4 = bank.getUserMoney().get(1000) + i2;
                bank.updateUserMoney(1000, i4);
                bank.updateMoney(1000,i3);
                usertotal += 1000 * i2;
                bank.updateUserTotalMoney(usertotal);
            }

            case 2 -> {
                //if the user inputted "500"
                i3 = bank.getMoney().get(500) + i2;
                i4 = bank.getUserMoney().get(500) + i2;
                bank.updateUserMoney(500, i4);
                bank.updateMoney(500,i3);
                usertotal += 500 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 3 -> {
                //if the user inputted "200"
                i3 = bank.getMoney().get(200) + i2;
                i4 = bank.getUserMoney().get(200) + i2;
                bank.updateUserMoney(200, i4);
                bank.updateMoney(200,i3);
                usertotal += 200 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 4 -> {
                //if the user inputted "100"
                i3 = bank.getMoney().get(100) + i2;
                i4 = bank.getUserMoney().get(100) + i2;
                bank.updateUserMoney(100, i4);
                bank.updateMoney(100,i3);
                usertotal += 100 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 5 -> {
                //if the user inputted "50"
                i3 = bank.getMoney().get(50) + i2;
                i4 = bank.getUserMoney().get(50) + i2;
                bank.updateUserMoney(50, i4);
                bank.updateMoney(50,i3);
                usertotal += 50 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 6 -> {
                //if the user inputted "20"
                i3 = bank.getMoney().get(20) + i2;
                i4 = bank.getUserMoney().get(20) + i2;
                bank.updateUserMoney(20, i4);
                bank.updateMoney(20,i3);
                usertotal += 20 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 7 -> {
                //if the user inputted "10"
                i3 = bank.getMoney().get(10) + i2;
                i4 = bank.getUserMoney().get(10) + i2;
                bank.updateUserMoney(10, i4);
                bank.updateMoney(10,i3);
                usertotal += 10 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 8 -> {
                //if the user inputted "5"
                i3 = bank.getMoney().get(5) + i2;
                i4 = bank.getUserMoney().get(5) + i2;
                bank.updateUserMoney(5, i4);
                bank.updateMoney(5,i3);
                usertotal += 5 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 9 -> {
                //if the user inputted "1"
                i3 = bank.getMoney().get(1) + i2;
                i4 = bank.getUserMoney().get(1) + i2;
                bank.updateUserMoney(1, i4);
                bank.updateMoney(1,i3);
                usertotal += i2;
                bank.updateUserTotalMoney(usertotal);
            }
        }
    } while (i != 10);
}
    /**
     * Replenishes the money in the vending machine.
     */

    public void replenishMoney() {
        HashMap<Integer,Integer> tempbank;
        int i = 0;
        int i2 = 0;
        int total = 0;
        int value = 0;
        int temp = 0;

        do {
            i = 0;
            i2 = 0;
            value = 0;
            total = bank.getTotalMoney();
            tempbank = bank.getMoney();
            System.out.println("[Choose a number that corresponds to your Bills/Coins] [Balance: " + bank.getTotalMoney() + "]");
            System.out.println("|1| 1000 Pesos |2| 500 Pesos |3| 200 Pesos");
            System.out.println("|4| 100 Pesos  |5| 50 Pesos  |6| 20 Pesos");
            System.out.println("|7| 10 Pesos   |8| 5 Pesos   |9| 1 Pesos");
            System.out.println("|10| EXIT");
            System.out.print("[Enter] ");

            do{
                i = Integer.parseInt(sc.nextLine());
                if(i < 0 || i > 10){
                    System.out.print("[Error Insert Valid Number] ");
                }
            }while(i < 0 || i > 10);

            if (i == 10) {
                break;
            }

            System.out.print("How many: ");
            i2 = Integer.parseInt(sc.nextLine());

            switch (i) {
                case 1 -> {
                    value = tempbank.get(1000);
                    temp = i2;
                    i2 += value;
                    bank.updateMoney(1000, i2);
                    total += 1000 * temp;
                    bank.updateTotalMoney(total);
                }
                case 2 -> {
                    value = tempbank.get(500);
                    temp = i2;
                    i2 += value;
                    bank.updateMoney(500, i2);
                    total += 500 * temp;
                    bank.updateTotalMoney(total);
                }
                case 3 -> {
                    value = tempbank.get(200);
                    temp = i2;
                    i2 += value;
                    bank.updateMoney(200, i2);
                    total += 200 * temp;
                    bank.updateTotalMoney(total);
                }
                case 4 -> {
                    value = tempbank.get(100);
                    temp = i2;
                    i2 += value;
                    bank.updateMoney(100, i2);
                    total += 100 * temp;
                    bank.updateTotalMoney(total);
                }
                case 5 -> {
                    value = tempbank.get(50);
                    temp = i2;
                    i2 += value;
                    bank.updateMoney(50, i2);
                    total += 50 * temp;
                    bank.updateTotalMoney(total);
                }
                case 6 -> {
                    value = tempbank.get(20);
                    temp = i2;
                    i2 += value;
                    bank.updateMoney(20, i2);
                    total += 20 * temp;
                    bank.updateTotalMoney(total);
                }
                case 7 -> {
                    value = tempbank.get(10);
                    temp = i2;
                    i2 += value;
                    bank.updateMoney(10, i2);
                    total += 10 * temp;
                    bank.updateTotalMoney(total);
                }
                case 8 -> {
                    value = tempbank.get(5);
                    temp = i2;
                    i2 += value;
                    bank.updateMoney(5, i2);
                    total += 5 * temp;
                    bank.updateTotalMoney(total);
                }
                case 9 -> {
                    value = tempbank.get(1);
                    temp = i2;
                    i2 += value;
                    bank.updateMoney(1, i2);
                    total += 1 * temp;
                    bank.updateTotalMoney(total);
                }
            }
        } while (i != 10);
    }
    /**
     * Displays the starting inventory of the vending machine.
     * It retrieves the starting stocks from the inventory and prints each item
     * along with its quantity.
     */
    public void displayStartingInventory() {
        System.out.println("=== Your Starting Inventory ===");
        HashMap<Ingredient, Integer> startingStocks = inventory[0].getStocks();
        //for each item, the name and the quantity is displayed
        for (Map.Entry<Ingredient, Integer> entry : startingStocks.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Item [" + ingredient.getName() + "]: [" + quantity + "]");
        }
    }
    /**
     * Displays the ending inventory of the vending machine.
     * It retrieves the ending stocks from the inventory and prints each item
     * along with its quantity.
     */
    public void displayEndingInventory() {
        HashMap<Ingredient, Integer> endingStocks = inventory[1].getStocks();
        System.out.println("=== Your Ending Inventory ===");
        //for each item, the name and the quantity is displayed
        for (Map.Entry<Ingredient, Integer> entry : endingStocks.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Item [" + ingredient.getName() + "]: [" + quantity + "]");
            }
    }
    /**
     * Displays the available items in the vending machine.
     * It formats the item information and prints it in a structured manner.
     */
    public void displayAvailableItem() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        int i = 1;
        int maxItemNameLength = 0;

        for (Ingredient item : slots.keySet()) {
            //gets the length name of each object
            int itemNameLength = item.getName().length();
            if (itemNameLength > maxItemNameLength)
                    maxItemNameLength = itemNameLength;
        }
        int itemInfoWidth = maxItemNameLength + 25;

        for (Map.Entry<Ingredient, Integer> entry : slots.entrySet()) {
            //gets the name, quantity, and price of each object
            Ingredient item = entry.getKey();
            int quantity = entry.getValue();
            String itemName = item.getName();
            int itemPrice = item.getPrice();
            i++;

            if (i % 2 == 0) {
                System.out.println("\n");
            }

            if(quantity >= 1) {
                //if the item is not out of stock
                String itemInfo = "[" + quantity + "]" + itemName + " | Price: Php " + decimalFormat.format(itemPrice);
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            }
            else {
                //if the item is out of stock
                String itemInfo = "[SOLD OUT]" + itemName + " | Price: Php " + decimalFormat.format(itemPrice);
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            }
        }

        System.out.println("\n\n");
    }
    /**
     * Performs maintenance operations on the vending machine.
     * It allows replenishing stocks, setting item prices, collecting money,
     * replenishing money, printing a summary of transactions, and displaying
     * the inventory.
     * @throws InterruptedException if the thread is interrupted during sleep
     */
    public void maintenance() throws InterruptedException{
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
            System.out.println("[7] Exit");
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
                    break;

                default: System.out.println("INVALID CHOICE!");
            }
        }while(choice != 7);
    }
    /**
     * Prints a summary of the transactions made in the vending machine.
     * It calculates the total price, quantity sold, and total price for each item,
     * and prints the information for each item along with the total price sold
     * for all items.
     */
    public void printSummaryTransaction() {
        System.out.println("\n\n===== SUMMARY OF TRANSACTION =====");
        int total = 0;
        Map<String, Integer> itemQuantities = new HashMap<>();
        Map<String, Integer> itemPrices = new HashMap<>();

        for (Log log : Sales) {
            //produces and displays the sales' name and price that are sold
            System.out.println("Name: " + log.getName());
            System.out.println("Price: " + log.getPrice());
            System.out.println("==========================");
            total += log.getPrice();

            String itemName = log.getName();
            int itemQuantity = log.getQuan();
            itemQuantities.put(itemName, itemQuantities.getOrDefault(itemName, 0) + itemQuantity);
            itemPrices.put(itemName, log.getPrice());
        }

        for (String itemName : itemQuantities.keySet()) {
            int itemQuantity = itemQuantities.get(itemName);
            int itemPrice = itemPrices.getOrDefault(itemName, 0);
            int itemTotalPrice = itemQuantity * itemPrice;
            //gets the total quantity of each item that were sold
            System.out.println(itemName + " Quantity Sold: " + itemQuantity);
            System.out.println(itemName + " Total Price: " + itemTotalPrice);
            System.out.println("==========================");
        }

        System.out.println("Total Price Sold for All Item: " + total);
    }
    /**
     * Retrieves the name of the vending machine.
     * @return the name of the vending machine
     */
    public String getName(){
        return name;
    }

    protected Map<Ingredient, Integer> getSlots() {
        return slots;
    }
    /**
     * Retrieves the type of the vending machine.
     * @return the type of the vending machine
     */
    public String getType(){
        return type;
    }
}