import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class Ranking extends Question implements java.io.Serializable {
    private Vector<String> choices = new Vector<String>();
    private Vector<String> displayChoices = new Vector<String>();

    public Ranking(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.response());

        io.prompt("\tEnter items to rank in the correct order: (enter nothing when finished)");
        while (!io.response().equals("")) {
            this.addChoices(io.response());
            io.prompt();
        }
        displayChoices.addAll(choices);
        Collections.shuffle(displayChoices);
    }

    public void modify() {
        for (int i = 0; i < choices.size(); i++) {
            io.prompt("Change rankable item "+i+" '" + choices.elementAt(i) + "': (enter nothing to skip)");
            if (!io.response().equals(""))
                choices.set(i, io.response());
        }
        displayChoices = new Vector<>();
        displayChoices.addAll(choices);
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
        choices.add(choice);
    }

}
