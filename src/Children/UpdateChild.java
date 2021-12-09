package Children;

import Gifts.Gift;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class UpdateChild {
    int id;
    Double niceScore;
    ArrayList<String> giftPreference = new ArrayList<>();

    public UpdateChild(JsonNode node) {
        id = node.get("id").asInt();
        if(node.get("niceScore").asText().compareTo("null") == 0) {
            niceScore = -1.0;
        } else {
            niceScore = node.get("niceScore").asDouble();
        }
        for(JsonNode gift : node.get("giftsPreferences")) {
            if(gift != null) {
                giftPreference.add(gift.asText());
            }
        }
    }

    public int getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftPreference() {
        return giftPreference;
    }

    @Override
    public String toString() {
        return "UpdateChild{" +
                "id=" + id +
                ", niceScore=" + niceScore +
                ", giftPreference=" + giftPreference +
                '}';
    }
}
