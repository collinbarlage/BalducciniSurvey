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
        res.addResponse(io.getInput());
    }
}


