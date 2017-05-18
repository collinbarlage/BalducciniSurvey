import java.util.Collections;
import java.util.Vector;

public class Matching extends Question implements java.io.Serializable {
    private Vector<String> choicesA = new Vector<String>();
    private Vector<String> choicesB = new Vector<String>();
    private Vector<String> displayA = new Vector<String>();
    private Vector<String> displayB = new Vector<String>();

    public Matching(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.response());

        io.prompt("\tEnter first list of choices: (enter nothing when finished)");
        while (!io.response().equals("")) {
            choicesA.add(io.response());
            io.prompt();
        }

        io.prompt("\tEnter corresponding list of answers:");
        for (int i = 1; i < choicesA.size(); i++) {
            choicesB.add(io.response());
            io.prompt();
        }
        choicesB.add(io.response());

        displayA.addAll(choicesA);
        displayB.addAll(choicesB);
        Collections.shuffle(displayA);
        Collections.shuffle(displayB);
    }

    public void modify() {
        for (int i = 0; i < choicesA.size(); i++) {
            io.prompt("Change item in 1st list '" + choicesA.elementAt(i) + "': (enter nothing to skip)");
            if (!io.response().equals(""))
                choicesA.set(i, io.response());
        }
        for (int i = 0; i < choicesB.size(); i++) {
            io.prompt("Change item in 2nd list '" + choicesB.elementAt(i) + "': (enter nothing to skip)");
            if (!io.response().equals(""))
                choicesB.set(i, io.response());
        }
        //reset all choices
        displayA = new Vector<>();
        displayB = new Vector<>();
        displayA.addAll(choicesA);
        displayB.addAll(choicesB);
        Collections.shuffle(displayA);
        Collections.shuffle(displayB);
    }

    public void display() {
        io.outputln("Ranking:\n\t" + this.getPrompt());
        for (int i = 0; i < displayA.size(); i++) {
            io.outputColumns("\t\t(" + toLetter(i) + ") " + displayA.elementAt(i), "(" + i + ") " + displayB.elementAt(i));
        }
        io.output("\n");
    }


}
