import java.util.*;

public class VendingMachine {
    private ArrayList<Slot> slots;
    private static HashMap<Integer,Integer> money = new HashMap<>();
    private HashMap<String, Integer> transactions;
    private final static String[] types = {"Regular", "Special"};
    private final static int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};

    public void testVendingMachine() {
        // Implementation here
    }

    public void createVendingMachine(String name) {
        // Implementation here
    }

    public void exit(){
        System.exit(0);
    }

    public static void main(String[] args) {
        int choice, itemcount;
        String type;
        ArrayList<Slot> slots = new ArrayList<Slot>();
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Choose an option: ");
            System.out.println("1. Create a Vending Machine");
            System.out.println("2. Test Vending Machine");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("You chose Option 1: Create a Vending Machine burat");
                    System.out.println("Enter which type of vending machine to create (Regular or Special): ");
                    type = sc.nextLine();

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
                            do{
                            System.out.println("How many items would you want in your vending machine? [Minimum of 8]: ");
                            itemcount = Integer.parseInt(sc.nextLine());
                            } while(itemcount < 8);
                            
                            slots = new ArrayList<>();

                            for (int i = 0; i < itemcount; i++) {
                                System.out.println("Enter details for item " + (i + 1) + ":");
                                System.out.println("[Name,Price,Calories]");
                                String input = sc.nextLine();
                                String[] parts = input.split(",");
                                int price = 0;
                                int calories = 0;
                                String name;
                                if(parts.length == 3) {
                                    name = parts[0];
                                    price = Integer.parseInt(parts[1]);
                                    calories = Integer.parseInt(parts[2]);
                                }
                                else{
                                    System.out.println("Invalid input format. Please try again.");
                                    i--;
                                    break;
                                }
                                int quantity = 0;
                                do{
                                    System.out.println("Quantity [Minimum of 10]: ");
                                    quantity = Integer.parseInt(sc.nextLine());                     
                                } while(quantity < 10);

                                Ingredient ingredient = new Ingredient(name, price, calories);
                                Slot slot = new Slot(quantity);
                                slot.setIngredient(ingredient);
                                slots.add(slot);
                            }
                           int total = 0;

                            System.out.println("===== BANK =====");
                            for (int denomination : denominations) {
                                System.out.println("Number of " + denomination + " Peso Bills/Coins [INSERT]");
                                int temp = Integer.parseInt(sc.nextLine());
                                money.put(denomination, temp);
                                total += money.get(denomination) * denomination;
                            }
                            System.out.println("Total Money Inserted for Change is [" + total + "]");

                            RegularVendingMachine regular = new RegularVendingMachine(slots);
                            regular.displayInventory();

                        } else if (type.equalsIgnoreCase("Special")) {
                            System.out.println("Creating a Special vending machine.");
                            // Perform actions for a Special vending machine
                        }
                    } else {
                        System.out.println("Invalid vending machine type.");
                    }
                    break;

                case 2:
                    System.out.println("You chose Option 2: Test Vending Machine");
                    // Perform actions for testing the vending machine
                    break;

                case 3:
                    System.out.println("Exiting Program....");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 1 && choice != 2 && choice != 3);
        sc.close();
    }
}