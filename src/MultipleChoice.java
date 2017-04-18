import java.util.Vector;

public class MultipleChoice extends Question implements java.io.Serializable {
    private Vector<String> choices = new Vector<String>();
    private Vector<String> correctChoices = new Vector<String>();

    public MultipleChoice(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.response());

        if(!isSurvey)
            addCorrectChoice();
        else
            io.prompt("\tEnter a choice for this question: (enter nothing when finished)");
        while(!io.response().equals("")) {
            this.addChoices(io.response());
            io.prompt();
        }
    }

    private void addCorrectChoice() {
        io.prompt("\tEnter correct choice(s):");
        addCorrectChoices(io.response());
        io.prompt("\tEnter incorrect choices:");
    }

    public void display() {
        io.outputln("\tMultiple Choice:\n\t"+this.getPrompt());
        for(int i=0; i<choices.size(); i++) {
            io.outputln("\t\t("+toLetter(i)+") "+choices.elementAt(i));
        }
        io.output("\n");
    }

    private void addChoices(String choice)   {
        choices.add(choice);
    }

    private void addCorrectChoices(String choice)   {
        correctChoices.add(choice);
    }

    public String getCorrectChoice(int i) {
        return correctChoices.elementAt(i);
    }
}
