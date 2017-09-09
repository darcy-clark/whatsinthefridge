package foodstorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FoodManager {

    private static ArrayList<StorageUnit> allStorageUnits = new ArrayList<>();

    private static final String COM_ADD_SU = "adds";

    public static void main(String args[]) {
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
                    System.out.println("Current storage units:\n");
                } else if(userCommand[0].equalsIgnoreCase(COM_ADD_SU)) {
                    if(userCommand.length > 1) {
                        StorageUnit newStorageUnit = new StorageUnit(userCommand[1]);
                        allStorageUnits.add(newStorageUnit);
                        System.out.println("New storage unit '" + userCommand[1] + "' created\n");
                    } else {
                        System.out.println("Storage unit requires a name!\n");
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
