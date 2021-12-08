package Gifts;

import com.fasterxml.jackson.databind.JsonNode;

public class Gift {
    String productName;
    Double price;
    String category;

    public Gift(JsonNode node) {
        productName = node.get("productName").asText();
        price = node.get("price").asDouble();
        category = node.get("category").asText();
    }

    @Override
    public String toString() {
        return "Gift{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
