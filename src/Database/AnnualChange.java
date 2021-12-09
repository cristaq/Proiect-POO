package Database;

import Children.Child;
import Children.Factory;
import Children.UpdateChild;
import Gifts.Gift;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class AnnualChange {
    private Double newSantaBudget;
    private ArrayList<Gift> newGifts = new ArrayList<>();
    private ArrayList<Child> newChildren = new ArrayList<>();
    private ArrayList<UpdateChild> updateChildren = new ArrayList<>();

    public AnnualChange(JsonNode node) {
        newSantaBudget = node.get("newSantaBudget").asDouble();
        for(JsonNode gift : node.get("newGifts")) {
            newGifts.add(new Gift(gift));
        }
        for(JsonNode child : node.get("newChildren")) {
            Child newChild = Factory.createChild(child, child.get("age").asInt());
            if(newChild != null) {
                newChildren.add(newChild);
            }
        }
        for(JsonNode updateChild : node.get("childrenUpdates")) {
            updateChildren.add(new UpdateChild(updateChild));
        }
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    public ArrayList<UpdateChild> getUpdateChildren() {
        return updateChildren;
    }
}
