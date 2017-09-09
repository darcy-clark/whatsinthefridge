package foodstorage;

public class StorageUnit {

    private String name;
    private String storageType;
    private boolean atCapacity;
    FoodItem[] foodItems;

    public StorageUnit(String name) {
        this.name = name;
    }
}
