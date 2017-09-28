package foodstorage;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class StorageUnit {

    private String name;
    private String storageType;
    private boolean atCapacity;
    Map<String, ArrayList<FoodItem>> foodItems = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private int numberOfItems;

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

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
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
        numberOfItems++;
    }

    public void deleteFoodItem(String foodItemName) {
        foodItems.remove(foodItemName);
        numberOfItems--;
    }

    public void deleteFoodItem(String foodItemName, int id) {
        ArrayList<FoodItem> foodItemsWithSameName = foodItems.get(foodItemName);
        foodItemsWithSameName.remove(id - 1);
        for(int i = id - 1; i < foodItemsWithSameName.size(); i++) {
            int currentId = foodItemsWithSameName.get(i).getId();
            foodItemsWithSameName.get(i).setId(currentId - 1);
        }
        numberOfItems--;
    }
}
