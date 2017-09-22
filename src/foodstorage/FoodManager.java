package foodstorage;

import savedata.SaveManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class FoodManager {

    private static Map<String, StorageUnit> allStorageUnits = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private static final String FREE_SPACE = "Free Space";

    private static final String COM_ADD_SU = "adds";
    private static final String COM_DEL_SU = "dels";
    private static final String COM_GET_SU = "gets";

    private static final String COM_ADD_FI = "addf";
    private static final String COM_DEL_FI = "delf";

    public static StorageUnit createStorageUnit(String name) throws IOException {
        return createStorageUnit(name, null);
    }

    public static StorageUnit createStorageUnit(String name, String storageType) throws IOException {
        if(allStorageUnits.containsKey(name)) {
            System.out.println("Storage unit already exists with this name!\n");
            return null;
        }

        StorageUnit newStorageUnit = new StorageUnit(name, storageType);
        allStorageUnits.put(newStorageUnit.getName(), newStorageUnit);
        SaveManager.saveToJson(newStorageUnit);
        System.out.println("New storage unit '" + name + "' created\n");

        return newStorageUnit;
    }

    public static void removeStorageUnit(String name) throws IOException {
        if(allStorageUnits.containsKey(name)) {
            SaveManager.deleteFromJson(allStorageUnits.get(name));
            allStorageUnits.remove(name);
            System.out.println("Storage unit '" + name + "' deleted\n");
            return;
        }

        System.out.println("Storage unit does not exist with this name!\n");
    }

    public static void displayStorageUnit(String name) {
        if(allStorageUnits.containsKey(name)) {
            StorageUnit unitToDisplay = allStorageUnits.get(name);
            System.out.println(unitToDisplay.getName() + "\n");
            System.out.println("Type:\t\t\t\t\t" + unitToDisplay.getStorageType());
            System.out.println("Number of Food Items:\t" + unitToDisplay.getFoodItems().size() + "\n");
            System.out.println("List of Food Items:");
            for(ArrayList<FoodItem> i : unitToDisplay.getFoodItems().values()) {
                for(FoodItem j : i) {
                    System.out.println(j.getName());
                }
            }
            System.out.println();
            return;
        }

        System.out.println("Storage unit does not exist with this name!\n");
    }

    public static void listAllStorageUnits() {
        System.out.println("Current storage units:\n");
        boolean empty = true;

        for(StorageUnit i : allStorageUnits.values()) {
            if(!i.getName().equals(FREE_SPACE)) {
                System.out.println(i.getName());
                empty = false;
            }
        }
        if(empty) {
            System.out.println("There are no created storage units.");
        }
        System.out.println();
    }

    public static FoodItem createFoodItem(String name) throws IOException {
        return createFoodItem(name, FREE_SPACE);
    }

    public static FoodItem createFoodItem(String name, String location) throws IOException {
        FoodItem newFoodItem = new FoodItem(name, location);
        StorageUnit storageLocation = allStorageUnits.get(location);
        storageLocation.addFoodItem(newFoodItem);
        System.out.println("New food item '" + name + "' created\n");
        SaveManager.saveToJson(newFoodItem);
        return newFoodItem;
    }

    public static void removeFoodItem(String nameFood, String nameStorage) {
        if(allStorageUnits.containsKey(nameStorage)) {
            StorageUnit storageUnit = allStorageUnits.get(nameStorage);
            ArrayList<FoodItem> foodItemsWithSameName = storageUnit.getFoodItems().get(nameFood);
            if(foodItemsWithSameName.size() == 1) {
                storageUnit.deleteFoodItem(nameFood);
                System.out.println("Food item " + nameFood + " deleted.\n");
            } else {
                System.out.println(foodItemsWithSameName.size() + " food items with this name exist, please specify ID.\n");
            }
            return;
        }
        System.out.println("Storage unit " + nameStorage + " does not exist!\n");
    }

    public static void main(String args[]) throws IOException {
        allStorageUnits = SaveManager.loadTreeMap();

        //Safe-guard in case I delete the save file contents
        if(!allStorageUnits.containsKey(FREE_SPACE)) {
            createStorageUnit(FREE_SPACE);
        }

        BufferedReader userCommandBR =  new BufferedReader(new InputStreamReader(System.in));
        String[] userCommand = new String[1];
        userCommand[0] = "-";

        while(!userCommand[0].equals("e")) {
            System.out.print("Enter a command: ");
            try {
                userCommand = userCommandBR.readLine().split(" ");

                if(userCommand[0].equalsIgnoreCase("e")) {
                    break;

                } else if(userCommand[0].equalsIgnoreCase("ls")) {
                    listAllStorageUnits();

                } else if(userCommand[0].equalsIgnoreCase(COM_ADD_SU)) {
                    if(userCommand.length == 2) {
                        createStorageUnit(userCommand[1]);
                    } else if(userCommand.length == 3) {
                        createStorageUnit(userCommand[1], userCommand[2]);
                    } else if(userCommand.length > 3) {
                        System.out.println("Too many arguments!\n");
                    } else {
                        System.out.println("Storage unit requires a name!\n");
                    }

                } else if(userCommand[0].equalsIgnoreCase(COM_DEL_SU)) {
                    if(userCommand.length > 1) {
                        removeStorageUnit(userCommand[1]);
                    } else {
                        System.out.println("Name of storage unit required.\n");
                    }

                } else if(userCommand[0].equalsIgnoreCase(COM_GET_SU)) {
                    if (userCommand.length > 1) {
                        displayStorageUnit(userCommand[1]);
                    } else {
                        System.out.println("Name of storage unit required.\n");
                    }

                } else if(userCommand[0].equalsIgnoreCase(COM_ADD_FI)) {
                    if (userCommand.length == 2) {
                        createFoodItem(userCommand[1]);
                    } else if (userCommand.length == 3) {
                        createFoodItem(userCommand[1], userCommand[2]);
                    } else if (userCommand.length > 3) {
                        System.out.println("Too many arguments!\n");
                    } else {
                        System.out.println("Food item requires a name!\n");
                    }

                } else if(userCommand[0].equalsIgnoreCase(COM_DEL_FI)) {
                    if(userCommand.length == 2) {
                        System.out.println("Please specify storage unit.\n");
                    } else if(userCommand.length == 3) {
                        removeFoodItem(userCommand[1], userCommand[2]);
                    } else if(userCommand.length == 4) {

                    } else if(userCommand.length > 3) {
                        System.out.println("Too many arguments!\n");
                    } else {
                        System.out.println("Must specify food item to delete!\n");
                    }

                } else {
                    System.out.println("Invalid command. Please try again or type help for a list of commands.\n");
                }

            } catch(IOException badInput) {
                System.out.println("Bad input. Please try again.\n");
            }
        }

        System.out.println("Exiting What's In The Fridge");

    }

}
