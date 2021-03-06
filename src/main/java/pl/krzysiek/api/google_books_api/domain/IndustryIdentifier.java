package pl.krzysiek.api.google_books_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Optional;

public class IndustryIdentifier {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("identifier")
    @Expose
    private String identifier;

    /**
     * No args constructor for use in serialization
     */
    public IndustryIdentifier() {
    }

    /**
     * @param type
     * @param identifier
     */
    public IndustryIdentifier(String type, String identifier) {
        super();
        this.type = type;
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}