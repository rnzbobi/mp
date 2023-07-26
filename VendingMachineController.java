import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class VendingMachineController implements ActionListener{
	private VendingMachineView view;
	private VendingMachine vendingMachineModel;
	HashMap<Ingredient, Integer> slots = new HashMap<>();
	private boolean vendingMachineCreated;
	
	public VendingMachineController(VendingMachineView view) {
		this.view = view;
		this.vendingMachineCreated = false;
		this.vendingMachineModel = new VendingMachine();
		attachListeners();
	}
	
	private void attachListeners() {
        view.addCoffeeVendingListener(this);
        view.addOwnVendingListener(this);
        view.addTestVendingListener(this);
        view.addExitVendingListener(this);
		view.addNameTextFieldListener(this);
        view.addTypeTextFieldListener(this);
        view.addNumberOfItemsTextFieldListener(this);
		view.addCreateListener(this);
		view.addSubmitBankListener(this);
		view.addCalculatorButtonListener(this);
		view.addCalculatorTextFieldListener(this);
		view.addSubmitButtonListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == view.getCoffeeVendingButton()) {
			if (vendingMachineCreated) {
				JOptionPane.showMessageDialog(view, "There is already a vending machine!");
			} else {
				view.showMainMenuFields(false);
				view.showCoffeeVendingFields(true);
				// Do NOT call createCoffeeVendingMachine() here
			}
		} else if (source == view.getOwnVendingButton()) {
			// Handle Own Vending Machine button action
			// ...
		} else if (source == view.getTestVendingButton()) {
			view.showMainMenuFields(false); // Hide the main menu buttons
			view.showDisplayMenuFields(true);
			view.displayAvailableItems(vendingMachineModel.RegularVendingMachine.getSlots()); // Assuming getSlots() returns the slots from the model
		} else if (source == view.getExitButton()) {
			System.exit(0);
		} else if (source == view.getNameTextField()) {
			// Handle user input from nameTextField
			// ...
		} else if (source == view.getTypeTextField()) {
			String typeInput = view.getTypeInput().toLowerCase(); // Convert to lowercase to handle case-insensitive input

			if (!typeInput.equals("regular") && !typeInput.equals("special")) {
				// If the input is neither "regular" nor "special", show an error message
				JOptionPane.showMessageDialog(view, "Invalid type! Please enter either 'regular' or 'special'.");
				view.getTypeTextField().setText(""); // Clear the text field
			}
		} else if (source == view.getNumberOfItemsTextField()) {
			// Handle the number of items text field event here
			// ...
		} else if (source == view.getCreateButton()) {
			view.showCoffeeVendingFields(false);
			view.showBankDetails(true);
		} else if (source == view.getSubmitBankButton()) {
			createCoffeeVendingMachine();
			view.showBankDetails(false);
			view.showMainMenuFields(false);
			view.showTestMenuFields(true);
		} else if (source == view.getSubmitButton()){
			//
		} else if (source == view.getCalculatorButtons()){

		} else if (source == view.getCalculatorTextField()){

		}
	}
	
	private void createCoffeeVendingMachine(){
        // ... (existing code for creating a coffee vending machine)
		Ingredient EspressoRoast = new Ingredient("Espresso Roast", 999, 5, "1.png");
        Ingredient Milk = new Ingredient("Milk", 99, 149, "6.png");
        Ingredient VanillaSyrup = new Ingredient("Vanilla Syrup", 49, 77, "9.png");
        Ingredient CaramelSyrup = new Ingredient("Caramel Syrup", 49, 30, "7.png");
        Ingredient ChocolateChips = new Ingredient("Chocolate Chips", 29, 136, "5.png");
        Ingredient WhippedCream = new Ingredient("Whipped Cream", 199, 72, "3.png");
        Ingredient MilkWhisk = new Ingredient("Milk Whisk", 59, 149, "8.png");
        Ingredient HotWater = new Ingredient("Hot Water", 1, 12, "4.png");
        Ingredient Cinnamon = new Ingredient("Cinnamon", 189, 19, "2.png");
        Ingredient Espresso = new Ingredient("Espresso", 349, 3, "10.png");

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
		
		int numberOfItems = 0;
		try {
        numberOfItems = Integer.parseInt(view.getNumberOfItemsInput());
			if (numberOfItems < 10) {
				JOptionPane.showMessageDialog(view, "Invalid input. Please enter a number greater than or equal to 10.");
				return; // Exit the method if the input is less than 10
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(view, "Invalid input. Please enter a valid number.");
			return; // Exit the method if the input is not a valid number
		}
		
		slots.put(EspressoRoast, numberOfItems);
        slots.put(Milk, numberOfItems);
        slots.put(VanillaSyrup, numberOfItems);
        slots.put(CaramelSyrup, numberOfItems);
        slots.put(ChocolateChips, numberOfItems);
        slots.put(WhippedCream, numberOfItems);
        slots.put(MilkWhisk, numberOfItems);
        slots.put(HotWater, numberOfItems);
        slots.put(Cinnamon, numberOfItems);
        slots.put(Espresso, numberOfItems);

        Dish CaramelMacchiato = new Dish("Caramel Macchiato", numberOfItems, 210, 338, CM);
        Dish WhiteChocolateMocha = new Dish("White Chocolate Mocha", numberOfItems, 210, 634, WCM);
        Dish CaffeAmericano = new Dish("Caffe Americano", numberOfItems, 165, 17, CA);
        Dish CaffeLatte = new Dish("Caffe Latte", numberOfItems, 190, 457, CL);
        Dish Cappucino = new Dish("Cappucino", numberOfItems, 165, 193, CAP);

        ArrayList<Dish> dishList = new ArrayList<Dish>();
        dishList.add(CaramelMacchiato);
        dishList.add(WhiteChocolateMocha);
        dishList.add(CaffeAmericano);
        dishList.add(CaffeLatte);
        dishList.add(Cappucino);
        // Create the coffee vending machine using the model
		
		String[] denominationInputs = view.getDenominationInputs();
		HashMap<Integer, Integer> bankDetails = new HashMap<>();
		int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
		for (int i = 0; i < 9; i++) {
			try {
				int numBillsCoins = Integer.parseInt(denominationInputs[i]);
				bankDetails.put(denominations[i], numBillsCoins);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(view, "Invalid input for denomination " + denominations[i] + ". Please enter a valid number.");
				return; // Exit the method if any denomination input is not valid
			}
		}
        vendingMachineModel.createVendingMachine(view.getNameInput(), view.getTypeInput(), slots, dishList, bankDetails);
        vendingMachineCreated = true;

        JOptionPane.showMessageDialog(view, "Hi Vending Machine [" + view.getNameInput() + "]!");
    }
}