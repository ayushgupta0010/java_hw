import java.time.LocalDateTime;

public class TaskMain {
    public static void main(String[] args) {
        Task task = new Task("finish assignment", 1, 120, LocalDateTime.of(2024, 3, 31, 22, 00));

        System.out.println("Name: " + task.getName());
        System.out.println("Priority: " + task.getPriority());
        System.out.println("Estimated Completion Time: " + task.getEstMinsToComplete());
        System.out.println("Due Date: " + Task.formatDate(task.getWhenDue()));

        task.increasePriority(3);
        System.out.println("Priority: " + task.getPriority());
        task.increasePriority(-3);
        System.out.println("Priority: " + task.getPriority());

        task.decreasePriority(2);
        System.out.println("Priority: " + task.getPriority());
        task.decreasePriority(1);
        System.out.println("Priority: " + task.getPriority());

        System.out.println("Is task overdue? " + task.overdue());

        task.setName("finish 1068 assignment");

        System.out.println(task);
    }
}
