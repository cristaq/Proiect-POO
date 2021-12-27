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

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
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
