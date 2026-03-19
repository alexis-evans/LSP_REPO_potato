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
