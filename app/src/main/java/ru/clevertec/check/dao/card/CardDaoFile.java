package ru.clevertec.check.dao.card;

import ru.clevertec.check.exceptions.IncorrectValuesNumber;
import ru.clevertec.check.model.Card;
import ru.clevertec.check.utils.Cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class CardDaoFile implements CardDao {

    private static final String INCORRECT_VALUES_NUMBER = "Incorrect values number in card file.\n" +
            "File must contain: cardId, description, discount. Values must be separated by commas.\n" +
            "The check will be provided without a discount card.";

    @Override
    public Card getCardByDescription(String description) {

        String path = getPathFromCache();
        Card card = null;

        try {
            card = findCardFromFile(path, description);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (IncorrectValuesNumber e) {
            System.out.println(e.getMessage());
        }
        return card;
    }


     Card findCardFromFile(String path, String description) throws IOException {

        Card card = null;
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
            if (descriptionMatches(line, description)) {
               card = createCard(line);
            }
        }
        reader.close();
        return card;
    }

     boolean descriptionMatches(String line, String description) {

        String[] variables = line.split(",");
        checkCorrectValuesNumber(variables);
        if (variables[1].trim().equals(description)) {
            return true;
        }
        return false;
    }

    void checkCorrectValuesNumber(String[] variables) {

        if (variables.length != 3) {
            throw new IncorrectValuesNumber(INCORRECT_VALUES_NUMBER);
        }
    }

     Card createCard(String line) {

        String[] variables = line.split(",");
        Card card = new Card();
        card.setId(Integer.parseInt(variables[0].trim()));
        card.setDescription(variables[1].trim());
        card.setDiscount(Integer.parseInt(variables[2].trim()));
        return card;
    }


     String getPathFromCache() {

        Optional<String> optionalPath = Cache.getFiles().stream().filter(file -> file.contains("Cards")).findFirst();
        return optionalPath.orElse("Cards.txt");
    }
}
