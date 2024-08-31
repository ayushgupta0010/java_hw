
public class Test {
    private String testName;
    private int testGrade;
    /**
     * Constructor which initializes instance variables and assigns a name/grade for a given test.
     * @param name is a String which describes the name of the test
     * @param grade is an Integer which describes the grade of the test
     */
    public Test(String name, int grade) {
        testName = name;
        testGrade = grade;
    }

    public String getTestName() {
        return testName;
    }
    public int getTestGrade() {
        return testGrade;
    }
}
