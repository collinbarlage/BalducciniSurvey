public abstract class Question implements java.io.Serializable {
    private String prompt;
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

    public void modify() {
        //
    }

    public void display() {
        //
    }


}
