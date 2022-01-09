package database;

import children.Child;
import children.Factory;
import children.UpdateChild;
import gifts.Gift;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * This class has all the information necessary to update
 * the database.
 */
public final class AnnualChange {
    private Double newSantaBudget;
    private ArrayList<Gift> newGifts = new ArrayList<>();
    private ArrayList<Child> newChildren = new ArrayList<>();
    private ArrayList<UpdateChild> updateChildren = new ArrayList<>();

    public AnnualChange(final JsonNode node) {
        newSantaBudget = node.get("newSantaBudget").asDouble();
        for (JsonNode gift : node.get("newGifts")) {
            if (gift != null) {
                newGifts.add(new Gift(gift));
            }
        }
        for (JsonNode child : node.get("newChildren")) {
            if (child != null) {
                Child newChild = Factory.createChild(child, child.get("age").asInt());
                if (newChild != null) {
                    newChildren.add(newChild);
                }
            }
        }
        for (JsonNode updateChild : node.get("childrenUpdates")) {
            if (updateChild != null) {
                updateChildren.add(new UpdateChild(updateChild));
            }
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
