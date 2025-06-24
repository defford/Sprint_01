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
            System.out.println("(no tasks)");
            return;
        }
        
        Node current = head;
        int index = 0;
        
        while (current != null) {
            System.out.println(index + ": " + current.data.toString());
            current = current.next;
            index++;
        }
    }
} 