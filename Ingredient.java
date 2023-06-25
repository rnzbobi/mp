public class Ingredient {
    private final String name;
    private int price;
    private final int calories;

    public Ingredient(String name, int price, int calories){
        this.name = name;
        this.price = price;
        this.calories = calories;
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

    public void setPrice(int price){
        this.price = price;
    }
}