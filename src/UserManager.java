public class UserManager {
    private User[] users = new User[50];
    private int count = 0;

    public boolean addUser(String name) {
        // Check if user already exists (case-insensitive)
        for (int i = 0; i < count; i++) {
            if (users[i].getName().equalsIgnoreCase(name)) {
                return false; // User already exists
            }
        }
        
        // Check if we have capacity
        if (count >= 50) {
            return false; // No more space
        }
        
        // Add new user
        users[count] = new User(name);
        count++;
        return true;
    }

    public User getUser(String name) {
        for (int i = 0; i < count; i++) {
            if (users[i].getName().equalsIgnoreCase(name)) {
                return users[i];
            }
        }
        return null; // User not found
    }

    public void listUsers() {
        if (count == 0) {
            printBoxed("(no users)");
        } else {
            StringBuilder userList = new StringBuilder();
            for (int i = 0; i < count; i++) {
                if (i > 0) {
                    userList.append("\n");
                }
                userList.append(users[i].getName());
            }
            printBoxed("Registered Users:\n" + userList.toString());
        }
    }
    
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
} 