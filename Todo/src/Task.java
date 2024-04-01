import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Task {
    String name;
    int priority, estMinsToComplete;
    LocalDateTime whenDue;

    public Task(String name, int priority, int estMinsToComplete, LocalDateTime whenDue) {
        this.name = name;
        this.priority = priority;
        this.estMinsToComplete = estMinsToComplete;
        this.whenDue = whenDue;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getEstMinsToComplete() {
        return estMinsToComplete;
    }

    public LocalDateTime getWhenDue() {
        return whenDue;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setEstMinsToComplete(int newEstMinsToComplete) {
        this.estMinsToComplete = newEstMinsToComplete;
    }

    public void setWhenDue(LocalDateTime newWhenDue) {
        this.whenDue = newWhenDue;
    }

    public String toString() {
        return String.format("Task(name = %s, priority = %d, estMinsToComplete = %d, whenDue = %s)", this.name, this.priority, this.estMinsToComplete, formatDate(whenDue));
    }

    public void increasePriority(int amount) {
        if (amount <= 0) return;
        this.priority += amount;
    }

    public void decreasePriority(int amount) {
        if (amount <= 0) return;
        this.priority = Math.max(0, priority - amount);
    }

    public boolean overdue() {
        return LocalDateTime.now(ZoneId.of("America/New_York")).isAfter(this.whenDue);
    }

    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
    }
}
