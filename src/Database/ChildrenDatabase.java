package Database;

import Children.Child;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;

public class ChildrenDatabase {
    private ArrayList<Child> children = new ArrayList<>();

    public void initChildren(JsonNode arrayNode) {
        for(JsonNode node : arrayNode) {
            children.add(new Child(node));
        }
    }

    public ArrayList<Child> getChildren() {
        return children;
    }
}
