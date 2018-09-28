
package pl.krzysiek.api.allegro_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delivery {

    @SerializedName("availableForFree")
    @Expose
    private Boolean availableForFree;
    @SerializedName("lowestPrice")
    @Expose
    private LowestPrice lowestPrice;

    public Boolean getAvailableForFree() {
        return availableForFree;
    }

    public void setAvailableForFree(Boolean availableForFree) {
        this.availableForFree = availableForFree;
    }

    public LowestPrice getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(LowestPrice lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

}
