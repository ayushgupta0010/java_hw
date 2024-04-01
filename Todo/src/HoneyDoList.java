import java.util.Arrays;

public class HoneyDoList {
    private Task tasks[];
    private int numTasks;
    private final int INITIAL_CAPACITY = 5;

    public HoneyDoList() {
        this.tasks = new Task[INITIAL_CAPACITY];
        this.numTasks = 0;
    }

    public String toString() {
        return Arrays.toString(tasks);
    }

    public int find(String name) {
        for(int i = 0; i < numTasks; ++i) {
            if(tasks[i].getName().equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    public void addTask(Task task) {
        if(numTasks < tasks.length - 1) {
            tasks[numTasks++] = task;
            return;
        }
        
        Task newTasks[] = new Task[numTasks * 2];
        for(int i = 0; i < numTasks; ++i) {
            newTasks[i] = tasks[i];
        }
        newTasks[numTasks++] = task;

        tasks = newTasks;
    }

    public int totalTime() {
        int time = 0;
        for(Task task : tasks) {
            if(task != null) time += task.getEstMinsToComplete();
        }
        return time;
    }

    public int shortestTime() {
        if(numTasks == 0) return -1;

        int index = 0;
        for(int i = 1; i < numTasks; ++i) {
            if(tasks[i].getEstMinsToComplete() < tasks[index].getEstMinsToComplete()) index = i;
        }
        return index;
    }

    public Task completeTask(int index) {
        if(index < 0 || index >= numTasks) return null;

        Task tempTask = tasks[index];
        for(int i = index; i < numTasks; ++i) {
            tasks[i] = tasks[i + 1];
        }
        --numTasks;

        return tempTask;
    }

    public Task getTask(int index) {
        if(index < 0 || index >= numTasks) return null;
        return tasks[index];
    }

    public Task[] overdueTasks() {
        int c = 0;
        for(Task task : tasks) {
            if(task != null && task.overdue()) c++;
        }

        Task oveTask[] = new Task[c];
        for(Task task : tasks) {
            if(task != null && task.overdue()) oveTask[--c] = task;
        }

        return oveTask;
    }
}
