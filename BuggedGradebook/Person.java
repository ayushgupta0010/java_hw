import java.util.ArrayList;
public class Person {
    private String name;
    private ArrayList<Test> grades;
    private double average;

    /**
     * Constructor which initializes instance variables and assigns a name to the student.
     * @param name is a String which describes the name of the student
     */
    public Person(String name) {
        this.name = name;
        grades = new ArrayList<Test>();
        average = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Test> getGrades() {
        return grades;
    }

    public double getAverage() {
        return average;
    }
    public void addGrade(Test test) {
        grades.add(test);
        average = calculateAverage();
    }

    public double calculateAverage() {
        double sum = 0;
        for (int i = 0; i < grades.size(); i++) {
            Test test = grades.get(i);
            sum += test.getTestGrade();
        }
        return sum;
    }
}
