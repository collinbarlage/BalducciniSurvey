import java.util.Collections;
import java.util.Vector;

public class MultipleChoice extends Question implements java.io.Serializable {
    private Vector<String> choices = new Vector<String>();
    private Vector<String> correctChoices = new Vector<String>();
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

    public String getCorrectChoice(int i) {
        return correctChoices.elementAt(i);
    }
}
