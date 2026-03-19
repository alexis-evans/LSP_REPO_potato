# AI Tool: ChatGPT

## Transcript
Q: based on the uploaded files, do question 1 step by step of the midterm

A: Thought for 1m 0s

Here is a step-by-step solution for Question 1 based on your uploaded midterm prompt and your OO course materials on classes, instance variables, collaboration, encapsulation, and Java class structure. The slide deck explains that classes define structure and behavior, instance variables store per-object data, and OO programs are built from collaborating objects.

Question 1, Step by Step
Step 1: Identify the classes from the CRC cards

The CRC cards give you two classes:

Task

TaskManager

That matches the OO idea that a class is a unit of abstraction that describes structure and behavior for its instances.

Task responsibilities

store task information

update task status

provide task details

TaskManager responsibilities

store tasks

add new tasks

find a task by ID

return tasks by status

Collaboration

TaskManager collaborates with Task

Task does not need to know about TaskManager

Step 2: Decide the fields for Task

From the prompt, Task needs:

taskId

description

status

Since the slides stress encapsulation and information hiding, these should be private, not public.

Step 3: Decide the constructor and rules for Task

The constructor is:

public Task(String taskId, String description)

Rule:

default status must be "OPEN"

So when a Task is created, it should always start as OPEN.

Step 4: Decide the methods for Task

Required methods:

getTaskId()

getDescription()

getStatus()

setStatus(String status)

toString()

Important rule for setStatus:

valid statuses:

OPEN

IN_PROGRESS

COMPLETE

if anything else is passed, set status to UNKNOWN

Important rule for toString():

format must be:

T1 Write report [OPEN]

Step 5: Choose the internal data structure for TaskManager

The prompt says TaskManager must support:

storing tasks

locating a task by taskId

preventing duplicate task IDs

retrieving tasks by status

The best structure here is a:

Map<String, Task>

Why this works:

fast lookup by taskId

easy duplicate detection using the key

still easy to loop through values and collect tasks by status

A HashMap<String, Task> is a strong choice.

Step 6: Decide the methods for TaskManager

Required methods:

addTask(Task task)

findTask(String taskId)

getTasksByStatus(String status)

Rules:

duplicate ID → throw IllegalArgumentException

not found in findTask() → return null

getTasksByStatus() → return a List<Task>

Step 7: Write Task.java
package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a task in the task management system.
 * A task stores an ID, description, and status.
 *
 * @author Alexis Evans
 */
public class Task {
    private String taskId;
    private String description;
    private String status;

    /**
     * Constructs a Task with the given ID and description.
     * The default status is OPEN.
     *
     * @param taskId the unique task ID
     * @param description the task description
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }

    /**
     * Returns the task ID.
     *
     * @return the task ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task status.
     *
     * @return the task status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the task status.
     * Valid values are OPEN, IN_PROGRESS, and COMPLETE.
     * Any other value sets the status to UNKNOWN.
     *
     * @param status the new task status
     */
    public void setStatus(String status) {
        if ("OPEN".equals(status) || "IN_PROGRESS".equals(status) || "COMPLETE".equals(status)) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns a string representation of the task.
     *
     * @return task in the format: taskId description [status]
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}
Step 8: Write TaskManager.java
package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects.
 * Supports adding tasks, finding tasks by ID, and retrieving tasks by status.
 *
 * @author Alexis Evans
 */
public class TaskManager {
    private Map<String, Task> tasks;

    /**
     * Constructs a TaskManager with an empty task collection.
     */
    public TaskManager() {
        tasks = new HashMap<>();
    }

    /**
     * Adds a task to the manager.
     * Throws an exception if a task with the same ID already exists.
     *
     * @param task the task to add
     * @throws IllegalArgumentException if the task ID already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate task ID");
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds a task by its task ID.
     *
     * @param taskId the ID of the task to find
     * @return the matching task, or null if not found
     */
    public Task findTask(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Returns all tasks whose status matches the given status.
     *
     * @param status the status to match
     * @return a list of matching tasks
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
Step 9: Keep the provided Driver unchanged

Use the exact driver from the prompt:

package org.howard.edu.lsp.midterm.crccards;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task t1 = new Task("T1", "Write report");
        Task t2 = new Task("T2", "Study for exam");
        Task t3 = new Task("T3", "Submit homework");

        manager.addTask(t1);
        manager.addTask(t2);
        manager.addTask(t3);

        t2.setStatus("IN_PROGRESS");

        System.out.println("OPEN TASKS");
        List<Task> openTasks = manager.getTasksByStatus("OPEN");
        for (Task t : openTasks) {
            System.out.println(t.toString());
        }

        System.out.println("\nTESTING DUPLICATE TASK ID");
        try {
            Task duplicate = new Task("T1", "Duplicate task");
            manager.addTask(duplicate);
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate task ID detected.");
        }

        System.out.println("\nTESTING INVALID STATUS");
        Task t4 = new Task("T4", "Invalid status test");
        t4.setStatus("DONE");
        System.out.println(t4.toString());

        System.out.println("\nTESTING FIND TASK");
        Task found = manager.findTask("T2");
        System.out.println(found);

        Task notFound = manager.findTask("T99");
        if (notFound == null) {
            System.out.println("Task not found.");
        }
    }
}
Step 10: Expected output

Because T2 is moved to IN_PROGRESS, the open tasks are T1 and T3.

Possible output:

OPEN TASKS
T1 Write report [OPEN]
T3 Submit homework [OPEN]

TESTING DUPLICATE TASK ID
Duplicate task ID detected.

TESTING INVALID STATUS
T4 Invalid status test [UNKNOWN]

TESTING FIND TASK
T2 Study for exam [IN_PROGRESS]
Task not found.

The order of open tasks should match the sample if your iteration order happens to align, but since HashMap does not guarantee ordering, the safer exam move is to use LinkedHashMap if you want insertion order preserved.

Here is that one small improvement:

private Map<String, Task> tasks = new LinkedHashMap<>();

That helps your displayed order stay as:

T1

T2

T3

and then OPEN tasks print:

T1

T3

Step 11: Write crccards.md

Put this in:

org/howard/edu/lsp/midterm/crccards/doc/crccards.md

Content:

TaskManager collaborates with Task because its responsibilities are to store tasks, add new tasks, find a task by ID, and return tasks by status. To perform those responsibilities, TaskManager must create, store, access, and inspect Task objects.

Task does not collaborate with TaskManager because its responsibilities are limited to storing its own information, updating its own status, and providing its own details. A Task can perform all of those responsibilities without needing to know how tasks are managed collectively.
Step 12: Write development_log.md

If you used ChatGPT, you must include the complete transcript according to the prompt. Since you are using me right now, you should not write “No external assistance was used.”

Put this in:

org/howard/edu/lsp/midterm/crccards/doc/development_log.md

A safe structure is:

# Development Log

## AI Tool Used
ChatGPT

## How it helped
Used to:
- interpret the Task and TaskManager requirements
- decide on an internal Java collection for TaskManager
- draft Java class implementations
- draft Javadoc comments
- draft the CRC collaboration explanation

## Complete Transcript
[Paste the complete conversation transcript here.]

For the exam, your professor’s wording is strict: include the full prompts and responses, not a summary.

Step 13: Final directory structure
org/
└── howard/
    └── edu/
        └── lsp/
            └── midterm/
                └── crccards/
                    ├── Driver.java
                    ├── Task.java
                    ├── TaskManager.java
                    └── doc/
                        ├── crccards.md
                        └── development_log.md
Best final version to submit

Use LinkedHashMap instead of HashMap so your printed order stays stable.

Here is the polished TaskManager.java version I would submit:

package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects.
 * Supports adding tasks, finding tasks by ID, and retrieving tasks by status.
 *
 * @author Alexis Evans
 */
public class TaskManager {
    private Map<String, Task> tasks;

    /**
     * Constructs a TaskManager with an empty task collection.
     */
    public TaskManager() {
        tasks = new LinkedHashMap<>();
    }

    /**
     * Adds a task to the manager.
     * Throws an exception if a task with the same ID already exists.
     *
     * @param task the task to add
     * @throws IllegalArgumentException if the task ID already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate task ID");
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds a task by its task ID.
     *
     * @param taskId the ID of the task to find
     * @return the matching task, or null if not found
     */
    public Task findTask(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Returns all tasks whose status matches the given status.
     *
     * @param status the status to match
     * @return a list of matching tasks
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
Why this answer fits the course material

This solution follows the OO ideas from your slides:

classes hold both data and behavior

instance variables represent object state

collaborating objects work together to perform a task

encapsulation is preserved by keeping fields private and exposing public methods only as needed

If you want, I can do Question 2 step by step next in the same format.