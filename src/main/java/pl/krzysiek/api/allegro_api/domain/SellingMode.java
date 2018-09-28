
package pl.krzysiek.api.allegro_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellingMode {

    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("popularity")
    @Expose
    private Integer popularity;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

}
