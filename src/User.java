public class User {
    private String name;
    private TaskList tasks = new TaskList();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTask(String desc) {
        tasks.add(new Task(desc));
    }

    public void completeTask(int idx) {
        if (!tasks.markDone(idx)) {
            printBoxed("✗ Invalid task number.");
        } else {
            printBoxed("✓ Task completed successfully.");
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

    public void printTasks() {
        System.out.println("\n--- Tasks for " + name + " ---");
        tasks.printAll();
    }
} 