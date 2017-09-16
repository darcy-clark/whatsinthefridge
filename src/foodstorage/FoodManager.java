package foodstorage;

import savedata.SaveManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FoodManager {

    private static ArrayList<StorageUnit> allStorageUnits = new ArrayList<>();

    private static final String COM_ADD_SU = "adds";
    private static final String COM_DEL_SU = "dels";

    public static StorageUnit createStorageUnit(String name) {
        for(StorageUnit i : allStorageUnits) {
            if(i.getName().equals(name)) {
                System.out.println("Storage unit already exists with this name!\n");
                return null;
            }
        }

        StorageUnit newStorageUnit = new StorageUnit(name);
        allStorageUnits.add(newStorageUnit);
        System.out.println("New storage unit '" + name + "' created\n");

        return newStorageUnit;
    }

    public static void removeStorageUnit(String name) {
        for(StorageUnit i : allStorageUnits) {
            if(i.getName().equalsIgnoreCase(name)) {
                allStorageUnits.remove(i);
                System.out.println("Storage unit '" + name + "' deleted\n");
                return;
            }
        }
        System.out.println("Storage unit does not exist with this name!\n");
    }

    public static void listAllStorageUnits() {
        System.out.println("Current storage units:\n");
        boolean empty = true;

        for(StorageUnit i : allStorageUnits) {
            System.out.println(i.getName());
            empty = false;
        }
        if(empty) {
            System.out.println("There are no created storage units.");
        }
        System.out.println();
    }

    public static void main(String args[]) throws IOException {
        System.out.println(System.getProperty("user.dir"));

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
                    if(userCommand.length > 1) {
                        StorageUnit newUnit = createStorageUnit(userCommand[1]);
                        if(newUnit != null) {
                            SaveManager saveManager = new SaveManager();
                            saveManager.saveToJson(newUnit);
                        }

                    } else {
                        System.out.println("Storage unit requires a name!\n");
                    }

                } else if(userCommand[0].equalsIgnoreCase(COM_DEL_SU)) {
                    if(userCommand.length > 1) {
                        removeStorageUnit(userCommand[1]);
                    } else {
                        System.out.println("Name of storage unit required.\n");
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
