package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtility {

    public static boolean isArgsContainsPairs(String[] args) {

        String expression = getWholeExpressionFromArgs(args);
        Pattern pattern = Pattern.compile("(\\d+\\-{1}\\d+)+(card\\-{1}\\d+)?");
        Matcher matcher = pattern.matcher(expression);
        if (!matcher.matches()) return false;
        return true;
    }

    private static String getWholeExpressionFromArgs(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg);
        }
        return stringBuilder.toString();
    }
}
