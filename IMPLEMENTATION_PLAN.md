# To-Do List Manager - Implementation Plan

## Project Overview
Building a command-line Java application for multi-user to-do list management with manual data structure implementations (no external libraries). This plan serves as the roadmap for systematic development.

---

## Implementation Strategy

### 1. Development Approach
- **Bottom-up implementation**: Start with core data structures, then build user management, finally CLI
- **Incremental validation**: Test each component as it's built
- **Minimal viable approach**: Implement only what's specified in PRD
- **Single-threaded focus**: No concurrency considerations needed

### 2. Project Structure
```
Sprint_01/
├── src/
│   ├── Task.java
│   ├── TaskList.java
│   ├── User.java
│   ├── UserManager.java
│   └── Main.java
└── IMPLEMENTATION_PLAN.md (this file)
```

---

## Phase 1: Core Data Models (Foundation)

### 1.1 Task Class Implementation
**Priority**: High | **Estimated Effort**: 30 minutes

**Requirements to implement**:
- Private fields: `String description`, `boolean done` (default false)
- Constructor: `Task(String desc)`
- Method: `markDone()` - sets done to true
- Method: `toString()` - returns formatted string with checkbox

**Implementation Details**:
- Simple POJO with minimal logic
- ToString format: `[x] description` (done) or `[ ] description` (pending)

**Success Criteria**:
- Task can be created with description
- Task can be marked as done
- Task displays correctly formatted string

---

### 1.2 TaskList Class Implementation  
**Priority**: High | **Estimated Effort**: 1.5 hours

**Requirements to implement**:
- Inner Node class with `Task data` and `Node next`
- Private field: `Node head`
- Method: `add(Task t)` - append to tail of linked list
- Method: `markDone(int idx)` - traverse to index and mark task done
- Method: `printAll()` - iterate and print all tasks with index numbers

**Implementation Details**:
- Singly-linked list implementation from scratch
- Zero-based indexing for user interface
- Handle empty list cases gracefully
- Linear traversal for index access (O(n) acceptable per NFR-2)

**Technical Challenges**:
- Proper tail insertion without losing head reference
- Index boundary checking for markDone operation
- Correct index display starting from 0

**Success Criteria**:
- Can add multiple tasks to list
- Can mark tasks complete by index
- Displays tasks in correct format with indices
- Handles invalid indices gracefully

---

## Phase 2: User Management Layer

### 2.1 User Class Implementation
**Priority**: High | **Estimated Effort**: 45 minutes

**Requirements to implement**:
- Private fields: `String name`, `TaskList tasks`
- Constructor: `User(String name)` - initializes with empty task list
- Getter: `getName()` returns user name
- Method: `addTask(String desc)` - creates Task and adds to TaskList
- Method: `completeTask(int idx)` - delegates to TaskList with error handling
- Method: `printTasks()` - prints header and delegates to TaskList

**Implementation Details**:
- User owns and manages their TaskList instance
- Error handling for invalid task indices
- Consistent user experience messaging

**Success Criteria**:
- User can be created with unique name
- User can manage their independent task list
- Error messages display clearly for invalid operations

---

### 2.2 UserManager Class Implementation
**Priority**: High | **Estimated Effort**: 1 hour

**Requirements to implement**:
- Private fields: `User[] users = new User[50]`, `int count = 0`
- Method: `addUser(String name)` - check uniqueness and capacity, return boolean
- Method: `getUser(String name)` - linear search, return User or null
- Method: `listUsers()` - print all user names or "(no users)"

**Implementation Details**:
- Fixed-size array with manual capacity management
- Case-sensitive name comparison for uniqueness
- Linear search through occupied slots only (0 to count-1)
- Clean messaging for edge cases

**Technical Challenges**:
- Efficient uniqueness checking during addUser
- Proper bounds checking for array operations
- Memory-efficient storage (don't initialize all 50 slots)

**Success Criteria**:
- Can register up to 50 unique users
- Prevents duplicate user names
- Can retrieve existing users by name
- Lists users in insertion order

---

## Phase 3: Command-Line Interface

### 3.1 Main Class - Core CLI Structure
**Priority**: High | **Estimated Effort**: 2 hours

**Requirements to implement**:
- `main(String[] args)` - application entry point
- Main menu loop with options 1-4
- `userMenu(User u, Scanner in)` - user-specific menu loop
- Input validation and error handling
- Clean exit functionality

**Implementation Details**:
- Scanner for user input throughout application
- Menu-driven interface with clear option numbering
- Proper input validation with try-catch for numbers
- Graceful error recovery (invalid input doesn't crash app)

**Menu Structure**:
```
Main Menu:
1. Add user
2. List users  
3. Select user
4. Exit

User Menu (after selecting user):
1. Add task
2. Complete task
3. View tasks
4. Back to Main Menu
```

**Technical Challenges**:
- Input validation for menu choices and task indices
- Exception handling for NumberFormatException
- Clean scanner usage to avoid input buffer issues
- User-friendly error messages

**Success Criteria**:
- Displays clear menu options
- Handles invalid input gracefully
- Transitions between menus correctly
- Provides feedback for all user actions

---

## Phase 4: Integration & Error Handling

### 4.1 End-to-End Integration
**Priority**: Medium | **Estimated Effort**: 1 hour

**Requirements to implement**:
- Complete workflow testing from Main → UserManager → User → TaskList → Task
- Consistent error messaging across all components
- Edge case handling (empty states, invalid inputs)

**Integration Points**:
- Main class instantiates UserManager
- UserManager creates and retrieves User instances
- User instances manage TaskList instances
- TaskList manages Task instances

**Success Criteria**:
- All components work together seamlessly
- Data flows correctly through all layers
- No memory leaks or dangling references

---

### 4.2 Error Handling & Validation
**Priority**: Medium | **Estimated Effort**: 45 minutes

**Requirements to implement**:
- Duplicate user name rejection
- Invalid task index handling  
- NumberFormatException handling for menu inputs
- Out-of-bounds protection for arrays and lists

**Error Message Standards**:
- Clear, user-friendly messages
- Consistent formatting
- Actionable guidance where possible

**Success Criteria**:
- Application never crashes from user input
- All error conditions provide helpful feedback
- Users can recover from errors and continue

---

## Phase 5: Final Validation & Polish

### 5.1 Acceptance Criteria Validation
**Priority**: High | **Estimated Effort**: 30 minutes

**Validation Checklist**:
- [ ] Can register up to 50 unique users
- [ ] Can add, view, and complete tasks per user
- [ ] All data kept in memory (no persistence)
- [ ] No external collection frameworks used
- [ ] CLI menus work as specified
- [ ] Error handling works correctly

### 5.2 Code Quality Review
**Priority**: Medium | **Estimated Effort**: 30 minutes

**Review Areas**:
- Code matches PRD class designs exactly
- Proper encapsulation and access modifiers
- Consistent naming conventions
- Clear separation of concerns
- No unused imports or dead code

---

## Implementation Timeline

| Phase | Components | Estimated Time | Dependencies |
|-------|-----------|----------------|--------------|
| 1 | Task, TaskList | 2 hours | None |
| 2 | User, UserManager | 1.75 hours | Phase 1 complete |
| 3 | Main (CLI) | 2 hours | Phase 2 complete |
| 4 | Integration & Error Handling | 1.75 hours | Phase 3 complete |
| 5 | Validation & Polish | 1 hour | Phase 4 complete |
| **Total** | **All Components** | **~8.5 hours** | **Sequential** |

---

## Risk Assessment & Mitigation

### Technical Risks
1. **Linked List Implementation Complexity**
   - *Risk*: Incorrect pointer management
   - *Mitigation*: Implement add() first, test thoroughly before markDone()

2. **Array Bounds Management**
   - *Risk*: Index out of bounds errors
   - *Mitigation*: Consistent bounds checking, use count variable properly

3. **Input Validation Gaps**
   - *Risk*: Unhandled edge cases causing crashes
   - *Mitigation*: Comprehensive try-catch blocks, test with invalid inputs

### Process Risks
1. **Scope Creep**
   - *Risk*: Adding features not in PRD
   - *Mitigation*: Strict adherence to PRD requirements, reference this plan

2. **Over-Engineering**
   - *Risk*: Adding complexity beyond requirements
   - *Mitigation*: Implement minimal viable solution first

---

## Success Metrics

### Functional Success
- All 5 classes implemented as specified in PRD
- All functional requirements (FR-1.1 through FR-3.2) working
- All acceptance criteria met

### Technical Success  
- No external dependencies beyond standard Java
- Clean, readable code following PRD architecture
- Proper error handling without crashes

### User Experience Success
- Intuitive CLI navigation
- Clear feedback messages
- Graceful error recovery

---

## Next Steps
1. **Setup**: Create src directory structure
2. **Start Phase 1**: Begin with Task class implementation
3. **Track Progress**: Update this plan as components are completed
4. **Document Issues**: Note any deviations or challenges encountered

This implementation plan will be updated throughout development to reflect actual progress and any adjustments needed. 