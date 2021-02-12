public class Stocks extends Product {
    protected String stock_ticker;
    protected String exchange;

    public Stocks(String stock_ticker, String exchange, String product_id, double price)  {
        this.stock_ticker = stock_ticker;
        this.exchange = exchange;
        this.product_ID = product_id;
        this.current_price = price;
    }

    @Override
    public double price(String exchange, String ticker) {
        return 0;
    }

    @Override
    public double price(String exchange, String contractCode, int month, int year) {
        return 0;
    }
}
