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
            System.out.println("Choose an option: ");
            System.out.println("1. Coffee Vending Machine");
			System.out.println("2. Create your own Vending Machine");
			System.out.println("3. Test Vending Machine");
            System.out.println("4. Exit");
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

                    HashMap<Ingredient, Integer> CM = new HashMap<>();
                    CM.put(EspressoRoast,1);
                    CM.put(Milk,1);
                    CM.put(VanillaSyrup,2);
                    CM.put(CaramelSyrup,1);

                    HashMap<Ingredient, Integer> WCM = new HashMap<>();
                    WCM.put(EspressoRoast,1);
                    WCM.put(ChocolateChips,3);
                    WCM.put(Milk,1);
                    WCM.put(WhippedCream,1);

                    HashMap<Ingredient, Integer> CA = new HashMap<>();
                    CA.put(HotWater,1);
                    CA.put(EspressoRoast,1);

                    HashMap<Ingredient, Integer> CL = new HashMap<>();
                    CL.put(EspressoRoast,1);
                    CL.put(Milk,1);
                    CL.put(MilkWhisk,1);
                    CL.put(VanillaSyrup,2);

                    HashMap<Ingredient, Integer> CAP = new HashMap<>();
                    CAP.put(Espresso,2);
                    CAP.put(Milk,1);
                    CAP.put(Cinnamon,2);

                    System.out.print("Enter the name of your Coffee Vending Machine: ");
                    name = sc.nextLine();
                    do {
                        System.out.print("Type of Coffee Vending Machine [Regular] | [Special]: ");
                        type = sc.nextLine();
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

                    Dish CaramelMacchiato = new Dish("Caramel Macchiato", number, 338, 210, CM);
                    Dish WhiteChocolateMocha = new Dish("White Chocolate Mocha", number, 634, 210, WCM);
                    Dish CaffeAmericano = new Dish("Caffe Americano", number, 17, 165, CA);
                    Dish CaffeLatte = new Dish("Caffe Latte", number, 457, 190, CL);
                    Dish Cappucino = new Dish("Cappucino", number, 193, 165, CAP);

                    ArrayList<Dish> dishList = new ArrayList<Dish>();
                    dishList.add(CaramelMacchiato);
                    dishList.add(WhiteChocolateMocha);
                    dishList.add(CaffeAmericano);
                    dishList.add(CaffeLatte);
                    dishList.add(Cappucino);

                    vendingmachine.createVendingMachine(name, type, slots, dishList);
                    vendingMachineCreated = true;
                    break;

                case 2:
                    if (vendingMachineCreated) {
                        System.out.println("Vending machine has already been created!");
                        break;
                    }

                    System.out.print("Enter the name of your Vending Machine: ");
                    name = sc.nextLine();
                    do {
                        System.out.print("Type of Vending Machine [Regular] | [Special]: ");
                        type = sc.nextLine();
                    } while (!(type.equalsIgnoreCase("Regular")) && !(type.equalsIgnoreCase("Special")));

                    System.out.print("How many slots do you want for the vending machine?: ");
                    while (!sc.hasNextInt() || (number = sc.nextInt()) < 8) {
                        System.out.println("Invalid input. Please enter a number greater than or equal to 8.");
                        sc.nextLine(); // Clear the input buffer
                    }
                    sc.nextLine();

                    HashMap<Ingredient, Integer> customSlots = new HashMap<>();
					
                    for (int i = 1; i <= number; i++) {
                        System.out.print("Enter the name of ingredient for slot " + i + ": ");
                        String ingredientName = sc.nextLine();

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
                        customSlots.put(ingredient, ingredientQuantity);
                    }
                    ArrayList<Dish> dishList2 = new ArrayList<Dish>();
                    vendingmachine.createVendingMachine(name, type, customSlots, dishList2);
                    vendingMachineCreated = true;
                    break;
					
				case 3:
				    System.out.println("You chose Option 3: Test Vending Machine");
                    if (vendingMachineCreated) {
                        vendingmachine.testVendingMachine();
                        System.out.println("\n");
                    } else {
                        System.out.println("\n[ERROR] You don't have a vending machine to test!");
                    }
                    break;
					
                case 4:
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
            
        } while (choice != 4);
        sc.close();
    }
}