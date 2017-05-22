import java.util.Vector;

/**
 * Created by 616 on 4/20/2017.
 */
public class ShortAnswer extends Question implements java.io.Serializable {

    public ShortAnswer(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.getInput());

        if(!isSurvey)
            addCorrectChoice();
    }

    private void addCorrectChoice() {
        io.prompt("\tEnter correct responses(s): (enter nothing when finished)");
        while(!io.getInput().equals("")) {
            addCorrectAnswer(io.getInput());
            io.prompt();
        }
    }

    public void modify() {
        for (int i = 0; i < correctChoices.size(); i++) {
            io.prompt("Change getInput '" + correctChoices.elementAt(i) + "': (enter nothing to skip)");
            if (!io.getInput().equals(""))
                correctChoices.set(i, io.getInput());
        }
    }

    public void display() {
        io.outputln("Short Answer:\n\t"+this.getPrompt()+"\n");
    }

    private void addCorrectAnswer(String choice)   {
        correctChoices.add(choice);
    }

    public void take(Response res) {
        io.prompt();
        QuestionResponse qs = new QuestionResponse(io.getInput());
        res.addResponse(qs);
    }

    public Boolean isCorrect(Vector<String> user) {
        return (correctChoices.contains(user.elementAt(0)));
    }


    public void tabulate(Vector<Response> responses, int q) {
        int correctCount[] = new int[correctChoices.size()];
        Vector<String> otherResponses = new Vector<String>();
        for (int i = 0; i < responses.size(); i++) {
            boolean wasCorrect = false;
            for(int j=0; j<correctChoices.size(); j++) {
                if (correctChoices.elementAt(j).equals(responses.elementAt(i).getQuestionResponse(q).getOne())) {
                    correctCount[j]++;
                    wasCorrect = true;
                }
            }
            if(!wasCorrect)
                otherResponses.add(responses.elementAt(i).getQuestionResponse(q).getOne());
        }
        if(!correctChoices.isEmpty()) {
            for (int i = 0; i < correctChoices.size(); i++)
                io.outputln("\t\t" + correctChoices.elementAt(i) + ":\t" + correctCount[i]);
        }
        io.outputln("\t\tOther responses:");
        for(String s: otherResponses)
            io.outputln("\t\t\t"+s);
    }


}


