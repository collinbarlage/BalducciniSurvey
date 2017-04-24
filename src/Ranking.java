import java.util.Vector;

public class Ranking extends Question implements java.io.Serializable {
    private Vector<String> choices = new Vector<String>();

    public Ranking(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.response());

        io.prompt("\tEnter items to rank in the correct order: (enter nothing when finished)");
        while(!io.response().equals("")) {
            this.addChoices(io.response());
            io.prompt();
        }
    }

    public void display() {
        io.outputln("\tRanking:\n\t"+this.getPrompt());
        for(int i=0; i<choices.size(); i++) {
            io.outputln("\t\t("+i+") "+choices.elementAt(i));
        }
        io.output("\n");
    }

    private void addChoices(String choice)   {
        choices.add(choice);
    }

}
