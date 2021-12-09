package Database;

import Children.Child;
import Children.Factory;
import Children.UpdateChild;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.TreeMap;

public class ChildrenDatabase {
    private TreeMap<Integer, Child> children = new TreeMap<>();

    public void initChildren(JsonNode arrayNode) {
        for(JsonNode node : arrayNode) {
            Child child = Factory.createChild(node, node.get("age").asInt());
            if(child != null) {
                children.put(child.getId(), child);
            }
        }
    }

    public void update(AnnualChange annualChange) {
        children.entrySet().removeIf(entry -> (entry.getValue().getAge() == 18));
        for (int key : children.keySet()) {
            Child update = Factory.growUp(children.get(key));
            if(update != children.get(key)) {
                children.put(key, update);
            }
        }

        for(Child child : annualChange.getNewChildren()) {
            children.put(child.getId(), child);
        }

        for(UpdateChild updateChild : annualChange.getUpdateChildren()) {
            Child forUpdate = children.get(updateChild.getId());
            if(forUpdate != null) {
                forUpdate.update(updateChild);
            }
        }
    }
    public TreeMap<Integer, Child> getChildren() {
        return children;
    }
}
