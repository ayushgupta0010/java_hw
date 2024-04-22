import java.util.ArrayList;

class Test {
    private ArrayList<Question> questions;
    private int totalPoints;

    public Test(ArrayList<Question> q) {
        questions = q;

        for(Question _q : questions) {
            totalPoints += _q.getPoints();
        }
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String toString() {
        String temp = "";
        for(int i = 0; i < questions.size(); ++i) {
            Question q = questions.get(i);
            temp += String.format("%d. ", i + 1) + q + '\n';
        }
        return temp;
    }

    public String toAnswerString() {
        String temp = "";
        for(int i = 0; i < questions.size(); ++i) {
            Question q = questions.get(i);
            temp += String.format("%d. ", i + 1) + q.toAnswerString() + '\n';
        }
        return temp;
    }
}