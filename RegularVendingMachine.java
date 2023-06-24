import java.text.DecimalFormat;
import java.util.*;

public class RegularVendingMachine {
    private final String name;
    private final String type = "Regular";
    private static HashMap<Ingredient, Integer> slots;
    private Bank bank;
    private HashMap<String, Integer> transactions;
    private Inventory[] inventory;
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
    }

    public boolean selectItem(String name) {
        boolean found = false;
        int price, total = 0;
        HashMap<Ingredient, Integer> availableitems = inventory[1].getStocks();
        for(Map.Entry<Ingredient, Integer> items : availableitems.entrySet()){
            Ingredient ingredient = items.getKey();
            int quantity = items.getValue();
            price = ingredient.getPrice();
            if(name.equalsIgnoreCase(ingredient.getName()) == true && price <= bank.getUserTotalMoney()){
                if(price >= bank.getUserTotalMoney()){
                    System.out.println("You do not have enough balance!");
                    return found;
                }
                System.out.println("You have selected the item [" + ingredient.getName() + "]\n");
                System.out.println("The calories of this item is: " + ingredient.getCalories() + "\n");
                quantity--;
                slots.put(ingredient,quantity);
                total = bank.getUserTotalMoney() - price;
                bank.updateUserTotalMoney(total);
                found = true;
                return true;
            }
        }
        if(found == false) {
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

        switch(i){
            case 1: bank.updateUserMoney(1000, i2);
                    usertotal += 1000 * i2;
                    bank.updateUserTotalMoney(usertotal);
                    break;
            case 2: bank.updateUserMoney(500, i2);
                    usertotal += 500 * i2;
                    bank.updateUserTotalMoney(usertotal);
                    break;
            case 3: bank.updateUserMoney(200, i2);
                    usertotal += 200 * i2;
                    bank.updateUserTotalMoney(usertotal);
                    break;
            case 4: bank.updateUserMoney(100, i2);
                    usertotal += 100 * i2;
                    bank.updateUserTotalMoney(usertotal);
                    break;
            case 5: bank.updateUserMoney(50, i2);
                    usertotal += 50 * i2;
                    bank.updateUserTotalMoney(usertotal);
                    break;
            case 6: bank.updateUserMoney(20, i2);
                    usertotal += 20 * i2;
                    bank.updateUserTotalMoney(usertotal);
                    break;
            case 7: bank.updateUserMoney(10, i2);
                    usertotal += 10 * i2;
                    bank.updateUserTotalMoney(usertotal);
                    break;
            case 8: bank.updateUserMoney(5, i2);
                    usertotal += 5 * i2;
                    bank.updateUserTotalMoney(usertotal);
                    break;
            case 9: bank.updateUserMoney(1, i2);
                    usertotal += 1 * i2;
                    bank.updateUserTotalMoney(usertotal);
                    break;
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
    int i = 0;
    StringBuilder stringBuilder = new StringBuilder();

    for (Map.Entry<Ingredient, Integer> entry : slots.entrySet()) {
        Ingredient item = entry.getKey();
        int quantity = entry.getValue();
        String itemName = item.getName();
        int itemPrice = item.getPrice();
        i++;
        stringBuilder.append("[").append(itemName).append("] ")
                .append(itemName).append(" [Php").append(decimalFormat.format(itemPrice)).append("]");

        if (i % 2 == 0) {
            stringBuilder.append(" | ");
        } else {
            stringBuilder.append("\n");
        }
    }

    System.out.print(stringBuilder.toString());
}

    public void maintenance() {

    }

    public void printTransaction() {

    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }
}