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
        return String.valueOf((char)(i + 65));
    }

    public void display() {
        //
    }


}
