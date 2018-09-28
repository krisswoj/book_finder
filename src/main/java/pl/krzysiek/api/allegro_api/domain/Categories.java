
package pl.krzysiek.api.allegro_api.domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories {

    @SerializedName("subcategories")
    @Expose
    private List<Subcategory> subcategories = null;
    @SerializedName("path")
    @Expose
    private List<Path> path = null;

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public List<Path> getPath() {
        return path;
    }

    public void setPath(List<Path> path) {
        this.path = path;
    }

}
