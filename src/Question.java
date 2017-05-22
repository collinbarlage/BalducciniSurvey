import java.util.Vector;

public abstract class Question implements java.io.Serializable {
    private String prompt;
    protected Vector<String> correctChoices = new Vector<String>();

    IO io = new IO();


    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String toLetter(int i) {
        return String.valueOf((char) (i + 65));
    }

    public int fromLetter(String s) {
        char c = s.charAt(0);
        return (int)c - 65;
    }

    public void edit() {
        io.output("Modifying ");
        this.display();
        io.prompt("Enter new prompt: (enter nothing to skip)");
        if (!io.getInput().equals("")) {
            prompt = io.getInput();
            io.outputln("Prompt set");
        }
        this.modify();
    }

    public void take(Response res) {
        QuestionResponse userResponse = new QuestionResponse();
        for (int i = 0; i < correctChoices.size(); i++) {
            io.prompt();
            userResponse.add(io.getInput());
        }
        res.addResponse(userResponse);
    }

    public Boolean isCorrect(Vector<String> user) {
        for(int i=0; i<user.size(); i++) {
            if(!correctChoices.contains(user.elementAt(i)))
                return false;
        }
        return (user.size() == correctChoices.size());
    }

    public void modify() {
        //
    }

    public void display() {
        //
    }

    public void tabulate(Vector<Response> responses, int q) {
        //
    }




}
