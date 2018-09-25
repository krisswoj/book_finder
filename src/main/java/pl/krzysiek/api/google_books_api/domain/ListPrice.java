package pl.krzysiek.api.google_books_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPrice {

    @SerializedName("amount")
    @Expose
    private Float amount;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    /**
     * No args constructor for use in serialization
     */
    public ListPrice() {
    }

    /**
     * @param amount
     * @param currencyCode
     */
    public ListPrice(Float amount, String currencyCode) {
        super();
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}