import java.util.Collections;
import java.util.Vector;

public class MultipleChoice extends Question implements java.io.Serializable {
    private Vector<String> choices = new Vector<String>();
    private Vector<String> displayChoices = new Vector<>();

    public MultipleChoice(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.getInput());

        if (!isSurvey)
            addCorrectChoice();
        else
            io.prompt("\tEnter choices for this question: (enter nothing when finished)");
        while (!io.getInput().equals("")) {
            this.addChoices(io.getInput());
            io.prompt();
        }
        //Randomize order
        displayChoices.addAll(choices);
        displayChoices.addAll(correctChoices);
        Collections.shuffle(displayChoices);
    }

    private void addCorrectChoice() {
        io.prompt("\tEnter correct choice(s): (enter nothing when finished)");
        while (!io.getInput().equals("")) {
            addCorrectChoices(io.getInput());
            io.prompt();
        }
        io.prompt("\tEnter incorrect choices:");
    }

    public void modify() {
        for (int i = 0; i < correctChoices.size(); i++) {
            io.prompt("Change correct choice '" + correctChoices.elementAt(i) + "': (enter nothing to skip)");
            if (!io.getInput().equals(""))
                correctChoices.set(i, io.getInput());
        }
        for (int i = 0; i < choices.size(); i++) {
            io.prompt("Change choice '" + choices.elementAt(i) + "': (enter nothing to skip)");
            if (!io.getInput().equals(""))
                choices.set(i, io.getInput());
        }
        //reset all choices
        displayChoices = new Vector<>();
        displayChoices.addAll(choices);
        displayChoices.addAll(correctChoices);
        Collections.shuffle(displayChoices);
    }

    public void display() {
        io.outputln("Multiple Choice:\n\t" + this.getPrompt());
        for (int i = 0; i < displayChoices.size(); i++) {
            io.outputln("\t\t(" + toLetter(i) + ") " + displayChoices.elementAt(i));
        }
        io.output("\n");
    }

    private void addChoices(String choice) {
        choices.add(choice);
    }

    private void addCorrectChoices(String choice) {
        correctChoices.add(choice);
    }

    public void take(Response res) {
        if(correctChoices.size() == 0)
            correctChoices.add("null");
        if(correctChoices.size()>1){
            io.outputln("\tPro tip: there are "+correctChoices.size()+" correct choices");
        }
        QuestionResponse qs = new QuestionResponse();
        for (int i = 0; i < correctChoices.size(); i++) {
            io.prompt();
            qs.add(displayChoices.elementAt(this.fromLetter(io.getInput())));
        }
        res.addResponse(qs);
    }
    public void tabulate(Vector<Response> responses, int q) {
        int correctCount[] = new int[correctChoices.size()];
        int incorrectCount[] = new int[choices.size()];
        for (int i = 0; i < responses.size(); i++) {
            for(int j=0; j<correctChoices.size(); j++) {
                if (responses.elementAt(i).getQuestionResponse(q).getResponses().contains(correctChoices.elementAt(j)))
                    correctCount[j]++;
            }
        }
        for (int i = 0; i < responses.size(); i++) {
            for(int j=0; j<choices.size(); j++) {
                if (responses.elementAt(i).getQuestionResponse(q).getResponses().contains(choices.elementAt(j)))
                    incorrectCount[j]++;
            }
        }
        if(correctChoices.size()>0) {
            for (int i = 0; i < correctChoices.size(); i++)
                io.outputln("\t\t" + correctChoices.elementAt(i) + ":\t" + correctCount[i]);
        }
        if(choices.size()>0) {
            for (int i = 0; i < choices.size(); i++)
                io.outputln("\t\t" + choices.elementAt(i) + ":\t" + incorrectCount[i]);
        }
    }

}
