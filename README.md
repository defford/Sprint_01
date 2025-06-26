# To-Do List Manager

A command-line Java application for multi-user to-do list management with manual data structure implementations.

## Features

- **Multi-user support**: Up to 50 unique users
- **Case-insensitive user names**: DANIEL = Daniel = daniel  
- **Visual output**: All messages beautifully boxed for clarity
- **Manual data structures**: Custom singly-linked lists and fixed-size arrays
- **In-memory storage**: No persistence (data resets on exit)

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Compilation & Execution
```bash
# Navigate to the src directory
cd src

# Compile all Java files
javac *.java

# Run the application
java Main
```

## Usage

### Main Menu
1. **Add user** - Register a new user (case-insensitive)
2. **List users** - View all registered users
3. **Select user** - Choose a user to manage their tasks
4. **Exit** - Quit the application

### User Menu (after selecting a user)
1. **Add task** - Create a new task with description
2. **Complete task** - Mark a task as done by index number
3. **View tasks** - Display all tasks with status
4. **Back to Main Menu** - Return to main menu

## Example Session
```
╔════════════════════════════════════╗
║ Welcome to the To-Do List Manager! ║
╚════════════════════════════════════╝

Main Menu:
1. Add user
2. List users  
3. Select user
4. Exit
Choose an option: 1
Enter user name: Alice

╔════════════════════════════════════╗
║ ✓ User 'Alice' added successfully. ║
╚════════════════════════════════════╝
```

## Architecture

- **Task.java** - Individual task with description and completion status
- **TaskList.java** - Singly-linked list of tasks
- **User.java** - User with name and owned task list
- **UserManager.java** - Fixed-size array managing up to 50 users
- **Main.java** - Command-line interface and application entry point

## Technical Notes

- Uses only standard Java libraries (no external dependencies)
- Manual implementation of data structures per project requirements
- Zero-based indexing for task operations
- Case-insensitive user name matching for better UX 