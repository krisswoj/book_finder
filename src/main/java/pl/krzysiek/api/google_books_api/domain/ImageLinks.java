package pl.krzysiek.api.google_books_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Optional;

public class ImageLinks {

    @SerializedName("smallThumbnail")
    @Expose
    private String smallThumbnail;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    /**
     * No args constructor for use in serialization
     */
    public ImageLinks() {
    }

    /**
     * @param thumbnail
     * @param smallThumbnail
     */
    public ImageLinks(String smallThumbnail, String thumbnail) {
        super();
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
    }

    public Optional getSmallThumbnail() {
        return Optional.ofNullable(smallThumbnail);
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}