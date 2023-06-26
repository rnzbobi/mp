import java.util.*;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        HashMap<Ingredient, Integer> slots = new HashMap<>();
        int number = 0;
        String name, type;
        Scanner sc = new Scanner(System.in);
        VendingMachine vendingmachine = new VendingMachine();
        boolean vendingMachineCreated = false;
        int choice;
        do {
            System.out.println("Choose an option: tite");
            System.out.println("1. Create a Vending Machine");
            System.out.println("2. Test Vending Machine");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    if (vendingMachineCreated) {
                        System.out.println("Vending machine has already been created!");
                        break;
                    }

                    Ingredient EspressoRoast = new Ingredient("Espresso Roast", 999, 5);
                    Ingredient Milk = new Ingredient("Milk", 99, 149);
                    Ingredient VanillaSyrup = new Ingredient("Vanilla Syrup", 49, 77);
                    Ingredient CaramelSyrup = new Ingredient("Caramel Syrup", 49, 30);
                    Ingredient ChocolateChips = new Ingredient("Chocolate Chips", 29, 136);
                    Ingredient WhippedCream = new Ingredient("Whipped Cream", 199, 72);
                    Ingredient MilkWhisk = new Ingredient("Milk Whisk", 59, 149);
                    Ingredient HotWater = new Ingredient("Hot Water", 1, 12);
                    Ingredient Cinnamon = new Ingredient("Cinnamon", 189, 19);
                    Ingredient Espresso = new Ingredient("Espresso", 349, 3);

                    System.out.print("Enter the name of your Coffee Vending Machine: ");
                    name = sc.next();
                    sc.nextLine(); // Consume the newline character
                    do {
                        System.out.print("Type of Coffee Vending Machine [Regular] | [Special]: ");
                        type = sc.next();
                        sc.nextLine(); // Consume the newline character
                    } while (!(type.equalsIgnoreCase("Regular")) && !(type.equalsIgnoreCase("Special")));

                    System.out.print("How many items do you want for all?:");
                    while (!sc.hasNextInt() || (number = sc.nextInt()) < 10) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    sc.nextLine();

                    slots.put(EspressoRoast, number);
                    slots.put(Milk, number);
                    slots.put(VanillaSyrup, number);
                    slots.put(CaramelSyrup, number);
                    slots.put(ChocolateChips, number);
                    slots.put(WhippedCream, number);
                    slots.put(MilkWhisk, number);
                    slots.put(HotWater, number);
                    slots.put(Cinnamon, number);
                    slots.put(Espresso, number);

                    vendingmachine.createVendingMachine(name, type, slots);
                    vendingMachineCreated = true;
                    break;

                case 2:
                    System.out.println("You chose Option 2: Test Vending Machine");
                    if (vendingMachineCreated) {
                        vendingmachine.testVendingMachine();
                        System.out.println("\n");
                    } else {
                        System.out.println("\n[ERROR] You don't have a vending machine to test!");
                    }
                    break;

                case 3:
                    System.out.print("\nExiting Program");
                    Thread.sleep(1000);
                    System.out.print(". ");
                    Thread.sleep(1000);
                    System.out.print(". ");
                    Thread.sleep(1000);
                    System.out.print(". \n");
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 3);
        sc.close();
    }
}