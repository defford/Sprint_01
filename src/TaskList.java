public class TaskList {
    private class Node {
        Task data;
        Node next;
        
        Node(Task t) {
            data = t;
        }
    }
    
    private Node head;

    public void add(Task t) {
        Node newNode = new Node(t);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public boolean markDone(int idx) {
        if (idx < 0) {
            return false;
        }
        
        Node current = head;
        int currentIndex = 0;
        
        while (current != null && currentIndex < idx) {
            current = current.next;
            currentIndex++;
        }
        
        if (current != null) {
            current.data.markDone();
            return true;
        }
        
        return false;
    }

    public void printAll() {
        if (head == null) {
            printBoxed("(no tasks)");
            return;
        }
        
        StringBuilder taskList = new StringBuilder();
        Node current = head;
        int index = 0;
        
        while (current != null) {
            if (index > 0) {
                taskList.append("\n");
            }
            taskList.append(index + ": " + current.data.toString());
            current = current.next;
            index++;
        }
        
        printBoxed(taskList.toString());
    }
    
    public static void printBoxed(String message) {
        String[] lines = message.split("\n");
        int maxLength = 0;
        
        for (String line : lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        
        System.out.print("╔");
        for (int i = 0; i < maxLength + 2; i++) {
            System.out.print("═");
        }
        System.out.println("╗");
        
        for (String line : lines) {
            System.out.print("║ ");
            System.out.print(line);
            for (int i = line.length(); i < maxLength; i++) {
                System.out.print(" ");
            }
            System.out.println(" ║");
        }
        
        System.out.print("╚");
        for (int i = 0; i < maxLength + 2; i++) {
            System.out.print("═");
        }
        System.out.println("╝");
    }
} 