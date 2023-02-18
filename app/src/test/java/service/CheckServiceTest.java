package service;

import dao.card.CardDaoCollection;
import dao.card.CardDaoFile;
import dao.product.ProductDaoCollection;
import dao.product.ProductDaoFile;
import model.Card;
import model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.Cache;
import utils.CheckGenerator;
import writer.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CheckServiceTest {

    @InjectMocks
    private CheckService checkService = new CheckService();

    @Mock
    private ProductDaoCollection productDaoCollection;
    @Mock
    private ProductDaoFile productDaoFile;
    @Mock
    private CardDaoCollection cardDaoCollection;
    @Mock
    private CardDaoFile cardDaoFile;
    @Mock
    private CheckGenerator checkGenerator;
    @Mock
    private CheckWriterCreator consoleWriter;
    @Mock
    private CheckWriterCreator fileWriter;


    @BeforeAll
    public static void init() {

        Cache.savePair("1-2");
        Cache.savePair("2-6");
        Cache.savePair("4-1");
    }

    @Test
    void checkIsProductFileExist() {

        Cache.saveFile("D:\\Products.docx");
        assertTrue(checkService.productFileExistence());

    }

    @Test
    void checkIsProductFileNotExist() {

        Cache.clearFiles();
        assertFalse(checkService.productFileExistence());
    }

    @Test
    void checkIsCardFileExist() {

        Cache.clearFiles();
        Cache.saveFile("D:\\Cards.txt");
        assertTrue(checkService.cardFileExistence());
    }

    @Test
    void checkIsCardFileNotExist() {

        Cache.clearFiles();
        assertFalse(checkService.cardFileExistence());
    }

    @Test
    void checkGetSelectedProductsFromFile() {

        int expectedSize = 3;
        Mockito.when(productDaoFile.findAll()).thenReturn(getProductList());

        List<Product> selectedProducts = checkService.getSelectedProductsFromFile();

        Mockito.verify(productDaoFile).findAll();
        assertNotNull(selectedProducts);
        assertEquals(expectedSize, selectedProducts.size());
    }

    @Test
    void checkGetSelectedProductsFromCollection() {

        int expectedSize = 3;
        Mockito.when(productDaoCollection.findAll()).thenReturn(getProductList());

        List<Product> selectedProducts = checkService.getSelectedProductsFromCollection();

        Mockito.verify(productDaoCollection).findAll();
        assertNotNull(selectedProducts);
        assertEquals(expectedSize, selectedProducts.size());
    }

    @Test
    void checkGetSelectedProducts() {

        int expectedSize = 3;

        List<Product> selectedProducts = checkService.getSelectedProducts(getProductList());

        assertNotNull(selectedProducts);
        assertEquals(expectedSize, selectedProducts.size());
    }

    @Test
    void checkGetSelectedCardFromFile() {

        Cache.saveCard("card-1");
        Card expectedCard = getCard();
        Mockito.when(cardDaoFile.getCardByDescription(any())).thenReturn(expectedCard);

        Optional<Card> card = checkService.getSelectedCardFromFile();

        Mockito.verify(cardDaoFile).getCardByDescription(any());
        assertNotNull(card);
        assertEquals(expectedCard.getDescription(), card.get().getDescription());
    }

    @Test
    void checkGetSelectedCardFromFileIsEmpty() {

        Cache.clearCards();
        Optional<Card> emptyCard = checkService.getSelectedCardFromFile();
        assertNotNull(emptyCard);
        assertTrue(emptyCard.isEmpty());
    }

    @Test
    void checkIsCardPresented() {

        Cache.saveCard("card-1");
        assertTrue(checkService.isCardPresented());
    }

    @Test
    void checkIsCardNotPresented() {

        Cache.clearCards();
        assertFalse(checkService.isCardPresented());
    }

    @Test
    void checkGetSelectedCardFromCollection() {

        Cache.saveCard("card-1");
        Card expectedCard = getCard();
        Mockito.when(cardDaoCollection.getCardByDescription(any())).thenReturn(expectedCard);

        Optional<Card> card = checkService.getSelectedCardFromCollection();

        Mockito.verify(cardDaoCollection).getCardByDescription(any());
        assertNotNull(card);
        assertEquals(expectedCard.getDescription(), card.get().getDescription());
    }

    @Test
    void checkGetSelectedCardFromCollectionIsEmpty() {

        Cache.clearCards();
        Optional<Card> emptyCard = checkService.getSelectedCardFromCollection();
        assertNotNull(emptyCard);
        assertTrue(emptyCard.isEmpty());
    }


    @Test
    void checkCreateProductQuantityMap() {

        int expectedMapSize = 3;
        int expectedQuantity = 6;
        List<Product> expectedList = getProductList();
        Map<Product, Integer> map = checkService.createProductQuantityMap(expectedList);

        assertNotNull(map);
        assertEquals(expectedMapSize, map.size());
        assertEquals(expectedQuantity, map.get(expectedList.get(1)));
    }

    @Test
    void checkCreateCheck() {

        String someCheck = "Some check";
        Mockito.when(checkGenerator.generateCheck(any(), any())).thenReturn(someCheck);

        checkService.createCheck(any(), any());

        Mockito.verify(checkGenerator).generateCheck(any(), any());
    }

    @Test
    void checkWriteCheck() {

        String someCheck = "Some check";
        Mockito.doNothing().when(consoleWriter).writeCheck(any());
        Mockito.doNothing().when(fileWriter).writeCheck(any());

        checkService.writeCheck(someCheck);

        Mockito.verify(consoleWriter).writeCheck(any());
        Mockito.verify(fileWriter).writeCheck(any());
    }


    private List<Product> getProductList() {

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Book", new BigDecimal("12.1")));
        products.add(new Product(2, "Phone", new BigDecimal("85")));
        products.add(new Product(3, "Computer", new BigDecimal("120.5")));
        products.add(new Product(4, "Toy bear", new BigDecimal("5.3")));
        return products;
    }

    private Card getCard() {
        return new Card(1, "card-1", 5);
    }

}