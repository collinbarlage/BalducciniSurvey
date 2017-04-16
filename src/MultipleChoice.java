import java.util.Vector;

public class MultipleChoice extends Question {
    private Vector<String> choices = new Vector<String>();

    public void addChoices(String choice)   {
        choices.add(choice);
    }

    public void makeQuestion() {
        IO io = new IO();
        io.outputln("New Multiple Choice Question");
        io.prompt("\tEnter question prompt:");
        this.setPrompt(io.response());

        io.prompt("Enter a choice for this question: (enter nothing when finished)");
        while(io.response().equals("") == false) {
            this.addChoices(io.response());
            io.prompt();
        }
    }

    public void display() {
        IO io = new IO();
        io.outputln("\t"+this.getPrompt());
        for(int i=0; i<choices.size(); i++) {
            io.outputln("\t\t("+i+") "+choices.elementAt(i));
        }
        output("\n");
    }
}
