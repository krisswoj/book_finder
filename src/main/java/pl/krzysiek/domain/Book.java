package pl.krzysiek.domain;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private String authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private int pageAmount;
    private String pictureLink;
    private List<IndustryBookIdentifier> industryBookIdentifiers;
    private List<ComparsionBookPrice> comparsionBookPrices;

    public Book(String title, String authors, String publisher, String publishedDate, String description, int pageAmount, String pictureLink, List<IndustryBookIdentifier> industryBookIdentifiers) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageAmount = pageAmount;
        this.pictureLink = pictureLink;
        this.industryBookIdentifiers = industryBookIdentifiers;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPageAmount() {
        return pageAmount;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public List<IndustryBookIdentifier> getIndustryBookIdentifiers() {
        return industryBookIdentifiers;
    }

    public List<ComparsionBookPrice> getComparsionBookPrices() {
        return comparsionBookPrices;
    }

    public void setComparsionBookPrices(List<ComparsionBookPrice> comparsionBookPrices) {
        this.comparsionBookPrices = comparsionBookPrices;
    }
}
