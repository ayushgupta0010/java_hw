import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class TestBank {
    final static String[] oqList = { "What is the smallest prime number?", "Which is 4th planet in the solar system?", "Who was the first man to step foot on moon?" };
    final static String[] fibList = { "_____ was the 16th US President.", "_____ is the current president of the US.", "_____ was the last president of the US." };
    final static String[] mcList = { "Who lives in a pineapple under the sea?", "Where was the Rocky movie filmed?", "Who was the main actor in the Rocky movie?" };

    final static String[] mcPossibleAnsList = { "1. Peter Griffin\n2. Scooby Doo\n3. Spongebob squarepants\n4. Eric Cartman\n", "1. New York\n2. Philadelphia\n3. California\n4. Chicago\n", "1. Sylvester Stallone\n2. Talia Shire\n3. Burt Young\n4. Carl Weathers\n" };

    final static String[] oqListAns = { "2", "Mars", "Neil Armstrong" };
    final static String[] fibListAns = { "Abraham Lincoln", "Joe Biden", "Donald Trump" };
    final static String[] mcListAns = { "3", "2", "1" };

    public static void main(String args[]) throws IOException {
        ArrayList<Question> questions = new ArrayList<Question>();

        Test test = createTest(questions);

        printTest(test);
        printTestAnswer(test);
        sendToFile(test);
    }

    public static void sendToFile(Test test) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the name of the file: ");
        String testFileName = sc.next();
        String ansKeyFileName = testFileName + " Answer Key.txt";

        File testFile = new File(testFileName + ".txt");
        File ansKeyFile = new File(ansKeyFileName);

        FileWriter testFw = new FileWriter(testFile);
        FileWriter ansKeyFw = new FileWriter(ansKeyFile);

        testFw.write(getHeader(test, false));
        ansKeyFw.write(getHeader(test, true));

        testFw.write(test.toString());
        ansKeyFw.write(test.toAnswerString());

        testFw.close();
        ansKeyFw.close();
    }

    public static Test createTest(ArrayList<Question> questions) {
        pickOqQuestions(questions);
        pickFibQuestions(questions);
        pickMcQuestions(questions);
        return new Test(questions);
    }

    public static void printTest(Test test) {
        System.out.printf(getHeader(test, false));
        System.out.println(test);
    }

    public static void printTestAnswer(Test test) {
        System.out.printf(getHeader(test, true));
        System.out.println(test.toAnswerString());
    }

    public static void pickOqQuestions(ArrayList<Question> questions) {
        int noQuesToPick = getRand(oqList.length) % oqList.length;
        for(int i = 0; i < noQuesToPick; ++i) {
            questions.add(new ObjectiveQuestion(getRand(4) + 1, getRand(100) + 1, 1, oqList[i], oqListAns[i]));
        }
    }

    public static void pickFibQuestions(ArrayList<Question> questions) {
        int noQuesToPick = getRand(fibList.length) % fibList.length;
        for(int i = 0; i < noQuesToPick; ++i) {
            questions.add(new FillInTheBlankQuestion(getRand(4) + 1, getRand(100) + 1, 1, fibList[i], fibListAns[i]));
        }
    }

    public static void pickMcQuestions(ArrayList<Question> questions) {
        int noQuesToPick = getRand(mcList.length) % mcList.length;
        for(int i = 0; i < noQuesToPick; ++i) {
            questions.add(new MultipleChoiceQuestion(getRand(4) + 1, getRand(100) + 1, 1, mcList[i], mcListAns[i], mcPossibleAnsList[i]));
        }
    }

    public static String getHeader(Test test, boolean isAns) {
        if(isAns) return String.format("********** Test With Answer Key **********\t\tTotal Points: %d\n\n", test.getTotalPoints());
        return String.format("********** Test **********\t\tTotal Points: %d\n\n", test.getTotalPoints());
    }

    public static int getRand(int max) {
        return new Random().nextInt(max);
    }
}