
package pl.krzysiek.api.allegro_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stock {

    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("available")
    @Expose
    private Integer available;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

}
