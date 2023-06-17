import java.util.*;

public class Dish {
    private final String name;
    private final int price;
    private final int calories;
    private final ArrayList<Ingredient> ingredients;

    public Dish(String name, int price, int calories, ArrayList<Ingredient> ingredients){
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.ingredients = ingredients;

    }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}