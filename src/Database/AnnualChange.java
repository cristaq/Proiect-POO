package Database;

import Children.Child;
import Gifts.Gift;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class AnnualChange {
    Double newSantaBudget;
    ArrayList<Gift> newGifts = new ArrayList<>();
    ArrayList<Child> newChildren = new ArrayList<>();

    public AnnualChange(JsonNode node) {
        newSantaBudget = node.get("newSantaBudget").asDouble();
        for(JsonNode gift : node.get("newGifts")) {
            newGifts.add(new Gift(gift));
        }
        for(JsonNode child : node.get("newChildren")) {
            newChildren.add(new Child(child));
        }
    }

    @Override
    public String toString() {
        return "AnnualChange{" +
                "newSantaBudget=" + newSantaBudget +
                ", newGifts=" + newGifts +
                ", newChildren=" + newChildren +
                '}';
    }
}
