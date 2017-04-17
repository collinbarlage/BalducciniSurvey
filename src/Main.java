import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        IO io = new IO();

        String mode = "N/A";
        boolean error = true;

        System.out.println(" B A L D U C C I N I  S U R V E Y\n----------------------------------\n");
        System.out.println("Enter a corresponding number to access menu items:\n");
        
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
            io.outputln("[1]\tCreate new "+mode+"\n[2]\tDisplay "+mode+"\n[3]\tLoad "+mode);
            io.prompt("[4]\tSave "+mode+"\n[5]\tQuit");

            switch (io.response()) {
                case "1": //Create new
                    io.prompt("Enter "+mode+" name:");
                    Test newTest = new Test(io.response());
                    newTest.makeTest(); //Prompt creator to add questions
                    //Display newly created survey and SAVE
                    newTest.display();
                    newTest.save();

                    if (mode.equals("survey"))
                        newTest.typeSurvey();
                    break;

                case "2": //Display
                    if (mode.equals("survey")) {
                        System.out.println("wat");
                    }
                    break;

                case "3": //Load
                    //getAllTests/Surveys
                    Test loadTest = Test.load("x.tst");
                    loadTest.display();
                    break;

                case "4": //Save
                    if (mode.equals("survey")) {
                        System.out.println("wat");
                    }
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
        }
    }
    public void getAllFiles() {

    }

}
