import java.io.File;
import java.util.Scanner;


public class RealtimeProgASM1 {

    private static final String[] KEYWORDS = {"Issue","Problem","Solved","Fix","Fixed","No","Done"};//keywords to get the solved issues file name.

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("=== Java Directory Analysis System ===");

        File folder = null;

        while (true) { // looping until the user input correct path
            System.out.println("Please enter or paste the full path of the directory:");
            String pathInput = scan.nextLine(); //get the input from user.

            if (pathInput.isEmpty()) { //check the input is empty or not.
                System.out.println("Error: The path cannot be empty.");//error handling
                continue; //if true go back to the loop.
            }

            folder = new File(pathInput);

            if (!folder.exists() || !folder.isDirectory()) { // check if it is a directory or folder that exists.
                System.out.println("Error: Invalid input, please ensure it is an existing directory in your laptop.");//error handling
            } else {
                break; // if it is valid then end the loop.
            }
        }

        analyzeDirectory(folder);// start analysis the folder

        scan.close();
    }

    private static void analyzeDirectory(File folder) {
        int JavaFileCounter = 0;
        int IssuesSolvedCounter = 0;

        File[] files = folder.listFiles(); // get all files in the folder.

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) { // make sure it is a file but not subfolder.
                    String fileName = file.getName();

                    if (fileName.toLowerCase().endsWith(".java")) { // Check it is a .java file or not
                        JavaFileCounter++; // if true the file counter will add 1

                        if (isSolvedIssues(fileName)) { // call the method and send parameter
                            IssuesSolvedCounter++; // if true the issues counter will add 1
                        }
                    }
                }
            }
        }

        System.out.println("\n--- Analysis Results ---");
        System.out.println("Number of Java Files    = " + JavaFileCounter);
        System.out.println("Number of Issues Solved = " + IssuesSolvedCounter); //print all the output after analyze the folder.
    }

    private static boolean isSolvedIssues(String name) { // the method that check if a file name have keyword
        String FileName = name.toLowerCase();// makes the file name to lower case
        for (String keyword : KEYWORDS) {
            if (FileName.contains(keyword.toLowerCase())) { //makes the keywords to lower case and check if the filename have keywords.
                return true;
            }
        }
        return false;
    }
}