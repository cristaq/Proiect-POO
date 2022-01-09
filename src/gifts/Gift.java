package gifts;

import com.fasterxml.jackson.databind.JsonNode;

public final class Gift {
    private String productName;
    private Double price;
    private String category;

    public Gift(final JsonNode node) {
        productName = node.get("productName").asText();
        price = node.get("price").asDouble();
        category = node.get("category").asText();
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
