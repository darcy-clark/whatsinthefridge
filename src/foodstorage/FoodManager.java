package foodstorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FoodManager {

    public static void main(String args[]) {
        BufferedReader userCommandBR =  new BufferedReader(new InputStreamReader(System.in));
        String userCommand = new String();

        while(!userCommand.equals("-e")) {
            System.out.print("Enter a command: ");
            try {
                userCommand = userCommandBR.readLine();
                System.out.println("You entered: " + userCommand + "\nThank you for your input.\n");
            } catch(IOException badInput) {
                System.out.println("Bad input. Please try again.");
            }
        }

        System.out.println("Exiting What's In The Fridge");

    }

}
