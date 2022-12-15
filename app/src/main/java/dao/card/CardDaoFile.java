package dao.card;

import model.Card;
import utils.Cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class CardDaoFile implements CardDao {


    @Override
    public Card getCardByDescription(String description) {

        String path = getPathFromCache();
        Card card = null;

        try {
            card = findCardFromFile(path, description);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return card;
    }


    private Card findCardFromFile(String path, String description) throws IOException {

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

    private boolean descriptionMatches(String line, String description) {

        String[] variables = line.split(",");
        if (variables[1].trim().equals(description)) {
            return true;
        }
        return false;
    }

    private Card createCard(String line) {

        String[] variables = line.split(",");
        Card card = new Card();
        card.setId(Integer.parseInt(variables[0].trim()));
        card.setDescription(variables[1].trim());
        card.setDiscount(Integer.parseInt(variables[2].trim()));
        return card;
    }


    private String getPathFromCache() {
        Optional<String> optionalPath = Cache.getFiles().stream().filter(file -> file.contains("Cards")).findFirst();
        return optionalPath.orElse("Cards.txt");
    }
}
