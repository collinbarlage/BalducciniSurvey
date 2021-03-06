import java.util.Vector;

public class TrueFalse extends Question implements java.io.Serializable {

    public TrueFalse(boolean isSurvey) {
        io.prompt("\tEnter true/false question prompt:");
        this.setPrompt(io.getInput());

        if (!isSurvey)
            addCorrectChoice();
        else
            correctChoices.add("null");
    }

    private void addCorrectChoice() {
        io.prompt("\tEnter correct choice: ('T' or 'F')");
        if (io.getInput().equals("T") || io.getInput().equals("t"))
            correctChoices.add("T");
        else
            correctChoices.add("F");
    }

    public void display() {
        io.outputln("True/False:\n\t" + this.getPrompt());
        io.outputln("\t\t(T)/(F)\n");
    }

    public void modify() {
        io.prompt("\tChange correct choice " + correctChoices.elementAt(0) + " : ('T' or 'F' or nothing to skip)");
        if (io.getInput().equals("T") || io.getInput().equals("t"))
            correctChoices.set(0, "T");
        else if (io.getInput().equals(""))
            correctChoices = correctChoices; //null on purpose
        else
            correctChoices.set(0, "F");

    }

    public void tabulate(Vector<Response> responses, int q) {
        int iTrue = 0, iFalse = 0;
        for (int i = 0; i < responses.size(); i++) {
            if(responses.elementAt(i).getQuestionResponse(q).getOne().equals("T"))
                iTrue++;
            if(responses.elementAt(i).getQuestionResponse(q).getOne().equals("F"))
                iFalse++;
        }
        io.outputln("\t\tTrue: "+iTrue+"\n\t\tFalse: "+iFalse);
    }
}
