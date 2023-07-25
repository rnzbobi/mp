/**
 * Represents an ingredient with a name, price, and calorie information.
 */
public class Ingredient {
    private final String name;
    private int price;
    private final int calories;

    /**
     * Constructs an Ingredient object with the specified name, price, and calorie information.
     *
     * @param name     the name of the ingredient
     * @param price    the price of the ingredient
     * @param calories the calorie information of the ingredient
     */
    public Ingredient(String name, int price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    /**
     * Retrieves the name of the ingredient.
     *
     * @return the name of the ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the ingredient.
     *
     * @return the price of the ingredient
     */
    public int getPrice() {
        return price;
    }

    /**
     * Retrieves the calorie information of the ingredient.
     *
     * @return the calorie information of the ingredient
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets the price of the ingredient.
     *
     * @param price the new price of the ingredient
     */
    public void setPrice(int price) {
        this.price = price;
    }
}