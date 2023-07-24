import java.util.*;

public class SpecialVendingMachine extends RegularVendingMachine{
    private Dish dish;

	public SpecialVendingMachine(String name, HashMap<Ingredient,Integer> slots2, Bank bank){
        super(name,slots2,bank);
    }
	
    public void createDish(String name, ArrayList<Ingredient> ingredients) {

    }

    public void prepareDish(Dish dish) {

    }
}