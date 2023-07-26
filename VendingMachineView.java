import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class VendingMachineView extends JFrame{
	private JButton coffeeVendingButton = new JButton("Coffee Vending Machine");
	private JButton ownVendingButton = new JButton("Own Vending Machine");
	private JButton testVendingButton = new JButton("Test Vending Machine");
	private JButton exitButton = new JButton("EXIT");
	private JButton createButton = new JButton("Create");
	
	private JTextField nameTextField = new JTextField(10);
    private JTextField typeTextField = new JTextField(10);
    private JTextField numberOfItemsTextField = new JTextField(10);
	
	private JLabel nameLabel = new JLabel("Enter the name of your Coffee Vending Machine:");
    private JLabel typeLabel = new JLabel("Type of Coffee Vending Machine [Regular] | [Special]:");
    private JLabel numberOfItemsLabel = new JLabel("How many items do you want for all?");
	
	private JLabel bankLabel = new JLabel("BANK");
    private JLabel denominationLabel = new JLabel("[Insert the number of Bills/Coins to your BANK]");
    private JLabel[] denominations = new JLabel[9];
    private JTextField[] denominationFields = new JTextField[9];
    private JButton submitBankButton = new JButton("Submit");
    private JPanel displayPanel;
    private JPanel calculatorPanel = new JPanel(new GridLayout(4, 3, 2, 2));

    private JLabel userMoneyLabel = new JLabel("User Balance: ");
    private JButton[] calculatorButtons = new JButton[10];
    private JTextField calculatorTextField = new JTextField(10);
    private JButton submitButton = new JButton("Submit");

	public VendingMachineView() {
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        // Update the layout settings for mainMenuPanel
        JPanel mainMenuPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbcDisplayPanel = new GridBagConstraints();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainMenuPanel.add(coffeeVendingButton, gbc);

        gbc.gridy = 1;
        mainMenuPanel.add(ownVendingButton, gbc);

        gbc.gridy = 2;
        mainMenuPanel.add(testVendingButton, gbc);

        gbc.gridy = 3;
        mainMenuPanel.add(exitButton, gbc);

        gbc.gridy = 4;
        mainMenuPanel.add(nameLabel, gbc);

        gbc.gridy = 5;
        mainMenuPanel.add(nameTextField, gbc);

        gbc.gridy = 6;
        mainMenuPanel.add(typeLabel, gbc);

        gbc.gridy = 7;
        mainMenuPanel.add(typeTextField, gbc);

        gbc.gridy = 8;
        mainMenuPanel.add(numberOfItemsLabel, gbc);

        gbc.gridy = 9;
        mainMenuPanel.add(numberOfItemsTextField, gbc);

        gbc.gridy = 10;
        mainMenuPanel.add(createButton, gbc);

        gbc.gridy = 11;
        mainMenuPanel.add(bankLabel, gbc);

        gbc.gridy = 12;
        mainMenuPanel.add(denominationLabel, gbc);

        String[] denominationNames = {"1000", "500", "200", "100", "50", "20", "10", "5", "1"};
        for (int i = 0; i < 9; i++) {
            gbc.gridy = 13 + i;
            denominations[i] = new JLabel(denominationNames[i] + " Peso :");
            mainMenuPanel.add(denominations[i], gbc);

            gbc.gridx = 1;
            denominationFields[i] = new JTextField(5);
            mainMenuPanel.add(denominationFields[i], gbc);
            gbc.gridx = 0;
        }

        gbc.gridy = 22;
        mainMenuPanel.add(submitBankButton, gbc);
		
		for (int i = 1; i <= 9; i++) {
            calculatorButtons[i] = new JButton(String.valueOf(i));
            calculatorPanel.add(calculatorButtons[i]);
        }
		calculatorButtons[0] = new JButton("0");

        gbcDisplayPanel.gridy = 23; // Set the desired row number for the displayPanel
        gbcDisplayPanel.gridwidth = 2; // Make it span the entire row
        mainMenuPanel.add(displayPanel, gbcDisplayPanel);

        nameLabel.setVisible(false);
        nameTextField.setVisible(false);
        typeLabel.setVisible(false);
        typeTextField.setVisible(false);
        numberOfItemsLabel.setVisible(false);
        numberOfItemsTextField.setVisible(false);
        createButton.setVisible(false);
        bankLabel.setVisible(false);
        denominationLabel.setVisible(false);
        for (int i = 0; i < 9; i++) {
            denominations[i].setVisible(false);
            denominationFields[i].setVisible(false);
        }
        submitBankButton.setVisible(false);

        displayPanel.setVisible(false);
        userMoneyLabel.setVisible(false);

        mainMenuPanel.setVisible(true);
        mainMenuPanel.add(displayPanel, gbcDisplayPanel);

        this.add(mainMenuPanel);
    }

    // Existing methods remain unchanged
	
    public void showMainMenuFields(boolean show) {
        coffeeVendingButton.setVisible(show);
        ownVendingButton.setVisible(show);
        testVendingButton.setVisible(show);
        exitButton.setVisible(show);
    }

    public void showTestMenuFields(boolean show) {
        testVendingButton.setVisible(show);
        exitButton.setVisible(show);
    }

    public void showDisplayMenuFields(boolean show){
        displayPanel.setVisible(show);
        userMoneyLabel.setVisible(show);

        // Refresh the display and right panels
        displayPanel.revalidate();
        displayPanel.repaint();
        // Refresh the parent containers
        this.revalidate();
        this.repaint();
    }

    public void displayAvailableItems(Map<Ingredient, Integer> slots) {

		displayPanel.add(userMoneyLabel);
        // Create a new grid layout with 5 rows and 2 columns to display items and quantity side by side
        GridLayout gridLayout = new GridLayout(5, 2, 3, 5);
        displayPanel.setLayout(gridLayout);

		calculatorPanel.add(new JLabel());
        calculatorPanel.add(calculatorButtons[0]);

        calculatorPanel.add(new JLabel()); // Empty label for spacing
		
		calculatorTextField.setPreferredSize(new Dimension(15, 10));
        displayPanel.add(calculatorPanel);
		displayPanel.add(calculatorTextField);

        for (Map.Entry<Ingredient, Integer> entry : slots.entrySet()) {
            Ingredient item = entry.getKey();
            int quantity = entry.getValue();
            String itemName = item.getName();
            int itemPrice = item.getPrice();
            String itemImageFileName = item.getImageFileName();

            // Create a panel to hold the item's image and information
            JPanel itemPanel = new JPanel(new BorderLayout());

            String itemInfo;
            if (quantity >= 1) {
                // If the item is available
                itemInfo = "<html><font color='green'>[" + quantity + "] " + itemName + "</font><br>Price: Php " + itemPrice + "</html>";
            } else {
                // If the item is sold out
                itemInfo = "<html><font color='red'>[SOLD OUT] " + itemName + "</font><br>Price: Php " + itemPrice + "</html>";
            }

            ImageIcon icon = createIcon(itemImageFileName);
            if (icon != null) {
                JLabel imageLabel = new JLabel(icon);
                itemPanel.add(imageLabel, BorderLayout.CENTER);
            } else {
                // If the icon cannot be loaded, show a default icon or an error image
                ImageIcon defaultIcon = new ImageIcon(); // Add your default image here
                JLabel itemLabel = new JLabel(itemInfo + " (Image Error)", defaultIcon, JLabel.CENTER);
                itemLabel.setIconTextGap(10); // Add some gap between text and icon
                itemPanel.add(itemLabel, BorderLayout.CENTER);
            }

            JLabel infoLabel = new JLabel(itemInfo, JLabel.CENTER);
            itemPanel.add(infoLabel, BorderLayout.SOUTH);

            displayPanel.add(itemPanel);
        }

        showDisplayMenuFields(true);

        displayPanel.revalidate(); // Refresh the panel
        displayPanel.repaint();
    }

    private ImageIcon createIcon(String fileName) {
        try {
            // Load the image file from the specified path
            File file = new File("C:\\Users\\rhenz\\Documents\\GitHub\\mp\\resources\\" + fileName);
            BufferedImage image = ImageIO.read(file);

            // Return null if the image file cannot be read
            if (image == null) {
                return null;
            }

            // Scale the image to the desired width and height (e.g., 100x100)
            int imageWidth = 100;
            int imageHeight = 100;
            Image scaledImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            // If the image file is not found or cannot be read, return null
            // You can return a default icon or show an error image here
            return null;
        }
    }

	public void showCoffeeVendingFields(boolean show) {
        nameLabel.setVisible(show);
        nameTextField.setVisible(show);
        typeLabel.setVisible(show);
        typeTextField.setVisible(show);
        numberOfItemsLabel.setVisible(show);
        numberOfItemsTextField.setVisible(show);
		createButton.setVisible(show);
    }
	
	public void showBankDetails(boolean show) {
        // Show or hide the new labels and text fields for bank details
        bankLabel.setVisible(show);
        denominationLabel.setVisible(show);
        for (int i = 0; i < 9; i++) {
            denominations[i].setVisible(show);
            denominationFields[i].setVisible(show);
        }
        submitBankButton.setVisible(show);
    }
	
	public JButton getCoffeeVendingButton() {
        return coffeeVendingButton;
    }

    public JButton getOwnVendingButton() {
        return ownVendingButton;
    }

    public JButton getTestVendingButton() {
        return testVendingButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
	
	public JButton getCreateButton() {
        return createButton;
    }

    public JButton getSubmitBankButton() {
        return submitBankButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }
	
	public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getTypeTextField() {
        return typeTextField;
    }

    public JTextField getNumberOfItemsTextField() {
        return numberOfItemsTextField;
    }
    public JTextField getCalculatorTextField() {
        return calculatorTextField;
    }
	
	public String getNameInput() {
        return nameTextField.getText();
    }

    public String getTypeInput() {
        return typeTextField.getText();
    }

    public String getNumberOfItemsInput() {
        return numberOfItemsTextField.getText();
    }
	
	public String[] getDenominationInputs() {
        String[] inputs = new String[9];
        for (int i = 0; i < 9; i++) {
            inputs[i] = denominationFields[i].getText();
        }
        return inputs;
    }

    public JButton[] getCalculatorButtons() {
        return calculatorButtons;
    }
	
	public void addNameTextFieldListener(ActionListener listener) {
        nameTextField.addActionListener(listener);
    }

    public void addTypeTextFieldListener(ActionListener listener) {
        typeTextField.addActionListener(listener);
    }

    public void addNumberOfItemsTextFieldListener(ActionListener listener) {
        numberOfItemsTextField.addActionListener(listener);
    }
	
	public void addCoffeeVendingListener(ActionListener listenerforCoffeeVendingButton){
		coffeeVendingButton.addActionListener(listenerforCoffeeVendingButton);
	}
	
	public void addOwnVendingListener(ActionListener listenerforOwnVendingButton){
		ownVendingButton.addActionListener(listenerforOwnVendingButton);
	}
	
	public void addTestVendingListener(ActionListener listenerforTestVendingButton){
		testVendingButton.addActionListener(listenerforTestVendingButton);
	}
	
	public void addExitVendingListener(ActionListener listenerforExitVendingButton){
		exitButton.addActionListener(listenerforExitVendingButton);
	}
	
	public void addCreateListener(ActionListener listenerforCreateButton){
		createButton.addActionListener(listenerforCreateButton);
	}
	
	public void addDenominationFieldListeners(ActionListener listener) {
		for (int i = 0; i < 9; i++) {
			denominationFields[i].addActionListener(listener);
		}
	}

    public void addSubmitBankListener(ActionListener listenerforsubmitBankButton){
		submitBankButton.addActionListener(listenerforsubmitBankButton);
	}

    public void addCalculatorButtonListener(ActionListener listener){
        for (int i = 0; i < 9; i++) {
			calculatorButtons[i].addActionListener(listener);
		}
    }

    public void addCalculatorTextFieldListener(ActionListener listener){
        calculatorTextField.addActionListener(listener);
    }

    public void addSubmitButtonListener(ActionListener listener){
        submitButton.addActionListener(listener);
    }
}