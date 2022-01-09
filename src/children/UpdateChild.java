package children;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * This class has all the information necessary to update
 * a child entry in the database
 */
public final class UpdateChild {
    private int id;
    private Double niceScore;
    private ArrayList<String> giftPreference = new ArrayList<>();

    public UpdateChild(final JsonNode node) {
        id = node.get("id").asInt();
        if (node.get("niceScore").asText().compareTo("null") == 0) {
            niceScore = -1.0;
        } else {
            niceScore = node.get("niceScore").asDouble();
        }
        for (JsonNode gift : node.get("giftsPreferences")) {
            if (gift != null) {
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
}
