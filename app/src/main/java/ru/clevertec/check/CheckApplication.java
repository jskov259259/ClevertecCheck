package ru.clevertec.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.clevertec.check.service.CheckService;
import ru.clevertec.check.utils.Cache;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.clevertec.check.utils.Pattern.*;

@SpringBootApplication
public class CheckApplication {

	private CheckService checkService = new CheckService();

	private static final String NO_ARGS = "Application requires input arguments";
	private static final String WRONG_ARGS = "Something wrong wih arguments. Be sure to use itemId-amount pairs. Example: 3-1 2-5 5-1\n" +
			"You can also add a discount card, example: 3-1 2-5 5-1 card-1234.\n" +
			"If you want to process pairs through a file, then write the path to the file. Example: D:\\Products\\Products.txt D:\\Cards\\Card.txt\n" +
			"File must match Products.<extension> or Card.<extension>";

	private static boolean isPairsPresented = false;

	public static void main(String[] args) {
		CheckApplication clevertecCheck = new CheckApplication();
		clevertecCheck.run(args);
		SpringApplication.run(CheckApplication.class, args);
	}

	private void run(String[] args) {

		if (!isArgsPresented(args)) {
			System.out.println(NO_ARGS);
		} else {
			if (isArgsCorrectAndSaveCache(args) && isPairsPresented) {
				executeData();
			} else {
				System.out.println(WRONG_ARGS);
			}
		}
	}

	boolean isArgsPresented(String[] args) {
		return args.length == 0 ? false : true;
	}

	boolean isArgsCorrectAndSaveCache(String[] args) {

		Pattern pairPattern = Pattern.compile(PAIR_PATTERN.getPattern());
		Pattern cardPattern = Pattern.compile(CARD_PATTERN.getPattern());
		Pattern filePattern = Pattern.compile(FILE_PATTERN.getPattern());
		for (String arg : args) {
			Matcher pairMatcher = pairPattern.matcher(arg);
			Matcher cardMatcher = cardPattern.matcher(arg);
			Matcher fileMatcher = filePattern.matcher(arg);

			if (pairMatcher.matches()) {
				isPairsPresented = true;
				Cache.savePair(arg);
			}
			if (fileMatcher.matches()) {
				Cache.saveFile(arg);
			}
			if (cardMatcher.matches()) {
				Cache.saveCard(arg);
			}

			if (!pairMatcher.matches() && !cardMatcher.matches() && !fileMatcher.matches()) return false;
			if (Cache.getCards().size() > 1) return false;
		}

		return true;
	}

	void executeData() {
		checkService.executeData();
	}

	public static boolean isPairsPresented() {
		return isPairsPresented;
	}

}
