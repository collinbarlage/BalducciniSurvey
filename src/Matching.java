import java.util.Vector;

public class Matching extends Question implements java.io.Serializable {
    private Vector<String> choicesA = new Vector<String>();
    private Vector<String> choicesB = new Vector<String>();

    public Matching(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.response());

        io.prompt("\tEnter first list of choices: (enter nothing when finished)");
        while(!io.response().equals("")) {
            choicesA.add(io.response());
            io.prompt();
        }

        io.prompt("\tEnter corresponding list of answers:");
        for(int i=1; i<choicesA.size(); i++) {
            choicesB.add(io.response());
            io.prompt();
        }
        choicesB.add(io.response());

    }

    public void display() {
        io.outputln("\tRanking:\n\t"+this.getPrompt());
        for(int i=0; i<choicesA.size(); i++) {
            io.outputColumns("\t\t("+toLetter(i)+") "+choicesA.elementAt(i),"("+i+") "+choicesB.elementAt(i));
        }
        io.output("\n");
    }


}
