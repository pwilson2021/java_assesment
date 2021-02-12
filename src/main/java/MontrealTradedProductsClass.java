import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MontrealTradedProductsClass implements MontrealTradedProducts {
    public List<Product> products_stats_list = new ArrayList<>();
    public Map<Product, Integer> trades = new HashMap<>();

    @Override
    public void addNewProduct(Product product) throws ProductAlreadyRegisteredException {
        List<Product> foundProducts = new ArrayList<>();
        foundProducts = products_stats_list.stream().filter(prod -> prod.product_ID == product.product_ID).collect(Collectors.toList());

        if (foundProducts.size() > 0) {
            throw new ProductAlreadyRegisteredException("Product already registered");
        } else {
            products_stats_list.add(product);
        }
    }

    @Override
    public void trade(Product product, int quantity) {
        List<Product> foundProducts = new ArrayList<>();
        foundProducts = products_stats_list.stream().filter(prod -> prod.product_ID == product.product_ID).collect(Collectors.toList());

        if (foundProducts.size() > 0) {
            trades.put(product, quantity);
        }
    }

    @Override
    public int totalTradeQuantityForDay() {
        int quantity = 0;
        for (Map.Entry<Product, Integer> entry : trades.entrySet() ) {
            quantity += entry.getValue();
        }
        return quantity;
    }

    @Override
    public double totalValueOfDaysTradedProducts() {
        double totalValue = 0;
        for (Map.Entry<Product, Integer> entry : trades.entrySet() ) {
            double value =  entry.getKey().price("string", "string");
            totalValue += (value * entry.getValue());
        }
        return totalValue;
    }
}
