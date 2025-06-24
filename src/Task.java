public class Task {
    private String description;
    private boolean done; // false by default

    public Task(String desc) {
        this.description = desc;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return (done ? "[x] " : "[ ] ") + description;
    }
} 