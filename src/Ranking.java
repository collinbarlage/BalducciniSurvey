import java.util.Collections;
import java.util.Vector;

public class Ranking extends Question implements java.io.Serializable {
    private Vector<String> displayChoices = new Vector<String>();

    public Ranking(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.getInput());

        io.prompt("\tEnter items to rank in the correct order: (enter nothing when finished)");
        while (!io.getInput().equals("")) {
            this.addChoices(io.getInput());
            io.prompt();
        }
        displayChoices.addAll(correctChoices);
        Collections.shuffle(displayChoices);
    }

    public void modify() {
        for (int i = 0; i < correctChoices.size(); i++) {
            io.prompt("Change rankable item "+i+" '" + correctChoices.elementAt(i) + "': (enter nothing to skip)");
            if (!io.getInput().equals(""))
                correctChoices.set(i, io.getInput());
        }
        displayChoices = new Vector<>();
        displayChoices.addAll(correctChoices);
        Collections.shuffle(displayChoices);
    }

    public void display() {
        io.outputln("Ranking:\n\t" + this.getPrompt());
        for (int i = 0; i < displayChoices.size(); i++) {
            io.outputln("\t\t(" + i + ") " + displayChoices.elementAt(i));
        }
        io.output("\n");
    }

    private void addChoices(String choice) {
        correctChoices.add(choice);
    }

}
