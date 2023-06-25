import java.util.*;

public class VendingMachine {
    private SpecialVendingMachine SpecialVendingMachine;
    private RegularVendingMachine RegularVendingMachine;
    private HashMap<Integer,Integer> money = new HashMap<>();
    private HashMap<Integer,Integer> usermoney = new HashMap<>();
    private Bank bank;
    private final static String[] types = {"Regular", "Special"};
    private final static int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};

    public void createVendingMachine(String name, String type, HashMap<Ingredient,Integer> slots) throws InterruptedException {
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
                System.out.print("Creating a Regular vending machine ");
                //Thread.sleep(1000);
                System.out.print(". ");
                //Thread.sleep(1000);
                System.out.print(". ");
                //Thread.sleep(1000);
                System.out.print(". \n");
                     int total = 0, usertotal = 0;
                     System.out.println("===== YOUR BANK =====");
                     System.out.println("[Insert the number of Bills/Coins to your BANK]");
                        for (int denomination : denominations) {
                            System.out.println(denomination + " Peso Bills/Coins [INSERT]");
                            int temp = Integer.parseInt(sc.nextLine());
                            money.put(denomination, temp);
                            usermoney.put(denomination,0);
                            total += money.get(denomination) * denomination;
                        }
                            System.out.println("\nTotal Money Inserted for Change is [" + total + "]");
                            bank = new Bank(money,usermoney,total,usertotal);
                            RegularVendingMachine regular = new RegularVendingMachine(name,slots,bank);
                            this.RegularVendingMachine = regular;
                            //Thread.sleep(1500);
                            RegularVendingMachine.displayStartingInventory();
                            //Thread.sleep(1500);
                            System.out.println("\n\n[VENDING MACHINE: ONLINE]");
                            RegularVendingMachine.displayAvailableItem();

                        } else if (type.equalsIgnoreCase("Special")) {
                            System.out.println("Creating a Special vending machine.");
                            //Thread.sleep(2000);
                            // Perform actions for a Special vending machine
                        }
                    } else {
                        System.out.println("Invalid vending machine type.");
                    }
    }
    
    public void testVendingMachine() throws InterruptedException {
        String itemname, userchoice, userchoice2;
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Coffee Vending Machine [" + RegularVendingMachine.getName() + "]!");
        //Thread.sleep(1500);
        System.out.println("This is a [" + RegularVendingMachine.getType() + "] type of vending machine\n");
        //Thread.sleep(1500);
        do{
            System.out.println("[1] Test Vending Features");
            System.out.println("[2] Maintenance Features");
            System.out.println("[3] Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(sc.nextLine());

            if(choice == 1){
                do{
                    System.out.println("CURRENT BALANCE OF THE VENDING MACHINE [" + bank.getTotalMoney() + "]");
                    //Thread.sleep(500);
                    System.out.println("CURRENT BALANCE OF THE USER [" + bank.getUserTotalMoney() + "]");
                    //Thread.sleep(500);
                    System.out.println("These are all the available items:\n");
                    RegularVendingMachine.displayAvailableItem();
                    //Thread.sleep(3000);
                    System.out.println("\n\nWould you like to order an item? (Y/N): ");
                    userchoice = sc.nextLine();
                    if(userchoice.equalsIgnoreCase("Y")) {
                        System.out.println("\nPlease insert money: ");
                        RegularVendingMachine.insertMoney();
                        System.out.println("Your current balance is :[" + bank.getUserTotalMoney() + "]");
                        if (bank.getUserTotalMoney() <= 0) {
                            System.out.println("You do not have enough balance!");
                            System.out.print("Would you like to insert money? (Y/N): ");
                            userchoice = sc.nextLine();
                            if (userchoice.equalsIgnoreCase("Y")) {
                                RegularVendingMachine.insertMoney();
                            } else {
                                System.out.println("Exiting Vending Machine");
                                return;
                            }
                        } else {
                            RegularVendingMachine.displayAvailableItem();
                            System.out.println("\n[A] Continue to Select Item");
                            System.out.println("[B] Return Money");
                            System.out.print("Select an option: ");
                            userchoice2 = sc.nextLine();
                            if (userchoice2.equalsIgnoreCase("A")) {
                                boolean itemSelected = false;
                                while (!itemSelected) {
                                    System.out.print("\nPlease select an item from the choices above: ");
                                    itemname = sc.nextLine();
                                    itemSelected = RegularVendingMachine.selectItem(itemname);
                                }
                            } else {
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
                        }
                    }
                    else{
                        return;
                    }
                }while(!(userchoice.equalsIgnoreCase("N") || userchoice.equalsIgnoreCase("B")));
            }
            else if(choice == 2){
                RegularVendingMachine.maintenance();
            }
            else if(choice == 3){
                return;
            }
            else{
                System.out.println("Invalid choice!");
            }
        } while (choice < 1 || choice > 3);

    }

    public void exit(){
        System.exit(0);
    }
}