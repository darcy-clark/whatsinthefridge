package foodstorage;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class StorageUnit {

    private String name;
    private String storageType;
    private boolean atCapacity;
    Map<String, ArrayList<FoodItem>> foodItems = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

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

    public Map<String, ArrayList<FoodItem>> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Map<String, ArrayList<FoodItem>> foodItems) {
        this.foodItems = foodItems;
    }

    public void addFoodItem(FoodItem foodItem) {
        if(foodItems.containsKey(foodItem.getName())) {
            ArrayList<FoodItem> foodItemsWithSameName = foodItems.get(foodItem.getName());
            foodItemsWithSameName.add(foodItem);
            foodItem.setId(foodItemsWithSameName.size());
        } else {
            ArrayList<FoodItem> mapArrayValue = new ArrayList<>();
            mapArrayValue.add(foodItem);
            foodItems.put(foodItem.getName(), mapArrayValue);
            foodItem.setId(1);
        }
    }

    public void deleteFoodItem(String foodItemName) {
        foodItems.remove(foodItemName);
    }

    public void deleteFoodItem(FoodItem foodItem, int id) {
        ArrayList<FoodItem> foodItemsWithSameName = foodItems.get(foodItem.getName());
        for(FoodItem i : foodItemsWithSameName) {
            if(i.getId() == id) {
                foodItemsWithSameName.remove(i);
                return;
            }
        }
    }
}
