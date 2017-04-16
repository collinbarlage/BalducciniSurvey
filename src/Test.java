import java.util.Vector;

public class Test extends IO {
    private String name;
    private boolean gradeable;
    private Vector<Question> questions = new Vector<Question>();

    public Test(String surveyName) {
        name = surveyName;
        gradeable = true; //default value for a test
    }

    public void makeTest() {
        IO io = new IO();
        while(true) {

            io.prompt("Add a question:\n[1]\tTrue/False\n[2]\tMultiple Choice\n[3]\tShort Answer" +
                    "\n[4]\tEssay\n[5]\tRanking\n[6]\tMatching\n[7]\tDone");
            switch (io.response()) {
                case "1": //TrueFalse
                    break;

                case "2": //Multiple Choice
                    MultipleChoice newMc = new MultipleChoice();
                    newMc.makeQuestion();
                    this.addQuestion(newMc);
                    break;

                case "3": //Short Answer
                    break;

                case "4": //Essay
                    break;

                case "5": //Ranking
                    break;

                case "6": //Matching
                    break;

                case "7": //exit
                    break;

                default:
                    break;

            }
            if(io.response().equals("7"))
                break;
        }
    }

    public void display() {
        IO io = new IO();

        io.outputln("\n\n"+this.getType()+" "+this.getName()+"\n");
        for(int i=0; i<questions.size(); i++)
            questions.elementAt(i).display();
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public String getName() {
        return name;
    }

    public void typeSurvey() {
        gradeable = false;
    }

    public String getType() {
        if(gradeable)
            return "Survey";
        else
            return "Test";
    }
}
