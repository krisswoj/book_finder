
package pl.krzysiek.api.allegro_api.domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("promoted")
    @Expose
    private List<Object> promoted = null;
    @SerializedName("regular")
    @Expose
    private List<Regular> regular = null;

    public List<Object> getPromoted() {
        return promoted;
    }

    public void setPromoted(List<Object> promoted) {
        this.promoted = promoted;
    }

    public List<Regular> getRegular() {
        return regular;
    }

    public void setRegular(List<Regular> regular) {
        this.regular = regular;
    }

}
