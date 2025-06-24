import java.util.Scanner;

public class Main {
    
    // Helper method to create boxed output
    private static void printBoxed(String message) {
        String[] lines = message.split("\n");
        int maxLength = 0;
        
        // Find the longest line
        for (String line : lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        
        // Create top border
        System.out.print("╔");
        for (int i = 0; i < maxLength + 2; i++) {
            System.out.print("═");
        }
        System.out.println("╗");
        
        // Print each line with side borders
        for (String line : lines) {
            System.out.print("║ ");
            System.out.print(line);
            // Add padding to align right border
            for (int i = line.length(); i < maxLength; i++) {
                System.out.print(" ");
            }
            System.out.println(" ║");
        }
        
        // Create bottom border
        System.out.print("╚");
        for (int i = 0; i < maxLength + 2; i++) {
            System.out.print("═");
        }
        System.out.println("╝");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        UserManager userManager = new UserManager();
        
        printBoxed("Welcome to the To-Do List Manager!");
        
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add user");
            System.out.println("2. List users");
            System.out.println("3. Select user");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int choice = Integer.parseInt(in.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter user name: ");
                        String name = in.nextLine().trim();
                        if (userManager.addUser(name)) {
                            printBoxed("✓ User '" + name + "' added successfully.");
                        } else {
                            printBoxed("✗ Failed to add user. User may already exist or capacity reached.");
                        }
                        break;
                        
                    case 2:
                        userManager.listUsers();
                        break;
                        
                    case 3:
                        System.out.print("Enter user name: ");
                        String userName = in.nextLine().trim();
                        User user = userManager.getUser(userName);
                        if (user != null) {
                            userMenu(user, in);
                        } else {
                            printBoxed("✗ User not found.");
                        }
                        break;
                        
                    case 4:
                        printBoxed("Goodbye! Thanks for using To-Do List Manager!");
                        in.close();
                        return;
                        
                    default:
                        printBoxed("✗ Invalid option. Please choose 1-4.");
                        break;
                }
            } catch (NumberFormatException e) {
                printBoxed("✗ Please enter a valid number.");
            }
        }
    }
    
    private static void userMenu(User user, Scanner in) {
        while (true) {
            System.out.println("\nUser Menu for " + user.getName() + ":");
            System.out.println("1. Add task");
            System.out.println("2. Complete task");
            System.out.println("3. View tasks");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            try {
                int choice = Integer.parseInt(in.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter task description: ");
                        String description = in.nextLine().trim();
                        user.addTask(description);
                        printBoxed("✓ Task added successfully.");
                        break;
                        
                    case 2:
                        System.out.print("Enter task number to complete: ");
                        try {
                            int taskIndex = Integer.parseInt(in.nextLine().trim());
                            user.completeTask(taskIndex);
                        } catch (NumberFormatException e) {
                            printBoxed("✗ Please enter a valid task number.");
                        }
                        break;
                        
                    case 3:
                        user.printTasks();
                        break;
                        
                    case 4:
                        return; // Back to main menu
                        
                    default:
                        printBoxed("✗ Invalid option. Please choose 1-4.");
                        break;
                }
            } catch (NumberFormatException e) {
                printBoxed("✗ Please enter a valid number.");
            }
        }
    }
} 