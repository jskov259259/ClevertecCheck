package service;

import dao.card.CardDaoCollection;
import dao.card.CardDaoFile;;
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
    void testProductFileExistence() {

        Cache.saveFile("D:\\Items.docx");
        assertFalse(checkService.productFileExistence());

        Cache.saveFile("D:\\Products.docx");
        assertTrue(checkService.productFileExistence());

    }

    @Test
    void testCardFileExistence() {

        Cache.saveFile("D:\\File.txt");
        assertFalse(checkService.cardFileExistence());

        Cache.saveFile("D:\\Cards.txt");
        assertTrue(checkService.cardFileExistence());
    }

    @Test
    void testGetSelectedProductsFromFile() {

        Mockito.when(productDaoFile.findAll()).thenReturn(getProductList());
        List<Product> selectedProducts = checkService.getSelectedProductsFromFile();
        Mockito.verify(productDaoFile).findAll();
        assertNotNull(selectedProducts);
        assertEquals(selectedProducts.size(), 3);
    }

    @Test
    void testGetSelectedProductsFromCollection() {

        Mockito.when(productDaoCollection.findAll()).thenReturn(getProductList());
        List<Product> selectedProducts = checkService.getSelectedProductsFromCollection();
        Mockito.verify(productDaoCollection).findAll();
        assertNotNull(selectedProducts);
        assertEquals(selectedProducts.size(), 3);
    }

    @Test
    void testGetSelectedProducts() {

        List<Product> selectedProducts = checkService.getSelectedProducts(getProductList());
        assertNotNull(selectedProducts);
        assertEquals(selectedProducts.size(), 3);
    }

    @Test
    void testGetSelectedCardFromFile() {

        Cache.clearCards();
        Optional<Card> emptyCard = checkService.getSelectedCardFromFile();
        assertNotNull(emptyCard);
        assertTrue(emptyCard.isEmpty());

        Cache.saveCard("card-1");
        Mockito.when(cardDaoFile.getCardByDescription(any())).thenReturn(getCard());
        Optional<Card> card = checkService.getSelectedCardFromFile();
        Mockito.verify(cardDaoFile).getCardByDescription(any());
        assertNotNull(card);
        assertEquals(getCard().getDescription(), card.get().getDescription());
    }

    @Test
    void testIsCardPresented() {

        assertFalse(checkService.isCardPresented());
        Cache.saveCard("card-1");
        assertTrue(checkService.isCardPresented());
    }

    @Test
    void testGetSelectedCardFromCollection() {

        Cache.clearCards();
        Optional<Card> emptyCard = checkService.getSelectedCardFromCollection();
        assertNotNull(emptyCard);
        assertTrue(emptyCard.isEmpty());

        Cache.saveCard("card-1");
        Mockito.when(cardDaoCollection.getCardByDescription(any())).thenReturn(getCard());
        Optional<Card> card = checkService.getSelectedCardFromCollection();
        Mockito.verify(cardDaoCollection).getCardByDescription(any());
        assertNotNull(card);
        assertEquals(getCard().getDescription(), card.get().getDescription());
    }

    @Test
    void testCreateProductQuantityMap() {

        Map<Product, Integer> map = checkService.createProductQuantityMap(getProductList());
        assertNotNull(map);
        assertEquals(map.size(), 3);
        assertEquals(map.get(getProductList().get(1)), 6);
    }

    @Test
    void testCreateCheck() {

        Mockito.when(checkGenerator.generateCheck(any(), any())).thenReturn("Some check");
        checkService.createCheck(any(), any());
        Mockito.verify(checkGenerator).generateCheck(any(), any());
    }

    @Test
    void testWriteCheck() {

        Mockito.doNothing().when(consoleWriter).writeCheck(any());
        Mockito.doNothing().when(fileWriter).writeCheck(any());
        checkService.writeCheck("Some check");
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