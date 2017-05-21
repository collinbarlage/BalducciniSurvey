import java.io.*;
import java.util.Vector;

public class Survey implements java.io.Serializable {
    IO io = new IO();
    protected String name;
    private Vector<Question> questions = new Vector<Question>();
    protected boolean isSurvey;
    String extention, type;

    public Survey() {
        isSurvey = true;
        extention = ".srv";
        type = "Survey";
        //Default for loading
    }

    public Survey(String surveyName) {
        isSurvey = true;
        extention = ".srv";
        type = "Survey";
        name = surveyName;
    }

    public void makeSurvey() {
        while (true) {
            io.prompt("Add a question:\n[1]\tTrue/False\n[2]\tMultiple Choice\n[3]\tShort Answer" +
                    "\n[4]\tEssay\n[5]\tRanking\n[6]\tMatching\n[7]\tDone");

            switch (io.getInput()) {
                case "1": //TrueFalse
                    TrueFalse newTf = new TrueFalse(isSurvey);
                    this.addQuestion(newTf);
                    break;

                case "2": //Multiple Choice
                    MultipleChoice newMc = new MultipleChoice(isSurvey); //Prompt user for question
                    this.addQuestion(newMc); //Add this question to test/survey
                    break;

                case "3": //Short Answer
                    ShortAnswer newSa = new ShortAnswer(isSurvey);
                    this.addQuestion(newSa);
                    break;

                case "4": //Essay
                    Essay newE = new Essay();
                    this.addQuestion(newE);
                    break;

                case "5": //Ranking
                    Ranking newR = new Ranking(isSurvey);
                    this.addQuestion(newR);
                    break;

                case "6": //Matching
                    Matching newM = new Matching(isSurvey);
                    this.addQuestion(newM);
                    break;

                case "7":
                    //exit
                    break;

                default:
                    break;

            }
            if (io.getInput().equals("7"))
                break;
        }
    }

    public void take() {
        io.prompt("Enter your name:");
        String userName = io.getInput();
        Response res = new Response(userName, name);
        io.outputln("\n\n----------------------------------------------\n" + type + " " + this.getName() + "\n");
        for (int i = 0; i < questions.size(); i++) {
            questions.elementAt(i).display();
            questions.elementAt(i).take(res);
        }
        io.outputln("Saving response...");
        try {
            FileOutputStream file = new FileOutputStream("responses/" + userName + "_" + name + extention + "r");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(res);
            out.close();
            file.close();
            io.outputln("Saved " + type + " response " + " in /responses/" + userName + "_" + name + extention + "r");
        } catch (IOException i) {
            i.printStackTrace();
        }
        io.outputln("Thanks " + userName + ". You did a great job\n");
    }

    public void edit() {
        this.display();
        while (true) {
            io.prompt("Enter question number to edit a question. Enter nothing to continue:");
            if (io.getInput().equals(""))
                break;
            else if (!io.getInput().matches("^-?\\d+$"))
                io.outputln("Enter a number, silly");
            else if (io.getNumber() < questions.size() && io.getNumber() >= 0) {
                io.output("Noice. You selected ");
                questions.elementAt(io.getNumber()).display();
                questions.elementAt(io.getNumber()).edit();
            } else
                io.outputln("That's not a question :(");
        }
    }

    public void display() {
        io.outputln("\n\n----------------------------------------------");
        io.outputln("\n\n" + type + " " + this.getName() + "\n");
        for (int i = 0; i < questions.size(); i++) {
            io.output("(" + i + ")\t");
            questions.elementAt(i).display();
        }
    }

    public void save() {
        try {
            FileOutputStream file = new FileOutputStream("saves/" + name + extention);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
            io.outputln("Saved " + type + " " + name + " in /saves/" + name + extention);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Survey load(String fileName) {
        IO io = new IO();
        io.outputln("Loading...");
        Survey loadSurvey = null;
        try {
            FileInputStream fileIn = new FileInputStream("saves/" + fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadSurvey = (Survey) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            io.outputln("File not found :(");
            c.printStackTrace();
            return null;
        }
        io.outputln("Survey " + loadSurvey.getName() + " Loaded!");
        return loadSurvey;
    }

    public String getAllFiles() {
        File saves = new File("saves/");
        File[] allFiles = saves.listFiles();
        Vector<Integer> availableFiles = new Vector<Integer>();

        io.outputln("Select a " + type + " to load:");
        for (int i = 0; i < allFiles.length; i++) {
            if (allFiles[i].getName().substring(allFiles[i].getName().length() - 4).equals(extention)) {
                io.outputln("[" + i + "]\t" + allFiles[i].getName());
                availableFiles.add(i);
            }
        }
        while (true) {
            io.prompt();
            if (!io.getInput().matches("^-?\\d+$"))
                io.outputln("Enter a number, silly");
            else if (!availableFiles.contains(io.getNumber()))
                io.outputln("File not found :(\nEnter a number from the list above");
            else
                break;
        }
        return allFiles[io.getNumber()].getName(); //return name of file
    }


    public void addQuestion(Question q) {
        questions.add(q);
        io.outputln("Question added\n");
    }

    public Question getQuestion(int i) {
        return questions.elementAt(i);
    }

    public String getName() {
        return name;
    }


}
