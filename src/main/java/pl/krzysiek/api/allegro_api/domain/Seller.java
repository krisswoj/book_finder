
package pl.krzysiek.api.allegro_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seller {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("company")
    @Expose
    private Boolean company;
    @SerializedName("superSeller")
    @Expose
    private Boolean superSeller;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCompany() {
        return company;
    }

    public void setCompany(Boolean company) {
        this.company = company;
    }

    public Boolean getSuperSeller() {
        return superSeller;
    }

    public void setSuperSeller(Boolean superSeller) {
        this.superSeller = superSeller;
    }

}
