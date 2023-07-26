import java.awt.event.ActionListener;
import java.awt.*;

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
	
	/*private JLabel bankLabel = new JLabel("===== YOUR BANK =====");
    private JLabel denominationLabel = new JLabel("[Insert the number of Bills/Coins to your BANK]");
    private JLabel[] denominations = new JLabel[9];
    private JTextField[] denominationFields = new JTextField[9];*/
	
	public VendingMachineView(){
		JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);

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
		
		/*gbc.gridy = 11;
        mainMenuPanel.add(bankLabel, gbc);

        gbc.gridy = 12;
        mainMenuPanel.add(denominationLabel, gbc);

        String[] denominationNames = {"1000", "500", "200", "100", "50", "20", "10", "5", "1"};
        for (int i = 0; i < 9; i++) {
            gbc.gridy = 13 + i;
            denominations[i] = new JLabel(denominationNames[i] + " Peso Bills/Coins [INSERT]");
            mainMenuPanel.add(denominations[i], gbc);

            gbc.gridx = 1;
            denominationFields[i] = new JTextField(5);
            mainMenuPanel.add(denominationFields[i], gbc);
            gbc.gridx = 0;
        }*/

		nameLabel.setVisible(false);
		nameTextField.setVisible(false);
		typeLabel.setVisible(false);
		typeTextField.setVisible(false);
		numberOfItemsLabel.setVisible(false);
		numberOfItemsTextField.setVisible(false);
		createButton.setVisible(false);
		/*bankLabel.setVisible(false);
		denominationLabel.setVisible(false);
		for (int i = 0; i < 9; i++) {
            denominations[i].setVisible(false);
            denominationFields[i].setVisible(false);
        }*/

        this.add(mainMenuPanel);
	}
	
	public void showCoffeeVendingFields(boolean show) {
		coffeeVendingButton.setVisible(false);
		ownVendingButton.setVisible(false);
        testVendingButton.setVisible(false);
        exitButton.setVisible(false);
        nameLabel.setVisible(show);
        nameTextField.setVisible(show);
        typeLabel.setVisible(show);
        typeTextField.setVisible(show);
        numberOfItemsLabel.setVisible(show);
        numberOfItemsTextField.setVisible(show);
		createButton.setVisible(show);
    }
	
	/*public void showBankDetails(boolean show) {
        // Show or hide the new labels and text fields for bank details
		showCoffeeVendingFields(false);
        bankLabel.setVisible(show);
        denominationLabel.setVisible(show);
        for (int i = 0; i < 9; i++) {
            denominations[i].setVisible(show);
            denominationFields[i].setVisible(show);
        }
    }*/
	
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
	
	public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getTypeTextField() {
        return typeTextField;
    }

    public JTextField getNumberOfItemsTextField() {
        return numberOfItemsTextField;
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
	
	/*public String[] getDenominationInputs() {
        String[] inputs = new String[9];
        for (int i = 0; i < 9; i++) {
            inputs[i] = denominationFields[i].getText();
        }
        return inputs;
    }*/
	
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
	
	/*public void addDenominationFieldListeners(ActionListener listener) {
		for (int i = 0; i < 9; i++) {
			denominationFields[i].addActionListener(listener);
		}
	}*/
}