import java.util.*;

/**
 * Represents a dish that can be ordered from a menu.
 */
public class Dish {
    private final String name;
    private final int quantity;
    private final int price;
    private final int calories;
    private final HashMap<Ingredient, Integer> ingredients;

    /**
     * Constructs a new Dish object with the specified name, price, calories, and ingredients.
     *
     * @param name        the name of the dish
     * @param price       the price of the dish
     * @param calories    the calorie count of the dish
     * @param ingredients the list of ingredients in the dish
     */
    public Dish(String name, int quantity, int price, int calories, HashMap<Ingredient, Integer> ingredients){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.calories = calories;
        this.ingredients = ingredients;
    }

    /**
     * Returns the name of the dish.
     *
     * @return the name of the dish
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the quantity of the dish.
     *
     * @return the quantity of the dish
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the price of the dish.
     *
     * @return the price of the dish
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the calorie count of the dish.
     *
     * @return the calorie count of the dish
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Returns the list of ingredients in the dish.
     *
     * @return the list of ingredients in the dish
     */
    public HashMap<Ingredient, Integer> getIngredients() {
        return ingredients;
    }
}