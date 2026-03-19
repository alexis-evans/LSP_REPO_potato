package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a task in the task management system.
 * A task stores an ID, a description, and a status (e.g., OPEN, IN_PROGRESS, CLOSED).
 * 
 * @author Alexis Evans
 */
public class Task {
    private String taskId;
    private String description;
    private String status;

    /**
     * Constructs a Task with the given ID and description.
     * Default status is set to "OPEN".
     * 
     * @param taskId the unique identifier for the task
     * @param description the task description
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }

    /**
     * Returns the task ID.
     * @return taskId
     */

    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the task description.
     * @return description
     */

    public String getDescription() {
        return description;
    }

    /**
     * Returns the task status.
     * @return status
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
