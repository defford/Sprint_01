public class UserManager {
    private User[] users = new User[50];
    private int count = 0;

    public boolean addUser(String name) {
        // Check if user already exists
        for (int i = 0; i < count; i++) {
            if (users[i].getName().equals(name)) {
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
            if (users[i].getName().equals(name)) {
                return users[i];
            }
        }
        return null; // User not found
    }

    public void listUsers() {
        if (count == 0) {
            System.out.println("(no users)");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(users[i].getName());
            }
        }
    }
} 