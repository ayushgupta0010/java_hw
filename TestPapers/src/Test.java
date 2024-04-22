import java.util.ArrayList;

class Test {
    private ArrayList<Question> questions;
    private int totalPoints;

    public Test(ArrayList<Question> q) {
        questions = q;

        for(Question _q : questions) {
            if(_q != null) totalPoints += _q.getPoints();
        }
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String toString(boolean withAns) {
        String temp = "";
        for(int i = 0; i < questions.size(); ++i) {
            Question q = questions.get(i);
            if(q != null) {
                temp += String.format("%d. ", i + 1) + q + (withAns ? "\nCorrect Ans: " + q.getCorrectAns() + '\n' : "") +  "\n";
            }
        }
        return temp;
    }
}