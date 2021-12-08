package Children;

import com.fasterxml.jackson.databind.JsonNode;
import enums.Cities;

import java.util.ArrayList;
import java.util.Locale;

public class Child {
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private String city;
    private ArrayList<Double> niceScore = new ArrayList<>();
    private ArrayList<String> giftPreference = new ArrayList<>();

    public Child(JsonNode node) {
        id = node.get("id").asInt();
        lastName = node.get("lastName").asText();
        firstName = node.get("firstName").asText();
        age = node.get("age").asInt();
        city = node.get("city").asText();
        niceScore.add(node.get("niceScore").asDouble());
        for(JsonNode gift : node.get("giftsPreferences")) {
            giftPreference.add(gift.asText());
        }
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", niceScore=" + niceScore +
                ", giftPreference=" + giftPreference +
                '}';
    }
}
