import java.util.Vector;

public class Response implements java.io.Serializable {
    IO io = new IO();
    private Vector<QuestionResponse> questionResponses = new Vector<>();
    private String name;
    private String surveyName;
    private int totalPoints;
    private int userPoints;

    public Response(String n, String s) {
        name = n;
        surveyName = s;
    }

//    public String getString() {
//        return questionResponses.elementAt(0);
//    }

//    public String getString(int i) {
//        return questionResponses.elementAt(i);
//    }

    public void addResponse(QuestionResponse qs) {
        questionResponses.add(qs);
    }

    public void grade(Test test) {
        totalPoints = 10 * questionResponses.size();
        io.outputln("\nResults:");
        for(int i = 0; i< questionResponses.size(); i++) {
            io.output("\t("+i+") - ");
            if(test.getQuestion(i).isCorrect(questionResponses.elementAt(i).getResponses()) == null) {
                io.outputln("Pending: N/A (a human must grade this)");
                totalPoints = totalPoints - 10;
            }
            else if(test.getQuestion(i).isCorrect(questionResponses.elementAt(i).getResponses())) {
                userPoints = userPoints + 10;
                io.outputln("Correct: 10pts");
            }
            else
                io.outputln("Incorrect: 0pts");
        }
        io.outputln("\n\tTOTAL: "+userPoints+"/"+totalPoints+"\t\n");
    }


    public String getName() {
        return name;
    }

    public String getSurveyName() {
        return surveyName;
    }
}
