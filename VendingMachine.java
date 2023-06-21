import java.util.*;

public class VendingMachine {
    private SpecialVendingMachine testSpecial;
    private RegularVendingMachine testRegular;
    private static HashMap<Integer,Integer> money = new HashMap<>();
    private final static String[] types = {"Regular", "Special"};
    private final static int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
    
    public void testVendingMachine() {
        String itemname;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of your desired item: ");
        itemname = sc.nextLine();

        testRegular.selectItem(itemname);
    }

    public void createVendingMachine(String name, String type, HashMap<Ingredient,Integer> slots) {
        Scanner sc = new Scanner(System.in);
        System.out.println("You chose Option 1: Hi Vending Machine [" + name + "]!");

        boolean isTypeValid = false;
            for (String validType : types) {
                if (validType.equalsIgnoreCase(type)) {
                    isTypeValid = true;
                    break;
                }
            }

        if (isTypeValid){
            if (type.equalsIgnoreCase("Regular")) {
                System.out.println("Creating a Regular vending machine...");
                     int total = 0;
                     System.out.println("===== YOUR BANK =====");
                     System.out.println("[Insert the number of Bills/Coins to your BANK]");
                        for (int denomination : denominations) {
                            System.out.println(denomination + " Peso Bills/Coins [INSERT]");
                            int temp = Integer.parseInt(sc.nextLine());
                            money.put(denomination, temp);
                            total += money.get(denomination) * denomination;
                        }
                            System.out.println("\nTotal Money Inserted for Change is [" + total + "]");
                            RegularVendingMachine regular = new RegularVendingMachine(slots);
                            this.testRegular = regular;
                            regular.displayStartingInventory();
                            System.out.println("\n\n[VENDING MACHINE: ONLINE]");
                            regular.displayAvailableItem();

                        } else if (type.equalsIgnoreCase("Special")) {
                            System.out.println("Creating a Special vending machine.");
                            // Perform actions for a Special vending machine
                        }
                    } else {
                        System.out.println("Invalid vending machine type.");
                    }
    }

    public void exit(){
        System.exit(0);
    }
}