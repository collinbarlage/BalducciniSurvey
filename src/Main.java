public class Main {

    public static void main(String[] args) {

        IO io = new IO();

        String mode = "N/A", fileName;
        boolean error = true;
        Survey newSurvey = new Survey(); //Placeholder for surveys
        Test newTest     = new Test();   //Placeholder for Tests

        io.outputln(" B A L D U C C I N I  S U R V E Y\n----------------------------------\n");
        io.outputln("Enter a corresponding number to access menu items:\n");
        
        while(error) {
            error = false;
            io.prompt("[1]\tSurvey Mode (no grading)\n[2]\tTest Mode   (grading enabled)");

            if (io.response().equals("1"))
                mode = "survey";
            else if (io.response().equals("2"))
                mode = "test";
            else {
                System.out.println(io.response() + " is not a valid option :(");
                error = true;
            }
        }

        error = true;
        while(error) {
            error = false;
            do {
                io.outputln("[1]\tCreate new "+mode+"\n[2]\tDisplay "+mode+"\n[3]\tLoad "+mode);
                io.prompt("[4]\tSave "+mode+"\n[5]\tQuit");

                switch (io.response()) {
                    case "1": //Create new
                        io.prompt("Enter " + mode + " name:");
                        if(mode.equals("survey")) {
                            newSurvey = new Survey(io.response());
                            newSurvey.makeSurvey(); //Prompt creator to add questions
                        }
                        else {
                            newTest = new Test(io.response());
                            newTest.makeSurvey();
                        }
                        break;

                    case "2": //Display
                        if (mode.equals("survey"))
                            newSurvey.display();
                        else
                            newTest.display();
                        break;

                    case "3": //Load
                        //getAllSurveys
                        if (mode.equals("survey")) {
                            fileName = newSurvey.getAllFiles();
                            newSurvey = Survey.load(fileName);
                        }
                        else {
                            fileName = newTest.getAllFiles();
                            newTest = Test.load(fileName);
                        }
                        break;

                    case "4": //Save
                        if (mode.equals("survey"))
                            newSurvey.save();
                        else
                            newTest.save();
                        break;

                    case "5": //Quit
                        System.out.println("See ya ~~\n");
                        System.exit(0);
                        break;

                    default:
                        System.out.println(io.response() + " is not a valid option :(");
                        error = true;
                        break;
                }
            } while (!io.response().equals("5"));
        }
    }

}
