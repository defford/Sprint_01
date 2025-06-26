import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        UserManager userManager = new UserManager();
        
        TaskList.printBoxed("Welcome to the To-Do List Manager!");
        
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
                            TaskList.printBoxed("✓ User '" + name + "' added successfully.");
                        } else {
                            TaskList.printBoxed("✗ Failed to add user. User may already exist or capacity reached.");
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
                            TaskList.printBoxed("✗ User not found.");
                        }
                        break;
                        
                    case 4:
                        TaskList.printBoxed("Goodbye! Thanks for using To-Do List Manager!");
                        in.close();
                        return;
                        
                    default:
                        TaskList.printBoxed("✗ Invalid option. Please choose 1-4.");
                        break;
                }
            } catch (NumberFormatException e) {
                TaskList.printBoxed("✗ Please enter a valid number.");
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
                        TaskList.printBoxed("✓ Task added successfully.");
                        break;
                        
                    case 2:
                        System.out.print("Enter task number to complete: ");
                        try {
                            int taskIndex = Integer.parseInt(in.nextLine().trim());
                            user.completeTask(taskIndex);
                        } catch (NumberFormatException e) {
                            TaskList.printBoxed("✗ Please enter a valid task number.");
                        }
                        break;
                        
                    case 3:
                        user.printTasks();
                        break;
                        
                    case 4:
                        return;
                        
                    default:
                        TaskList.printBoxed("✗ Invalid option. Please choose 1-4.");
                        break;
                }
            } catch (NumberFormatException e) {
                TaskList.printBoxed("✗ Please enter a valid number.");
            }
        }
    }
} 