import java.util.Vector;

/**
 * Created by 616 on 4/20/2017.
 */
public class ShortAnswer extends Question implements java.io.Serializable {
    private Vector<String> correctAnswers = new Vector<String>();

    public ShortAnswer(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.response());

        if(!isSurvey)
            addCorrectChoice();
    }

    private void addCorrectChoice() {
        io.prompt("\tEnter correct responses(s): (enter nothing when finished)");
        while(!io.response().equals("")) {
            addCorrectAnswer(io.response());
            io.prompt();
        }
    }

    public void modify() {
        for (int i = 0; i < correctAnswers.size(); i++) {
            io.prompt("Change response '" + correctAnswers.elementAt(i) + "': (enter nothing to skip)");
            if (!io.response().equals(""))
                correctAnswers.set(i, io.response());
        }
    }

    public void display() {
        io.outputln("Short Answer:\n\t"+this.getPrompt()+"\n");
    }

    private void addCorrectAnswer(String choice)   {
        correctAnswers.add(choice);
    }
}


