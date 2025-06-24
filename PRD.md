## Overview

This document defines the product requirements for a simple, in-memory, multi-user To-Do List Manager in Java. Its purpose is to guide an AI code editor (or engineering team) through design and implementation, ensuring all functional needs and constraints are clearly specified.

---

## Objectives

* Deliver a command-line Java application where multiple users can maintain independent to-do lists.
* Demonstrate manual implementation of fundamental data structures: a fixed-size user array and a singly-linked list for each user’s tasks.
* Keep the codebase minimal—no external libraries for data structures, no persistence layer, no unit tests, and no web frameworks.

---

## Scope & Constraints

**In Scope**

* User creation, lookup, and listing, stored in a fixed-length `User[]`.
* Per-user task management via a custom singly-linked list: add, mark complete, and view.
* Single-threaded CLI interface (no concurrency controls).

**Out of Scope**

* Persistence (all data is lost on application exit).
* Unit or integration tests.
* GUI, web interfaces, frameworks (e.g. Spring Boot).
* Doubly-linked lists or collection libraries for core logic.

**Assumptions**

* Maximum of 50 users (configurable at compile time).
* Users are uniquely identified by name (case-sensitive).
* Task indexing starts at 0 in display and commands.

---

## User Personas & Use Cases

1. **New User**

   * Wants to sign up by providing a unique name.
2. **Returning User**

   * Selects their name to view or update tasks.
3. **Task Manager**

   * Adds tasks by description.
   * Marks tasks done by numeric index.
   * Reviews full task list with statuses.

---

## Functional Requirements

### 1. User Management

* **FR-1.1**: `addUser(name: String): boolean`

  * If `name` is unique and capacity remains, create a new `User`; return `true`.
  * Otherwise return `false`.
* **FR-1.2**: `getUser(name: String): User | null`

  * Linear search through user array; return matching `User` or `null` if not found.
* **FR-1.3**: `listUsers(): void`

  * Print all registered user names in insertion order, or `(no users)` if empty.

### 2. Task Management (per User)

* **FR-2.1**: `addTask(description: String): void`

  * Create a new `Task` and append it to the user’s `TaskList`.
* **FR-2.2**: `completeTask(index: int): void`

  * Traverse the singly-linked list; if node at `index` exists, mark its `Task.done = true`; otherwise, print “Invalid task number.”
* **FR-2.3**: `printTasks(): void`

  * Iterate list, printing each task as `{index}: [ ] desc` or `{index}: [x] desc`.
  * If no tasks, print `(no tasks)`.

### 3. Command-Line Interface

* **FR-3.1**: **Main Menu**

  1. Add user
  2. List users
  3. Select user
  4. Exit
* **FR-3.2**: **User Menu** (after selecting an existing user)

  1. Add task
  2. Complete task
  3. View tasks
  4. Back to Main Menu

---

## Non-Functional Requirements

* **NFR-1**: **Simplicity** – All logic handwritten; no collection or DI frameworks.
* **NFR-2**: **Performance** – O(n) operations acceptable given small user/task volumes.
* **NFR-3**: **Reliability** – Single-threaded CLI removes concurrency issues.
* **NFR-4**: **Maintainability** – Clear separation of concerns across classes.

---

## Technical Architecture & Class Design

### 1. Task

```java
public class Task {
  private String description;
  private boolean done; // false by default

  public Task(String desc) { this.description = desc; }
  public void markDone() { this.done = true; }
  @Override
  public String toString() {
    return (done ? "[x] " : "[ ] ") + description;
  }
}
```

### 2. TaskList (Singly-Linked)

```java
public class TaskList {
  private class Node {
    Task data;
    Node next;
    Node(Task t) { data = t; }
  }
  private Node head;

  public void add(Task t) { /* append to tail */ }
  public boolean markDone(int idx) { /* traverse & mark */ }
  public void printAll() { /* iterate & print */ }
}
```

### 3. User

```java
public class User {
  private String name;
  private TaskList tasks = new TaskList();

  public User(String name) { this.name = name; }
  public String getName() { return name; }
  public void addTask(String desc) { tasks.add(new Task(desc)); }
  public void completeTask(int idx) { /* delegate & handle invalid */ }
  public void printTasks() { /* header + tasks.printAll() */ }
}
```

### 4. UserManager

```java
public class UserManager {
  private User[] users = new User[50];
  private int count = 0;

  public boolean addUser(String name) { /* unique & space check */ }
  public User getUser(String name) { /* linear search */ }
  public void listUsers() { /* print or “(no users)” */ }
}
```

### 5. Main (CLI Flow)

```java
public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    UserManager um = new UserManager();
    // loop: Main Menu → handle options → userMenu(u, in)
  }
  private static void userMenu(User u, Scanner in) {
    // loop: User Menu → handle add/complete/view/back
  }
}
```

---

## CLI Interaction Flow

1. **Start** → display Main Menu
2. **Add User** → prompt for name → feedback success/failure
3. **List Users** → show names or “(no users)”
4. **Select User**

   * If found → enter User Menu
   * If not → “User not found.”
5. **User Menu** → add task, complete task, view tasks, or back
6. **Exit** → program terminates

---

## Error Handling & Validation

* Reject duplicate user names.
* Enforce numeric input for task indexes; catch `NumberFormatException`.
* Handle out-of-bounds task indices with a clear message.
* Guard against user menu commands outside 1–4.

---

## Acceptance Criteria

* Able to register up to 50 unique users.
* Able to add, view, and complete tasks per user via CLI menus.
* All data kept in memory; application state resets on exit.
* Code contains only the classes and methods defined above, with no external collection frameworks or tests.

---

## Future Enhancements (Non-Blocking)

* **Persistence**: JSON or simple file storage on shutdown/restart.
* **Search/Filter**: keyword search or status filters.
* **Undo/Redo**: extend linked list for backward traversal.
* **Concurrency**: add locks for multi-threaded modes.

This PRD ensures clarity of scope, a minimal but extensible design, and concrete guidance for implementation by an AI code editor or development team.
