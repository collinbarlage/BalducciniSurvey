public class Main {

    public static void main(String[] args) {

        IO io = new IO();

        String mode = "N/A", fileName;
        boolean error = true;
        Survey newSurvey = new Survey(); //Placeholder for surveys
        Test newTest     = new Test();   //Placeholder for Tests

        io.outputln("\n B A L D U C C I N I  S U R V E Y\n----------------------------------");
        io.outputln("      made with <3 by Collin  \n");
        io.outputln("Enter a corresponding number to access menu items:\n");

        while(error) {
            error = false;
            io.prompt("[1]\tSurvey Mode (no grading)\n[2]\tTest Mode   (grading enabled)");

            if (io.getInput().equals("1"))
                mode = "survey";
            else if (io.getInput().equals("2"))
                mode = "test";
            else {
                System.out.println(io.getInput() + " is not a valid option :(");
                error = true;
            }
        }

        error = true;
        while(error) {
            error = false;
            do { //display options
                io.outputln("[1]\tCreate new "+mode+"\n[2]\tDisplay "+mode+"\n[3]\tLoad "+mode+"\n"+
                               "[4]\tSave "+mode+"\n[5]\tModify existing "+mode+"\n[6]\tTake a "+mode+"\n"+
                               "[7]\tTabulate a "+mode);
                if (mode.equals("test"))
                    io.outputln("[8]\tGrade a test");
                io.prompt("[0]\tQuit");

                switch (io.getInput()) {
                    case "1": //Create new
                        io.prompt("Enter " + mode + " name:");
                        if(mode.equals("survey")) {
                            newSurvey = new Survey(io.getInput());
                            newSurvey.makeSurvey(); //Prompt creator to add questions
                        }
                        else {
                            newTest = new Test(io.getInput());
                            newTest.makeSurvey();
                        }
                        break;

                    case "2": //Display
                        if(newSurvey.name == null  &&  mode.equals("survey")){ //load a survey before hand
                            fileName = newSurvey.getAllFiles();
                            newSurvey = Survey.load(fileName);
                        }
                        if(newTest.name == null  &&  mode.equals("test")){ //load a test before hand
                            fileName = newTest.getAllFiles();
                            newTest = Test.load(fileName);
                        }
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


                    case "5": //Modify
                        if(newSurvey.name == null  &&  mode.equals("survey")){ //load a survey before hand
                            fileName = newSurvey.getAllFiles();
                            newSurvey = Survey.load(fileName);
                        }
                        if(newTest.name == null  &&  mode.equals("test")){ //load a test before hand
                            fileName = newTest.getAllFiles();
                            newTest = Test.load(fileName);
                        }
                        if (mode.equals("survey"))
                            newSurvey.edit();
                        else
                            newTest.edit();
                        break;

                    case "6": //Take
                        if (mode.equals("survey"))
                            newSurvey.take();
                        else
                            newTest.take();
                        break;

                    case "7": //Tabulate

                        break;

                    case "8": //Grade
                        if(mode.equals("test")) {

                        }
                        else {
                            System.out.println(io.getInput() + " is not a valid option :'(");
                            error = true;
                        }
                        break;

                    case "0": //Quit
                        System.out.println("See ya!\n");
                        System.exit(0);
                        break;

                    default:
                        System.out.println(io.getInput() + " is not a valid option :(");
                        error = true;
                        break;
                }
            } while (!io.getInput().equals("0"));
        }
    }


}
