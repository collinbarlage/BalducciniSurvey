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
        System.out.println("DEFAULT TAKE METHOD!");
        for (int i = 0; i < correctChoices.size(); i++) {
            io.prompt();
            res.addResponse(io.getInput());
        }
    }

    public void modify() {
        //
    }

    public void display() {
        //
    }


}
