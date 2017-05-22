import java.util.Vector;

public class Essay extends Question implements java.io.Serializable {

    public Essay() {
        io.prompt("\tEnter the essay prompt:");
        this.setPrompt(io.getInput());
        correctChoices.add("null"); //dummy for taking
    }

    public Boolean isCorrect(Vector<String> user) {
      return null;
    }


    public void display() {
        io.outputln("Essay:\n\t" + this.getPrompt() + "\n");
    }

    public void tabulate(Vector<Response> responses, int q) {
        for (int i = 0; i < responses.size(); i++)
            io.outputln("\t\t"+responses.elementAt(i).getQuestionResponse(q).getOne());
    }


}


