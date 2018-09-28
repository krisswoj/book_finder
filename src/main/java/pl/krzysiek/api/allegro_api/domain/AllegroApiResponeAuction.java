
package pl.krzysiek.api.allegro_api.domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllegroApiResponeAuction {

    @SerializedName("items")
    @Expose
    private Items items;
    @SerializedName("searchMeta")
    @Expose
    private SearchMeta searchMeta;
    @SerializedName("categories")
    @Expose
    private Categories categories;
    @SerializedName("filters")
    @Expose
    private List<Filter> filters = null;
    @SerializedName("sort")
    @Expose
    private List<Sort> sort = null;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public SearchMeta getSearchMeta() {
        return searchMeta;
    }

    public void setSearchMeta(SearchMeta searchMeta) {
        this.searchMeta = searchMeta;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Sort> getSort() {
        return sort;
    }

    public void setSort(List<Sort> sort) {
        this.sort = sort;
    }

}
