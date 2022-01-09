package database;

import gifts.Gift;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public final class GiftDatabase {
    private ArrayList<Gift> gifts = new ArrayList<>();
    public void initGifts(final JsonNode arrayNode) {
        for (JsonNode node : arrayNode) {
            gifts.add(new Gift(node));
        }
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void update(final AnnualChange annualChange) {
        gifts.addAll(annualChange.getNewGifts());
    }
}
