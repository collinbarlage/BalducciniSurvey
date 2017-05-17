
public class TrueFalse extends Question implements java.io.Serializable {
    boolean correctChoice;

    public TrueFalse(boolean isSurvey) {
        io.prompt("\tEnter true/false question prompt:");
        this.setPrompt(io.response());

        if(!isSurvey)
            addCorrectChoice();
    }

    private void addCorrectChoice() {
        io.prompt("\tEnter correct choice: ('T' or 'F')");
        if (io.response().equals("T") || io.response().equals("t"))
            correctChoice = true;
        else
            correctChoice = false;
    }

    public void display() {
        io.outputln("True/False:\n\t"+this.getPrompt());
        io.outputln("\t\t(T)/(F)\n");
    }

    public void modify() {
        io.prompt("\tChange correct choice "+correctChoice+" : ('T' or 'F' or nothing to skip)");
        if (io.response().equals("T") || io.response().equals("t"))
            correctChoice = true;
        else if(io.response().equals(""))
            correctChoice = correctChoice; //null on purpose
        else
            correctChoice = false;
    }

    public boolean getCorrectChoice() {
        return correctChoice;
    }
}
