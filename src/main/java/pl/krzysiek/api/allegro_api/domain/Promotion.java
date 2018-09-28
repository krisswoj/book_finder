
package pl.krzysiek.api.allegro_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promotion {

    @SerializedName("emphasized")
    @Expose
    private Boolean emphasized;
    @SerializedName("bold")
    @Expose
    private Boolean bold;
    @SerializedName("highlight")
    @Expose
    private Boolean highlight;

    public Boolean getEmphasized() {
        return emphasized;
    }

    public void setEmphasized(Boolean emphasized) {
        this.emphasized = emphasized;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

}
