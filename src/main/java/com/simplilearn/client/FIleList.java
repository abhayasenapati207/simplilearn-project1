package com.simplilearn.client;
import java.util.Scanner;
import java.io.File;
public class FIleList {
    private static final String ROOT_DIRECTORY = "/var/www/html/log/";

    public static void main(String[] args) {
        displayFileManagementWelcomeScreen();
        showInputScreen();
    }

    private static void showInputScreen(){
        int customerOption;
        Scanner scanner = new Scanner(System.in);
        do {
            displayUserInterface();
            System.out.print("Enter an option: ");
            customerOption = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (customerOption) {
                case 1:
                    displayFileNamesAscending();
                    break;
                case 2:
                    displayFileManagementOptions(scanner);
                    break;
                case 3:
                    System.out.println("Closing the application...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (customerOption != 3);

        scanner.close();
    }
    private static void displayFileManagementWelcomeScreen() {
        System.out.println("Welcome to the File Management Application");
        System.out.println("Developer: Abhaya Ranjan Senapati");
        System.out.println();
    }

    private static void displayUserInterface() {
        System.out.println("1. Display current file names in ascending order");
        System.out.println("2. File Management Options");
        System.out.println("3. Close the application");
        System.out.println();
    }

    private static void displayFileNamesAscending() {
        File rootDirectory = new File(ROOT_DIRECTORY);
        File[] files = rootDirectory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No files found in the directory.");
        } else {

            System.out.println("Current file names in ascending order:");
            for (File file : files) {
                System.out.println(file.getName());
            }
        }

        System.out.println();
    }

    private static void displayFileManagementOptions(Scanner scanner) {
        int option;
        do {
            System.out.println("File Management Options:");
            System.out.println("1. Add a file to the existing directory list");
            System.out.println("2. Delete a file from the existing directory list");
            System.out.println("3. Search for a file in the main directory");
            System.out.println("4. Go back to the main context");
            System.out.print("Enter an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    addFileToDirectory(scanner);
                    break;
                case 2:
                    deleteFileFromDirectory(scanner);
                    break;
                case 3:
                    searchFileInDirectory(scanner);
                    break;
                case 4:
                    System.out.println("Going back to the main context...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (option != 4);

        System.out.println();
    }

    private static void addFileToDirectory(Scanner scanner) {
        System.out.print("Enter the file name to add: ");
        String fileName = scanner.nextLine();
        File file = new File(ROOT_DIRECTORY, fileName);

        if (file.exists()) {
            System.out.println("File already exists.");
        } else {
            try {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("File added successfully.");
                } else {
                    System.out.println("Failed to add the file.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred while adding the file: " + e.getMessage());
            }
        }
    }
    private static void deleteFileFromDirectory(Scanner scanner) {

        System.out.println("Enter the file name to delete:");
        String fileName = scanner.nextLine();

        // Get the file
        File file = new File(ROOT_DIRECTORY+fileName);

        // Delete the file
        file.delete();

        // Success message
        System.out.println("File deleted successfully!");

    }
    private static void searchFileInDirectory(Scanner scanner) {
        // Prompt the user for the file name
        System.out.println("Enter the file name to search for:");
        String fileName = scanner.nextLine();

        // Get the current directory
        File currentDirectory = new File(ROOT_DIRECTORY);

        // Get the list of files in the current directory
        File[] files = currentDirectory.listFiles();

        // Search for the file
        File foundFile = null;
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                foundFile = file;
                break;
            }
        }

        // Success message
        if (foundFile != null) {
            System.out.println("File found!");
            System.out.println(foundFile.getAbsolutePath());
        } else {
            System.out.println("File not found!");
        }
    }

}
