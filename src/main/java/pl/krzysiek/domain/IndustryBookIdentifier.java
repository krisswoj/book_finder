package pl.krzysiek.domain;

public class IndustryBookIdentifier {

    private String type;
    private String identifier;

    public IndustryBookIdentifier(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }
}
