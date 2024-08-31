import java.util.*;
/**
 * Driver class to create and display a teacher's grade book to a user,
 * allowing for ways to modify or gather information about students.
 */
public class GradeBook {
    public static void main(String[] args) {
        ArrayList<Person> gradeBook = new ArrayList<>();
        gradeBook.add(new Person("Ben"));
        gradeBook.add(new Person("Lizzy"));
        gradeBook.add(new Person("Billy"));
        while (true) { //keeps prompting the user until they hit option 4 ("Exit")
            displayMenu(gradeBook);
        }
    }

    public static void displayMenu(ArrayList<Person> gradeBook) {
        System.out.println("1. Add grades");
        System.out.println("2. Check grades");
        System.out.println("3. Grade rankings");
        System.out.println("4. Exit");
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Your choice: ");
        int choice = scan.nextInt();
        System.out.println();
        switch (choice) {
            case 1: //adds grade to user (either Ben, Lizzy, or Billy). asks for test name and their grade.
                System.out.println("adding grades -- who do you want to add a grade to?");
                displayNames(gradeBook);
                int input;
                do {
                    System.out.print("enter your number: ");
                    input = scan.nextInt();
                } while (input <= 0 || input > gradeBook.size());
                Person student = gradeBook.get(input - 1);
                System.out.println("your choice is " + student.getName());
                //now add the test to the person
                System.out.print("what's the name of the test: ");
                String testName = scan.next();
                System.out.print("what's the grade of the test: ");
                int testGrade = scan.nextInt();
                student.addGrade(new Test(testName, testGrade));
                break;
            case 2: //check grades
                System.out.println("showing grades: \n");
                for (int i = 0; i < gradeBook.size(); i++) {
                    System.out.printf("%d. %s %n", i+1, gradeBook.get(i).getName());
                    System.out.println("-------");
                    ArrayList<Test> grades = gradeBook.get(i).getGrades();
                    for (int j = 0; j < grades.size(); j++) {
                        System.out.printf("name: %s %n grade: %d %n", grades.get(j).getTestName(), grades.get(j).getTestGrade());
                    }
                    System.out.println("AVERAGE: " + gradeBook.get(i).getAverage());
                    System.out.println();
                }
                break;
            case 3: //ranks students based on average grade (#1 is the best, #2 is second best, etc.)
                if (gradeBook.isEmpty()) {
                    System.out.println("NO STUDENTS");
                }
                else {
                    System.out.println("\nshowing grade rankings: ");
                    rankStudents(gradeBook);
                }
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Invalid choice");
        }
        System.out.println();
    }

    //uses reverse bubble sort to rank students based on average grades
    public static void rankStudents(ArrayList<Person> gradeBook) {
        ArrayList<Person> rankList = new ArrayList<>(gradeBook);
        for (int i = 0; i < rankList.size() - 1; i++) {
            for (int j = 0; j < rankList.size() - i - 1; j++) {
                if (rankList.get(j).getAverage() > rankList.get(j+1).getAverage()) {
                    //swap elements
                    Collections.swap(rankList, i, j+1);
                }
            }
        }
        for (int index = 0; index < rankList.size(); index++) {
            System.out.printf("%d. %s %.2f %n", index+1, rankList.get(index).getName(), gradeBook.get(index).getAverage());
        }
    }

    /*
    prints out names in the following order -- this method is correct
    1. Ben
    2. Lizzy
    3. Billy
     */
    public static void displayNames(ArrayList<Person> gradeBook) {
        for (int i = 0; i < gradeBook.size(); i++) {
            System.out.printf("%d. %s %n", i+1, gradeBook.get(i).getName());
        }
        System.out.println();
    }
}
