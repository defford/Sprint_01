public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return (done ? "[x] " : "[ ] ") + description;
    }
} 