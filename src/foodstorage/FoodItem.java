package foodstorage;

import java.util.UUID;

public class FoodItem {

    private String name;
    private String uuid;
    private String location;
    private int quantity;

    public FoodItem() {

    }

    public FoodItem(String name, String location) {
        this.name = name;
        this.location = location;
        uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
