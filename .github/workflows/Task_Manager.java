package user_login;

import javax.swing.*;
import java.util.*;

public class Task_Manager {
    static boolean checkTaskDescription = false;
    static boolean checkUserInput = true;
    // Declare variables here so they are accessible throughout the method//
    static String taskName, taskDescription, taskDeveloper, taskDuration, taskID, taskAmount;
    static int taskNumber = 0; // Initialize taskNumber to 0//

    //-------------------------------------------------------------------------------------------//
    public static void main(String[] args) {
        Task_Manager manager = new Task_Manager();
        manager.tasks();
    }
    //-------------------------------------------------------------------------------------------//

    public void tasks() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");//Welcoming output too the user//

        List<Map<String, String>> tasksList = new ArrayList<>();//Creates a new array where the three items: Add tasks, show report and quit are stored in//

        //-------------------------------------------------------------------------------------------//
        while (checkUserInput) {
            String[] option = {"Add tasks", "Show report", "Quit"};// Array created with varaible name option//
            int decision = JOptionPane.showOptionDialog(null, "What do you want to do?", "Task Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

            switch (decision) {//Switch created too determin different outcome for all 3 of the options//
                case 0: // Add tasks
                    //-------------------------------------------------------------------------------------------//
                    taskAmount = JOptionPane.showInputDialog(null, "How many tasks would you like to add?");//Prompts user to enter how many tasks he/she wants//
                    int numberOfTasks = Integer.parseInt(taskAmount);
                    //-------------------------------------------------------------------------------------------//

                    for (int i = 0; i < numberOfTasks; i++) {
                        Map<String, String> taskDetails = new HashMap<>();//Storage location for data input//
                       
                        //-------------------------------------------------------------------------------------------//
                        taskName = JOptionPane.showInputDialog("Enter a task name:");//Prompts user too choose a name for their task//
                        taskDetails.put("Task Number", String.valueOf(taskNumber));//Inputs the task number and then thereafter the task name too the task details final output of the tasks//
                        taskDetails.put("Task Name", taskName);
                       
                        //-------------------------------------------------------------------------------------------//
                        while (!checkTaskDescription) {//The while is user too check the complexity of the task description//
                            taskDescription = JOptionPane.showInputDialog("Enter the task description (50 characters maximum):");//Prompts user too add a task desription and gives the user parameters//
                            if (isTaskDescriptionValid(taskDescription)) {//If the task description meets the requirements then it inputs the description too the task details final output of the tasks//
                                checkTaskDescription = true;
                                taskDetails.put("Task Description", taskDescription);
                                JOptionPane.showMessageDialog(null, "Task successfully captured");//Outputs too show user that the desciption is added successfully//
                            } else {
                                JOptionPane.showMessageDialog(null, "Please enter a task description of less or equal than 50 characters.");//Outputs the user if the requirements are not met//
                            }
                        }
                        //-------------------------------------------------------------------------------------------//
                       
                        checkTaskDescription = false; // Reset for next task
                        //-------------------------------------------------------------------------------------------//
                       
                        taskDeveloper = JOptionPane.showInputDialog("Enter the developer details(First name and last name):");//Asks user too input the developers details//
                        taskDetails.put("Task Developer", taskDeveloper);//Inputs the developers details too the task details final output of tasks//
                        //-------------------------------------------------------------------------------------------//
                       
                        taskDuration = JOptionPane.showInputDialog("Enter the task duration (In hours):");//Prompts the user too input the amount of hours for the task//
                        taskDetails.put("Task Duration", taskDuration);//Inputs the previous prompt into the task details final output of tasks//
                        //-------------------------------------------------------------------------------------------//
                       
                        // Generate task ID
                        String taskNamePart = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();//Customises the previous details for the task ID within the parameters of the requirements//
                        String taskDeveloperPart = taskDeveloper.length() >= 3 ? taskDeveloper.substring(taskDeveloper.length() - 3).toUpperCase() : taskDeveloper.toUpperCase();
                        taskID = taskNamePart + ":" + taskNumber + ":" + taskDeveloperPart;
                        taskDetails.put("Task ID", taskID);//Inputs the created task ID too the task details final output of tasks//
                        //-------------------------------------------------------------------------------------------//
                       
                        String[] status = {"To do", "Done", "Doing"};//Prompts the user with a button option where he/she can input the status of the current task//
                        int statusChoice = JOptionPane.showOptionDialog(null, "What is the status of the task?", "Task Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
                        String taskStatus = status[statusChoice];
                        taskDetails.put("Task Status", taskStatus);//Inputs the status of the task too the task details final output of tasks//
                        //-------------------------------------------------------------------------------------------//
                       
                        tasksList.add(taskDetails);
                        taskNumber++; // Increment task number for the next task
                    }
                    //-------------------------------------------------------------------------------------------//

                    displayTasksAndTotalDuration(tasksList);//Outputs all the tasks created after all the inputs made by the user//
                    break;

                case 1: // Show reports
                    Report report = new Report();
                    report.showReport(tasksList);
                    break;

                case 2: // Quit
                    JOptionPane.showMessageDialog(null, "Quitting");
                    checkUserInput = false;
                    break;
            }
        }
    }
    //-------------------------------------------------------------------------------------------//

    public static boolean isTaskDescriptionValid(String taskDescription) {//This is where the task description is checked for its complexity//
        return taskDescription != null && taskDescription.length() <= 50;
    }
    //-------------------------------------------------------------------------------------------//
   
    public static boolean createTaskID(String taskID) {//This is where the task ID requirements are tested//
        return taskID != null && taskID.matches("[A-Z]{2}:[0-9]+:[A-Z]{3}");
    }
    //-------------------------------------------------------------------------------------------//

    public static int calculateTotalDuration(List<Map<String, String>> tasksList) {//This is where all this time that is entered for the task is calculated and outputed//
        int totalDuration = 0;
        for (Map<String, String> task : tasksList) {
            totalDuration += Integer.parseInt(task.get("Task Duration"));
        }
        return totalDuration;
    }
    //-------------------------------------------------------------------------------------------//

    public static void displayTasksAndTotalDuration(List<Map<String, String>> tasksList) {//This is where all the inputs of the user is taken and is put into the output format//
        StringBuilder allTasks = new StringBuilder();
        for (Map<String, String> task : tasksList) {
            allTasks.append("Task Number: ").append(task.get("Task Number")).append("\n");
            allTasks.append("Task Name: ").append(task.get("Task Name")).append("\n");
            allTasks.append("Task Description: ").append(task.get("Task Description")).append("\n");
            allTasks.append("Task Developer: ").append(task.get("Task Developer")).append("\n");
            allTasks.append("Task Duration: ").append(task.get("Task Duration")).append("\n");
            allTasks.append("Task ID: ").append(task.get("Task ID")).append("\n");
            allTasks.append("Task Status: ").append(task.get("Task Status")).append("\n\n");
        }
        int totalDuration = calculateTotalDuration(tasksList);//Inputs the total hours worked and outputs it//
        allTasks.append("Total Hours Worked: ").append(totalDuration).append("\n");
        JOptionPane.showMessageDialog(null, allTasks.toString());
    }
    //-------------------------------------------------------------------------------------------//
}