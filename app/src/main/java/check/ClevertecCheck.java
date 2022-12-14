package check;


import utils.CheckUtility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClevertecCheck {

    private static final String WRONG_MESSAGE = "Something wrong wih arguments. Use itemId-quantity pairs. Example: 3-1 2-5 5-1\n" +
            "You can also add a discount card, example: 3-1 2-5 5-1 card-1234.\n" +
            "If you want to process pairs through a file, then write the path to the file. Example: D:\\Items\\Items1.txt D:\\Items\\Items2.txt\n" +
            "File must match <filename>.<extension>";

    public static void main(String[] args) {

        if (isArgsContainsFiles(args)) {
            executeDataFromFiles(args);
        } else {
            if (isArgsContainsPairs(args)) {
                executeDataFromPairs(args);
            } else {
                System.out.println(WRONG_MESSAGE);
            }
        }
    }

    private static boolean isArgsContainsFiles(String[] args) {

        Pattern pattern = Pattern.compile("([a-zA-Z0-9.:]+\\\\{1})*(\\w)+(\\.{1}\\w+)");
        for (String arg : args) {
            Matcher matcher = pattern.matcher(arg);
            if (!matcher.matches()) return false;
        }
        return true;
    }

    private static boolean isArgsContainsPairs(String[] args) {

        return CheckUtility.isArgsContainsPairs(args);
    }

    private static void executeDataFromFiles(String[] args) {


        System.out.println("executing data from files");
    }

    private static void executeDataFromPairs(String[] args) {


        System.out.println("executing data from pairs");
    }


}
