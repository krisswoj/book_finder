package pl.krzysiek.api.google_books_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetailPrice_ {

    @SerializedName("amountInMicros")
    @Expose
    private Float amountInMicros;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    /**
     * No args constructor for use in serialization
     */
    public RetailPrice_() {
    }

    /**
     * @param amountInMicros
     * @param currencyCode
     */
    public RetailPrice_(Float amountInMicros, String currencyCode) {
        super();
        this.amountInMicros = amountInMicros;
        this.currencyCode = currencyCode;
    }

    public Float getAmountInMicros() {
        return amountInMicros;
    }

    public void setAmountInMicros(Float amountInMicros) {
        this.amountInMicros = amountInMicros;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}