package database;

import children.Child;
import children.Factory;
import children.UpdateChild;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Collection;
import java.util.TreeMap;

/**
 * This class contains all children in a TreeMap, ordered by ID.
 */
public final class ChildrenDatabase {
    private TreeMap<Integer, Child> children = new TreeMap<>();

    /**
     * Creates all children in the initial data.
     * @param arrayNode a JsonArray with children data
     */
    public void initChildren(final JsonNode arrayNode) {
        for (JsonNode node : arrayNode) {
            Child child = Factory.createChild(node, node.get("age").asInt());
            if (child != null) {
                children.put(child.getId(), child);
            }
        }
    }

    /**
     * Updates the children entries. The method will remove all children above 18,
     * will apply growUp method from factory and will add the new children, while
     * updating the old entries.
     * @param annualChange
     */
    public void update(final AnnualChange annualChange) {
        children.entrySet().removeIf(entry -> (entry.getValue().getAge() == 18));
        for (int key : children.keySet()) {
            Child update = Factory.growUp(children.get(key));
            if (update != children.get(key)) {
                children.put(key, update);
            }
        }
        for (Child child : annualChange.getNewChildren()) {
            children.put(child.getId(), child);
        }

        for (UpdateChild updateChild : annualChange.getUpdateChildren()) {
            Child forUpdate = children.get(updateChild.getId());
            if (forUpdate != null) {
                forUpdate.update(updateChild);
            }
        }
    }
    @JsonIgnore
    public TreeMap<Integer, Child> getChildren() {
        return children;
    }
    @JsonProperty("children")
    public Collection<Child> getJ() {
        return children.values();
    }
}
