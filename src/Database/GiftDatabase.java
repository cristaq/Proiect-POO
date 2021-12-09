package Database;

import Gifts.Gift;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class GiftDatabase {
    private ArrayList<Gift> gifts = new ArrayList<>();
    public void initGifts(JsonNode arrayNode) {
        for(JsonNode node : arrayNode) {
            gifts.add(new Gift(node));
        }
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void update(AnnualChange annualChange) {
        gifts.addAll(annualChange.getNewGifts());
    }
}
