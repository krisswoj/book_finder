package pl.krzysiek.domain;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private String authors;
    private String publishedDate;
    private String description;
    private String isbn10;
    private String isbn13;
    private String pictureLink;
    private List<CompareBookPrices> compareBookPrices;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public List<CompareBookPrices> getCompareBookPrices() {
        return compareBookPrices;
    }

    public void setCompareBookPrices(List<CompareBookPrices> compareBookPrices) {
        this.compareBookPrices = compareBookPrices;
    }
}
