
package pl.krzysiek.api.allegro_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchMeta {

    @SerializedName("availableCount")
    @Expose
    private Integer availableCount;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("fallback")
    @Expose
    private Boolean fallback;

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getFallback() {
        return fallback;
    }

    public void setFallback(Boolean fallback) {
        this.fallback = fallback;
    }

}
