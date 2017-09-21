package foodstorage;

import java.util.Map;
import java.util.TreeMap;

public class StorageUnit {

    private String name;
    private String storageType;
    private boolean atCapacity;
    Map<String, FoodItem> foodItems = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public StorageUnit() {

    }

    public StorageUnit(String name, String storageType) {
        this.name = name;
        this.storageType = storageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public Map<String, FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Map<String, FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public void addFoodItem(FoodItem foodItem) {
        foodItems.put(foodItem.getUuid(), foodItem);
    }
}
