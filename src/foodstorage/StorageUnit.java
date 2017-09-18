package foodstorage;

public class StorageUnit {

    private String name;
    private String storageType;
    private boolean atCapacity;
    FoodItem[] foodItems;

    public StorageUnit() {

    }

    public StorageUnit(String name, String storageType) {
        this.name = name;
        this.storageType = storageType;
    }

    public String getName() {
        return name;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
}
