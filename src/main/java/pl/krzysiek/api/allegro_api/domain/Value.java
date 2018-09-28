
package pl.krzysiek.api.allegro_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("selected")
    @Expose
    private Boolean selected;
    @SerializedName("idSuffix")
    @Expose
    private String idSuffix;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getIdSuffix() {
        return idSuffix;
    }

    public void setIdSuffix(String idSuffix) {
        this.idSuffix = idSuffix;
    }

}
