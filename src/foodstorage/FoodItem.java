package foodstorage;

public class FoodItem {

    private String name;
    private String location;
    private int quantity;

    public FoodItem() {

    }

    public FoodItem(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
