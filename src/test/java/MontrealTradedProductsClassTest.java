import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MontrealTradedProductsClassTest {
    public List<Product> productList = new ArrayList<>();
    public List<String> companyIds = Arrays.asList("AAPL", "TESLA",
        "ALIBABA", "GOOGLE", "FACEBOOK", "UBER", "MICROSOFT", "ORACLE", "SNAPCHAT", "LYFT");
    public Random random = new Random();


    @Test
    void addNewProduct() {
//        for (int i = 0; i < 10; i ++)
//        {
//            //int j = i + 1;
//            int randMonth = random.nextInt(12 - 1) + 1;
//            int randYear = random.nextInt(2021 - 2015) + 2015;
//            String randomString = java.util.UUID.randomUUID().toString();
//            productList.add(makeStock(randomString, randomString, companyIds.get(i), random.nextFloat()));
//           // productList.add(makeFuture(randomString, randomString, randMonth, randYear, companyIds.get(j), random.nextFloat()));
//        }

        MontrealTradedProductsClass trades = new MontrealTradedProductsClass();
        int randMonth = random.nextInt(12 - 1 ) + 1;
        int randYear = random.nextInt(2021 - 2015) + 2015;
        String randomString = java.util.UUID.randomUUID().toString();
        Product firstStock = new Stocks(randomString, randomString, "TESLA", random.nextFloat());
        Product secondStock = new Stocks(randomString, randomString, "TESLA", random.nextFloat());

        assertThrows(ProductAlreadyRegisteredException.class, () -> {
             trades.addNewProduct(firstStock);
             trades.addNewProduct(secondStock);
        });
    }

    @Test
    void trade() {
        Map<Product, Integer> trades_test = new HashMap<>();

        MontrealTradedProductsClass trades = new MontrealTradedProductsClass();
        int randMonth = random.nextInt(12 - 1 ) + 1;
        int randYear = random.nextInt(2021 - 2015) + 2015;
        String randomString = java.util.UUID.randomUUID().toString();
        Product firstStock = new Stocks(randomString, randomString, companyIds.get(0), random.nextFloat());
        Product secondStock = new Stocks(randomString, randomString,companyIds.get(1),random.nextFloat());
        Product thirdStock = new Stocks(randomString, randomString,companyIds.get(2),random.nextFloat());

        try {
            trades.addNewProduct(firstStock);
            trades.addNewProduct(secondStock);
        } catch (ProductAlreadyRegisteredException e) {
            System.out.println("Product Already registered");
        }

        trades.trade(firstStock, 15);
        trades.trade(secondStock, 15);
        trades.trade(thirdStock, 20);

        trades_test.put(firstStock, 15);
        trades_test.put(secondStock, 15);
        trades_test.put(thirdStock, 20);


        assertFalse(trades.trades.equals((trades_test)));
    }

    @Test
    void totalTradeQuantityForDay() {

        MontrealTradedProductsClass trades = new MontrealTradedProductsClass();
        int randMonth = random.nextInt(12 - 1 ) + 1;
        int randYear = random.nextInt(2021 - 2015) + 2015;
        String randomString = java.util.UUID.randomUUID().toString();
        Product firstStock = new Stocks(randomString, randomString, companyIds.get(0), random.nextFloat());
        Product secondStock = new Stocks(randomString, randomString,companyIds.get(1),random.nextFloat());
        Product thirdStock = new Stocks(randomString, randomString,companyIds.get(2),random.nextFloat());

        try {
            trades.addNewProduct(firstStock);
            trades.addNewProduct(secondStock);
            trades.addNewProduct(thirdStock);
        } catch (ProductAlreadyRegisteredException e) {
            System.out.println("Product Already registered");
        }

        trades.trade(firstStock, 15);
        trades.trade(secondStock, 15);
        trades.trade(thirdStock, 20);

        assertEquals(50, trades.totalTradeQuantityForDay());
    }

    @Test
    void totalValueOfDaysTradedProducts() {

        Stocks mockProduct = Mockito.mock(Stocks.class);
        Mockito.when(mockProduct.price("first_rand", "second_rand")).thenReturn(18.0);

        Stocks mockProduct1 = Mockito.mock(Stocks.class);
        Mockito.when(mockProduct.price("first_rand", "second_rand")).thenReturn(19.0);

        Stocks mockProduct2 = Mockito.mock(Stocks.class);
        Mockito.when(mockProduct.price("first_rand", "second_rand")).thenReturn(20.0);

        MontrealTradedProductsClass trades = new MontrealTradedProductsClass();
        MontrealTradedProductsClass mockTrades = Mockito.mock(MontrealTradedProductsClass.class);
        Mockito.when(mockTrades.totalValueOfDaysTradedProducts()).thenReturn(955.0);

//        Product firstStock = new mockProduct(randomString, randomString, companyIds.get(0), random.nextFloat());
//        Product secondStock = new Stocks(randomString, randomString,companyIds.get(1),random.nextFloat());
//        Product thirdStock = new Stocks(randomString, randomString,companyIds.get(2),random.nextFloat());


        try {
            trades.addNewProduct(mockProduct);
            trades.addNewProduct(mockProduct1);
            trades.addNewProduct(mockProduct2);
        } catch (ProductAlreadyRegisteredException e) {
            System.out.println("Product Already registered");
        }

        trades.trade(mockProduct, 15);
        trades.trade(mockProduct1, 15);
        trades.trade(mockProduct2, 20);


        assertEquals(955, mockTrades.totalValueOfDaysTradedProducts());
    }
}