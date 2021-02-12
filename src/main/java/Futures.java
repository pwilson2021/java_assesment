public class Futures extends Product {
    protected String exchange;
    protected String contract_code;
    protected int contract_month;
    protected int contract_year;

    public Futures(String exchange, String contractCode, int month, int year, String product_id, double price) {
        this.exchange = exchange;
        this.contract_code = contractCode;
        this.contract_month = month;
        this.contract_year = year;
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
