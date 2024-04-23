class Question {
    final int MIN_DIFFICULTY = 1;
    final int MAX_DIFFICULTY = 100;

    private int points, difficulty, answerSpace;
    private String questionText, correctAns;

    public Question(int p, int d, int a, String q, String c) {
        points = p;
        answerSpace = a;
        questionText = q;
        correctAns = c;
        difficulty = Math.max(MIN_DIFFICULTY, Math.min(d, MAX_DIFFICULTY));
    }

    public int getPoints() {
        return points;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getAnswerSpace() {
        return answerSpace;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public String toString() {
        String temp = String.format("%s\t\t%d points\n", questionText, points);
        for(int i = 0; i < answerSpace; ++i) {
            temp += "Your answer: __________\n";
        }
        return temp;
    }

    public String toAnswerString(){
        return this.toString() + "\nCorrect Answer: " + correctAns + '\n';
    }
}

class ObjectiveQuestion extends Question {
    public ObjectiveQuestion(int p, int d, int a, String q, String c) {
        super(p, d, a, q, c);
    }

    public String toString() {
        return super.toString();
    }

    public String toAnswerString() {
        return super.toAnswerString();
    }
}

class FillInTheBlankQuestion extends Question {
    public FillInTheBlankQuestion(int p, int d, int a, String q, String c) {
        super(p, d, a, q, c);
    }

    public String toString() {
        return super.toString();
    }

    public String toAnswerString() {
        return super.toAnswerString();
    }
}

class MultipleChoiceQuestion extends Question {
    private String possibleAnswers;

    public MultipleChoiceQuestion(int p, int d, int a, String q, String c, String pa) {
        super(p, d, a, q, c);
        possibleAnswers = pa;
    }

    public String getPossibleAnswer() {
        return possibleAnswers;
    }

    public String toString() {
        String temp = super.toString();
        temp += "\nPossible Answers: \n" + possibleAnswers;
        return temp;
    }

    public String toAnswerString() {
        return super.toAnswerString();
    }
}