public class UserManager {
    private User[] users = new User[50];
    private int count = 0;

    public boolean addUser(String name) {
        // Check if user already exists (case-insensitive)
        for (int i = 0; i < count; i++) {
            if (users[i].getName().equalsIgnoreCase(name)) {
                return false; 
            }
        }
        
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
        return null; 
    }

    public void listUsers() {
        if (count == 0) {
            TaskList.printBoxed("(no users)");
        } else {
            StringBuilder userList = new StringBuilder();
            for (int i = 0; i < count; i++) {
                if (i > 0) {
                    userList.append("\n");
                }
                userList.append(users[i].getName());
            }
            TaskList.printBoxed("Registered Users:\n" + userList.toString());
        }
    }
} 