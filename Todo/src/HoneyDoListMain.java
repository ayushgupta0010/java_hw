import java.time.LocalDateTime;

public class HoneyDoListMain {
    public static void main(String[] args) {
        HoneyDoList list = new HoneyDoList();
        list.addTask(new Task("finish assignment 1", 1, 120, LocalDateTime.of(2024, 2, 13, 22, 10)));
        list.addTask(new Task("finish assignment 2", 2, 20, LocalDateTime.of(2024, 4, 25, 22, 20)));
        list.addTask(new Task("finish assignment 3", 3, 10, LocalDateTime.of(2024, 3, 31, 22, 30)));
        list.addTask(new Task("finish assignment 4", 4, 140, LocalDateTime.of(2024, 2, 10, 22, 40)));
        list.addTask(new Task("finish assignment 5", 5, 180, LocalDateTime.of(2024, 5, 31, 22, 50)));
        list.addTask(new Task("finish assignment 6", 6, 126, LocalDateTime.of(2024, 3, 25, 23, 20)));
        list.addTask(new Task("finish assignment 7", 7, 121, LocalDateTime.of(2024, 4, 30, 21, 00)));
        list.addTask(new Task("finish assignment 8", 8, 190, LocalDateTime.of(2024, 1, 31, 12, 00)));
        list.addTask(new Task("finish assignment 9", 9, 200, LocalDateTime.of(2024, 2, 1, 02, 00)));

        System.out.println("\n" + list + "\n\n");

        System.out.println("Shortest Task: " + list.getTask(list.shortestTime()));
        System.out.println("\nIndex matched: " + list.find("FinIsh Assignment 4"));

        list.completeTask(3);
        list.completeTask(5);
        list.completeTask(8);

        System.out.println("\n" + list + "\n\n");
        System.out.println("Total task time: " + list.totalTime() + " minutes");
        
        System.out.println("\nOverdue tasks list:");
        for(Task task : list.overdueTasks()) {
            System.out.println(task);
        }
    }    
}
