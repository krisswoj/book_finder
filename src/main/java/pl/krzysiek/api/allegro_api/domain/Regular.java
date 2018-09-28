
package pl.krzysiek.api.allegro_api.domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Regular {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("seller")
    @Expose
    private Seller seller;
    @SerializedName("promotion")
    @Expose
    private Promotion promotion;
    @SerializedName("delivery")
    @Expose
    private Delivery delivery;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("sellingMode")
    @Expose
    private SellingMode sellingMode;
    @SerializedName("stock")
    @Expose
    private Stock stock;
    @SerializedName("category")
    @Expose
    private Category category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public SellingMode getSellingMode() {
        return sellingMode;
    }

    public void setSellingMode(SellingMode sellingMode) {
        this.sellingMode = sellingMode;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
