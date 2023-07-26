public class Main {
    public static void main(String[] args) {
        // Create the view
        VendingMachineView view = new VendingMachineView();

        // Create the controller and pass the view to it
        VendingMachineController controller = new VendingMachineController(view);

        // Set the view visible
        view.setVisible(true);
    }
}