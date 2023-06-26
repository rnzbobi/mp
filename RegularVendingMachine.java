import java.text.DecimalFormat;
import java.util.*;

public class RegularVendingMachine {
    private final String name;
    private final String type = "Regular";
    private static HashMap<Ingredient, Integer> slots;
    private Bank bank;
    private HashMap<String, Integer> transactions;
    private Inventory[] inventory;
    private ArrayList<Log> Sales;
    Scanner sc = new Scanner(System.in);

    public RegularVendingMachine(String name, HashMap<Ingredient,Integer> slots2, Bank bank){
        this.name = name;
        this.bank = bank;
        RegularVendingMachine.slots = slots2;
        this.inventory = new Inventory[2];
        inventory[0] = new Inventory();
        inventory[1] = new Inventory();
        inventory[0].setStocks(slots2);
        inventory[1].setStocks(slots2);
        this.Sales = new ArrayList<>();
    }

    public boolean selectItem(String name) throws InterruptedException{
        boolean found = false;
        int price, total = 0, totalowner;
        HashMap<Ingredient, Integer> availableitems = inventory[1].getStocks();
        for(Map.Entry<Ingredient, Integer> items : availableitems.entrySet()){
            Ingredient ingredient = items.getKey();
            int quantity = items.getValue();
            price = ingredient.getPrice();
            if(name.equalsIgnoreCase(ingredient.getName()) && price <= bank.getUserTotalMoney() && quantity > 0){
                System.out.println("You have selected the item [" + ingredient.getName() + "]\n");
                System.out.println("The calories of this item is: " + ingredient.getCalories() + "\n");
                quantity--;
                slots.put(ingredient,quantity);
                total = bank.getUserTotalMoney() - price;
                bank.updateUserTotalMoney(total);
                totalowner = bank.getTotalMoney() + price;
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
                found = true;
                Log sale = new Log(ingredient.getName(),price,1);
                Sales.add(sale);
                System.out.println("------------------------------");
                System.out.println("         RECEIPT");
                System.out.println("------------------------------");
                System.out.println("Item: " + ingredient.getName());
                System.out.println("Price: Php " + price);
                System.out.println("Tendered : " + (total + price));
                System.out.println("Change : " + bank.getTotalMoney());
                System.out.println("------------------------------");
                System.out.println("Thank you for your purchase!\n");
                return true;
            }
            else if(name.equalsIgnoreCase(ingredient.getName()) && price > bank.getUserTotalMoney()){
                System.out.println("[Error Processing: INSUFFICIENT FUNDS] Please insert money.");
                insertMoney();
                return false;
                }
            else if(name.equalsIgnoreCase(ingredient.getName()) && quantity <= 0){
                System.out.println("[Error Processing: INSUFFICIENT QUANTITY]");
                return false;
            }
        }
        if(!found) {
            System.out.println("[ERROR] The item you have selected is invalid!");
            return false;
        }

        return false;
    }

    public void insertMoney() {
        int i = 0;
        int i2 = 0;
        int usertotal = 0;
    
        do {
        i = 0;
        i2 = 0;
        System.out.println("[Choose a number that corresponds to your Bills/Coins] [Balance: " + bank.getUserTotalMoney() + "]");
        System.out.println("|1| 1000 Pesos |2| 500 Pesos |3| 200 Pesos");
        System.out.println("|4| 100 Pesos  |5| 50 Pesos  |6| 20 Pesos");
        System.out.println("|7| 10 Pesos   |8| 5 Pesos   |9| 1 Pesos");
        System.out.println("|10| EXIT");
        System.out.print("[Enter] ");
        i = Integer.parseInt(sc.nextLine());
        
        if (i == 10) {
            break;
        }
        
        System.out.print("How many: ");
        i2 = Integer.parseInt(sc.nextLine());

        switch (i) {
            case 1 -> {
                bank.updateUserMoney(1000, i2);
                usertotal += 1000 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 2 -> {
                bank.updateUserMoney(500, i2);
                usertotal += 500 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 3 -> {
                bank.updateUserMoney(200, i2);
                usertotal += 200 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 4 -> {
                bank.updateUserMoney(100, i2);
                usertotal += 100 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 5 -> {
                bank.updateUserMoney(50, i2);
                usertotal += 50 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 6 -> {
                bank.updateUserMoney(20, i2);
                usertotal += 20 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 7 -> {
                bank.updateUserMoney(10, i2);
                usertotal += 10 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 8 -> {
                bank.updateUserMoney(5, i2);
                usertotal += 5 * i2;
                bank.updateUserTotalMoney(usertotal);
            }
            case 9 -> {
                bank.updateUserMoney(1, i2);
                usertotal += i2;
                bank.updateUserTotalMoney(usertotal);
            }
        }
    } while (i != 10);
    
}

    public void replenishMoney() {
        int i = 0;
        int i2 = 0;
        int total = bank.getTotalMoney();

        do {
            i = 0;
            i2 = 0;
            System.out.println("[Choose a number that corresponds to your Bills/Coins] [Balance: " + bank.getTotalMoney() + "]");
            System.out.println("|1| 1000 Pesos |2| 500 Pesos |3| 200 Pesos");
            System.out.println("|4| 100 Pesos  |5| 50 Pesos  |6| 20 Pesos");
            System.out.println("|7| 10 Pesos   |8| 5 Pesos   |9| 1 Pesos");
            System.out.println("|10| EXIT");
            System.out.print("[Enter] ");
            i = Integer.parseInt(sc.nextLine());

            if (i == 10) {
                break;
            }

            System.out.print("How many: ");
            i2 = Integer.parseInt(sc.nextLine());

            switch (i) {
                case 1 -> {
                    bank.updateMoney(1000, i2);
                    total += 1000 * i2;
                    bank.updateTotalMoney(total);
                }
                case 2 -> {
                    bank.updateMoney(500, i2);
                    total += 500 * i2;
                    bank.updateTotalMoney(total);
                }
                case 3 -> {
                    bank.updateMoney(200, i2);
                    total += 200 * i2;
                    bank.updateTotalMoney(total);
                }
                case 4 -> {
                    bank.updateMoney(100, i2);
                    total += 100 * i2;
                    bank.updateTotalMoney(total);
                }
                case 5 -> {
                    bank.updateMoney(50, i2);
                    total += 50 * i2;
                    bank.updateTotalMoney(total);
                }
                case 6 -> {
                    bank.updateMoney(20, i2);
                    total += 20 * i2;
                    bank.updateTotalMoney(total);
                }
                case 7 -> {
                    bank.updateMoney(10, i2);
                    total += 10 * i2;
                    bank.updateTotalMoney(total);
                }
                case 8 -> {
                    bank.updateMoney(5, i2);
                    total += 5 * i2;
                    bank.updateTotalMoney(total);
                }
                case 9 -> {
                    bank.updateMoney(1, i2);
                    total += i2;
                    bank.updateTotalMoney(total);
                }
            }
        } while (i != 10);
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
        int i = 1;
        int maxItemNameLength = 0;
    
        for (Ingredient item : slots.keySet()) {
            int itemNameLength = item.getName().length();
            if (itemNameLength > maxItemNameLength) {
            maxItemNameLength = itemNameLength;
            }
        }
    
        int itemInfoWidth = maxItemNameLength + 25;
    
        for (Map.Entry<Ingredient, Integer> entry : slots.entrySet()) {
            Ingredient item = entry.getKey();
            int quantity = entry.getValue();
            String itemName = item.getName();
            int itemPrice = item.getPrice();
            i++;
        
            if (i % 2 == 0) {
                System.out.println("\n");
            }

            if(quantity >= 1) {
                String itemInfo = "[" + quantity + "]" + itemName + " | Price: Php " + decimalFormat.format(itemPrice);
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            }
            else {
                String itemInfo = "[SOLD OUT]" + itemName + " | Price: Php " + decimalFormat.format(itemPrice);
                String formattedItemInfo = String.format("%-" + itemInfoWidth + "s", itemInfo);
                System.out.print(formattedItemInfo);
            }
        }

        System.out.println("\n\n");
    }

    public void maintenance() throws InterruptedException{
        HashMap<Ingredient, Integer> availableitems = inventory[1].getStocks();
        String itemname;
        int choice, replenish, setprice;
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
                    found = false;
                    displayAvailableItem();
                    System.out.print("\nEnter the item that you wish to replenish: ");
                    itemname = sc.nextLine();

                    for (Map.Entry<Ingredient, Integer> items : availableitems.entrySet()) {
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
                        System.out.println("[ERROR] Item does not exist");
                    }
                    break;
                case 2:
                    found = false;
                    displayAvailableItem();
                    System.out.print("\nEnter the item that you wish to change price: ");
                    itemname = sc.nextLine();

                    for (Map.Entry<Ingredient, Integer> items : availableitems.entrySet()) {
                        Ingredient ingredient = items.getKey();
                        int quantity = items.getValue();
                        int price = ingredient.getPrice();
                        if (itemname.equalsIgnoreCase(ingredient.getName())) {
                            System.out.print("How much would you like to set this product? ");
                            setprice = Integer.parseInt(sc.nextLine());
                            ingredient.setPrice(setprice);
                        }
                    }
                    if(!found){
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
                    bank.updateTotalMoney(0);
                    bank.updateMoney(1000,0);
                    bank.updateMoney(500,0);
                    bank.updateMoney(200,0);
                    bank.updateMoney(100,0);
                    bank.updateMoney(50,0);
                    bank.updateMoney(20,0);
                    bank.updateMoney(10,0);
                    bank.updateMoney(5,0);
                    bank.updateMoney(1,0);
                    System.out.print("\nMoney has been dispensed.");
                    break;

                case 4:

                    break;

                case 5:
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

    public void printSummaryTransaction() {
        System.out.println("===== SUMMARY OF TRANSACTION =====");
        int total = 0;
        Map<String, Integer> itemQuantities = new HashMap<>();
        Map<String, Integer> itemPrices = new HashMap<>();

        for (Log log : Sales) {
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

            System.out.println(itemName + " Quantity Sold: " + itemQuantity);
            System.out.println(itemName + " Total Price: " + itemTotalPrice);
            System.out.println("==========================");
        }

        System.out.println("Total Price Sold for All Item: " + total);
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }
}