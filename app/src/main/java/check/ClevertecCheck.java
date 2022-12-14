package check;


import service.CheckService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClevertecCheck {

    private static CheckService checkService = new CheckService();

    private static final String PAIR_PATTERN = "(\\d+\\-{1}\\d+)";
    private static final String CARD_PATTERN = "(card\\-{1}\\d+)";
    private static final String FILE_PATTERN = "([a-zA-Z0-9.:]+\\\\{1})*(\\w)+(\\.{1}\\w+)";

    private static final String NO_ARGS = "Application requires input arguments";
    private static final String WRONG_ARGS = "Something wrong wih arguments. Be sure to use itemId-amount pairs. Example: 3-1 2-5 5-1\n" +
            "You can also add a discount card, example: 3-1 2-5 5-1 card-1234.\n" +
            "If you want to process pairs through a file, then write the path to the file. Example: D:\\Items\\Items1.txt D:\\Items\\Items2.txt\n" +
            "File must match <filename>.<extension>";

    private static boolean isPairPresented = false;
    private static boolean isFilePresented = false;
    private static boolean isCardPresented = false;

    public static void main(String[] args) {

        if (!isArgsPresented(args)) {
            System.out.println(NO_ARGS);
        } else {
            if (isArgsCorrect(args) && isPairPresented) {
                executeData(args, isFilePresented, isCardPresented);
            } else {
                System.out.println(WRONG_ARGS);
            }
        }
    }

    private static boolean isArgsPresented(String[] args) {
        return args.length == 0 ? false : true;
    }

    private static boolean isArgsCorrect(String[] args) {

        Pattern pairPattern = Pattern.compile(PAIR_PATTERN);
        Pattern cardPattern = Pattern.compile(CARD_PATTERN);
        Pattern filePattern = Pattern.compile(FILE_PATTERN);
        for (String arg : args) {
            Matcher pairMatcher = pairPattern.matcher(arg);
            Matcher cardMatcher = cardPattern.matcher(arg);
            Matcher fileMatcher = filePattern.matcher(arg);

            if (pairMatcher.matches()) isPairPresented = true;
            if (fileMatcher.matches()) isFilePresented = true;
            if (cardMatcher.matches()) isCardPresented = true;
            if (!pairMatcher.matches() && !cardMatcher.matches() && !fileMatcher.matches()) return false;
        }

        return true;
    }

    private static void executeData(String[] args, boolean fileExistence, boolean cardExistence) {

        checkService.executeData(args, fileExistence, cardExistence);
    }



}
