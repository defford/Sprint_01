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
            TaskList.printBoxed("✗ Invalid task number.");
        } else {
            TaskList.printBoxed("✓ Task completed successfully.");
        }
    }

    public void printTasks() {
        System.out.println("\n--- Tasks for " + name + " ---");
        tasks.printAll();
    }
} 