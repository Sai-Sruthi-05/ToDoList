import java.text.SimpleDateFormat;
import java.util.*;

class ToDoList{
    public static String userChoice(int choice, ArrayList<String> taskNames, ArrayList<Date> taskDeadLines,Scanner sc){
        if(choice == 1){
            System.out.println("\nEnter your task name: ");
            String taskName=sc.nextLine();

            System.out.println("Enter your task deadline (dd-mm-yyyy): ");
            String deadLine=sc.nextLine();
            
            addTask(taskNames,taskDeadLines,taskName,deadLine);

        }else if(choice ==2){
            if(taskNames.isEmpty()){
                System.out.println("No tasks available.\n");
                return null;
            }
            display(taskNames,taskDeadLines);
            System.out.println("Enter task number to delete: ");
            int taskNumber=Integer.parseInt(sc.nextLine());
            deleteTask(taskNames,taskDeadLines,taskNumber);

        }else if(choice ==3){
            display(taskNames,taskDeadLines);

        }else if(choice == 4){
            if(taskNames.isEmpty()){
                System.out.println("There is no task to update.");
                return null;
            }
            display(taskNames, taskDeadLines);
            System.out.println("Enter task number to update: ");
            int taskNumber=Integer.parseInt(sc.nextLine());

            if (taskNumber <= 0 || taskNumber > taskNames.size()) {
            System.out.println("Invalid task number!\n");
            return null;
            }

            System.out.print("Enter new task name (or press Enter to keep unchanged): ");
            String newName = sc.nextLine();

            System.out.print("Enter new deadline (dd-mm-yyyy) (or press Enter to keep unchanged): ");
            String newDeadline = sc.nextLine();

            update(taskNames, taskDeadLines, taskNumber, newName, newDeadline);

        }else if(choice==5){
            return "Goodbye! exiting the application.";

        }else{
            System.out.println("Invalid choice\n");
        }

        return null;
    }

    public static void addTask(ArrayList<String> taskNames,ArrayList<Date> taskDeadLines,String taskName,String deadLine){
        Date deadlineDate=validateDate(deadLine);
        if (deadlineDate != null) {
            taskNames.add(taskName);
            taskDeadLines.add(deadlineDate);
            System.out.println("Task added successfully!\n");
        }
    }

    public static void display(ArrayList<String> taskNames,ArrayList<Date> taskDeadLines){
        if (taskNames.isEmpty()) {
            System.out.println("No tasks available.\n");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("\nYour Tasks:");

        for (int i = 0; i < taskNames.size(); i++) {
            String formattedDeadline = dateFormat.format(taskDeadLines.get(i));
            System.out.println((i + 1) + ". " + taskNames.get(i) + " - Deadline: " + formattedDeadline);
        }

        System.out.println();
    }

    public static void deleteTask(ArrayList<String> taskNames,ArrayList<Date> taskDeadLines,int taskNumber){
        int taskIndex = taskNumber - 1;
        if (taskIndex >= 0 && taskIndex < taskNames.size()) {
            String removedTask = taskNames.remove(taskIndex);
            taskDeadLines.remove(taskIndex);
            System.out.println("Task '" + removedTask + "' deleted successfully!\n");
        } else {
            System.out.println("Invalid task number!\n");
        }
    }
    public static Date validateDate(String deadLine){
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(deadLine);
            return date;
        } catch (Exception e) {
            System.out.println("Invalid date format! Please enter the deadline in DD-MM-YYYY format.\n");
            return null;
        }
    }
    public static void update(ArrayList<String> taskNames, ArrayList<Date> taskDeadlines,
                              int taskNumber, String newName, String newDeadline) {
            int index = taskNumber - 1;

            if (!newName.trim().isEmpty()) {
            taskNames.set(index, newName);
    }

            if (!newDeadline.trim().isEmpty()) {
            Date updatedDate = validateDate(newDeadline);
            if (updatedDate != null) {
            taskDeadlines.set(index, updatedDate);
        }
    }

    System.out.println("Task updated successfully!\n");
}
    public static void main(String[] args){
        ArrayList<String> taskNames=new ArrayList<>();
        ArrayList<Date> taskDeadLines=new ArrayList<>();

        Scanner sc=new Scanner(System.in);

        System.out.println("Welcome to TO-DO Application!\n");

        while(true){
            System.out.println("\nChoose an operation: \n");
            System.out.println("1. Add task");
            System.out.println("2. Delete task");
            System.out.println("3. Display task");
            System.out.println("4. Update task");
            System.out.println("5. Exit");

            System.out.println("\nEnter your choice: ");
            try {
            int choice = Integer.parseInt(sc.nextLine());
            String value = userChoice(choice, taskNames, taskDeadLines, sc);

        if ("Goodbye! exiting the application.".equals(value)) {
            System.out.println(value);
            break;
        }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.\n");
                }

            }
        }
    }
