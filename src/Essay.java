
public class Essay extends Question implements java.io.Serializable {

    public Essay() {
        io.prompt("\tEnter the essay prompt:");
        this.setPrompt(io.getInput());

    }

    public void display() {
        io.outputln("Essay:\n\t"+this.getPrompt()+"\n");
    }

}


