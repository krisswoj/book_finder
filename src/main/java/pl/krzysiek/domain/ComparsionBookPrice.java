package pl.krzysiek.domain;

public class ComparsionBookPrice {

    private int id;
    private Book book;
    private String storeName;
    private String directLink;
    private String currency;
    private double price;
    private double shippingCost;
    private double additionalCosts;
    private boolean availability;

    public ComparsionBookPrice(Book book, String storeName, String directLink, String currency, double price, double shippingCost, double additionalCosts, boolean availability) {
        this.book = book;
        this.storeName = storeName;
        this.directLink = directLink;
        this.currency = currency;
        this.price = price;
        this.shippingCost = shippingCost;
        this.additionalCosts = additionalCosts;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getDirectLink() {
        return directLink;
    }

    public String getCurrency() {
        return currency;
    }

    public double getPrice() {
        return price;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public double getAdditionalCosts() {
        return additionalCosts;
    }

    public boolean isAvailability() {
        return availability;
    }
}
