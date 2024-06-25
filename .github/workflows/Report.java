package user_login;

import javax.swing.JOptionPane; // Importing JOptionPane for dialog boxes
import java.util.*; // Importing the necessary classes for List, Map, and Iterator

public class Report {

    // Method to show different report options to the user
    public void showReport(List<Map<String, String>> tasksList) {
        // Array of options for the user to choose from
        String[] option = {"Display finished tasks", "Longest duration", "Search task name", "Search developer", "Delete task", "Full report"};
        
        // Displaying a dialog with the options
        int report = JOptionPane.showOptionDialog(null, "What do you want to do?", "Task Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

        // Switch case to handle the user's choice
        switch (report) {
            case 0:
                displayDoneTasks(tasksList); // Show tasks that are done
                break;
            case 1:
                displayLongestDuration(tasksList); // Show the task with the longest duration
                break;
            case 2:
                searchTaskByName(tasksList); // Search for a task by its name
                break;
            case 3:
                searchTaskByDeveloper(tasksList); // Search for tasks by developer name
                break;
            case 4:
                deleteTaskByName(tasksList); // Delete a task by its name
                break;
            case 5:
                displayFullReport(tasksList); // Display a full report of all tasks
                break;
            default:
                JOptionPane.showMessageDialog(null, "Option not implemented yet."); // Default case for unimplemented options
                break;
        }
    }

    // Method to display tasks with status "Done"
    public static void displayDoneTasks(List<Map<String, String>> tasksList) {
        StringBuilder doneTasks = new StringBuilder(); // StringBuilder to accumulate the done tasks
        for (Map<String, String> task : tasksList) {
            if ("Done".equals(task.get("Task Status"))) {
                // Append task details if the status is "Done"
                doneTasks.append("Task Name: ").append(task.get("Task Name")).append("\n");
                doneTasks.append("Task Developer: ").append(task.get("Task Developer")).append("\n");
                doneTasks.append("Task Duration: ").append(task.get("Task Duration")).append("\n\n");
            }
        }
        if (doneTasks.length() == 0) {
            doneTasks.append("No tasks with status 'Done'."); // Message if no tasks are done
        }
        JOptionPane.showMessageDialog(null, doneTasks.toString()); // Display the accumulated tasks
    }

    // Method to display the task with the longest duration
    public static void displayLongestDuration(List<Map<String, String>> tasksList) {
        if (tasksList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available."); // Message if the list is empty
            return;
        }

        // Initialize with the first task
        Map<String, String> longestTask = tasksList.get(0);
        int maxDuration = Integer.parseInt(longestTask.get("Task Duration"));

        // Iterate through tasks to find the one with the longest duration
        for (Map<String, String> task : tasksList) {
            int duration = Integer.parseInt(task.get("Task Duration"));
            if (duration > maxDuration) {
                maxDuration = duration;
                longestTask = task;
            }
        }

        // Prepare and display the message with the longest task duration
        String message = "Developer with the longest task duration:\n";
        message += "Developer: " + longestTask.get("Task Developer") + "\n";
        message += "Duration: " + longestTask.get("Task Duration") + " hours\n";

        JOptionPane.showMessageDialog(null, message);
    }

    // Method to search for a task by its name
    public static void searchTaskByName(List<Map<String, String>> tasksList) {
        String taskName = JOptionPane.showInputDialog("Enter the task name to search:"); // Input dialog for task name
        if (taskName == null || taskName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Task name cannot be empty."); // Check if input is empty
            return;
        }

        StringBuilder foundTasks = new StringBuilder(); // StringBuilder to accumulate found tasks
        boolean taskFound = false;

        // Iterate through tasks to find matching task names
        for (Map<String, String> task : tasksList) {
            if (taskName.equalsIgnoreCase(task.get("Task Name"))) {
                taskFound = true;
                foundTasks.append("Task Name: ").append(task.get("Task Name")).append("\n");
                foundTasks.append("Task Developer: ").append(task.get("Task Developer")).append("\n");
                foundTasks.append("Task Status: ").append(task.get("Task Status")).append("\n\n");
            }
        }

        if (!taskFound) {
            foundTasks.append("No task found with the name '").append(taskName).append("'."); // Message if no task is found
        }

        JOptionPane.showMessageDialog(null, foundTasks.toString()); // Display the accumulated tasks
    }

    // Method to search for tasks by developer's name
    public static void searchTaskByDeveloper(List<Map<String, String>> tasksList) {
        String developerName = JOptionPane.showInputDialog("Enter the developer's name to search:"); // Input dialog for developer name
        if (developerName == null || developerName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Developer name cannot be empty."); // Check if input is empty
            return;
        }

        StringBuilder foundTasks = new StringBuilder(); // StringBuilder to accumulate found tasks
        boolean taskFound = false;

        // Iterate through tasks to find matching developer names
        for (Map<String, String> task : tasksList) {
            if (developerName.equalsIgnoreCase(task.get("Task Developer"))) {
                taskFound = true;
                foundTasks.append("Task Name: ").append(task.get("Task Name")).append("\n");
                foundTasks.append("Task Status: ").append(task.get("Task Status")).append("\n\n");
            }
        }

        if (!taskFound) {
            foundTasks.append("No tasks found for developer '").append(developerName).append("'."); // Message if no task is found
        }

        JOptionPane.showMessageDialog(null, foundTasks.toString()); // Display the accumulated tasks
    }

    // Method to delete a task by its name
    public static void deleteTaskByName(List<Map<String, String>> tasksList) {
        String taskName = JOptionPane.showInputDialog("Enter the task name to delete:"); // Input dialog for task name
        if (taskName == null || taskName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Task name cannot be empty."); // Check if input is empty
            return;
        }

        Iterator<Map<String, String>> iterator = tasksList.iterator(); // Iterator to safely remove elements
        boolean taskFound = false;

        while (iterator.hasNext()) {
            Map<String, String> task = iterator.next();
            if (taskName.equalsIgnoreCase(task.get("Task Name"))) {
                // Confirm deletion
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the task '" + taskName + "'?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    iterator.remove(); // Remove the task
                    JOptionPane.showMessageDialog(null, "Task '" + taskName + "' has been deleted.");
                } else {
                    JOptionPane.showMessageDialog(null, "Task '" + taskName + "' was not deleted.");
                }
                taskFound = true;
                break;
            }
        }

        if (!taskFound) {
            JOptionPane.showMessageDialog(null, "No task found with the name '" + taskName + "'."); // Message if no task is found
        }
    }

    // Method to display a full report of all tasks
    public static void displayFullReport(List<Map<String, String>> tasksList) {
        StringBuilder fullReport = new StringBuilder("Full Report of Tasks:\n\n"); // StringBuilder to accumulate the full report

        // Iterate through tasks to append their details to the report
        for (Map<String, String> task : tasksList) {
            fullReport.append("Task Name: ").append(task.get("Task Name")).append("\n");
            fullReport.append("Task Developer: ").append(task.get("Task Developer")).append("\n");
            fullReport.append("Task Status: ").append(task.get("Task Status")).append("\n");
            fullReport.append("Task Duration: ").append(task.get("Task Duration")).append(" hours\n\n");
        }

        JOptionPane.showMessageDialog(null, fullReport.toString()); // Display the full report
    }
}
