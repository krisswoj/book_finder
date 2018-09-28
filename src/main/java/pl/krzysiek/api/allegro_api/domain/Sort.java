
package pl.krzysiek.api.allegro_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sort {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("selected")
    @Expose
    private Boolean selected;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
