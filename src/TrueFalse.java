
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
        io.outputln("\tTrue/False:\n\t"+this.getPrompt());
        io.outputln("\t\t(T)/(F)\n");
    }

    public boolean getCorrectChoice() {
        return correctChoice;
    }
}
