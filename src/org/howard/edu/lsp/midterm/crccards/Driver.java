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

