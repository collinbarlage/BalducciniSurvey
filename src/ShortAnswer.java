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
        io.prompt("\tEnter correct choice(s): (enter nothing when finished)");
        addCorrectAnswer(io.response());
        while(!io.response().equals("")) {
            addCorrectAnswer(io.response());
            io.prompt();
        }
    }

    public void display() {
        io.outputln("\tShort Answer:\n\t"+this.getPrompt()+"\n");
    }

    private void addCorrectAnswer(String choice)   {
        correctAnswers.add(choice);
    }
}


