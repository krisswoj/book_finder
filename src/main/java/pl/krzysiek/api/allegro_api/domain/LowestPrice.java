
package pl.krzysiek.api.allegro_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LowestPrice {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
