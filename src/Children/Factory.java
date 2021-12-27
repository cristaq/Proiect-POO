package Children;

import com.fasterxml.jackson.databind.JsonNode;

public class Factory {
    public static Child createChild(JsonNode node, int age) {
        if(age < 5) {
            return new Baby(node);
        } else if (age < 12) {
            return new Kid(node);
        } else if (age < 19) {
            return new Teen(node);
        } else {
            return null;
        }
    }

    public static Child growUp(Child child) {
        child.growUp();
        int age = child.getAge();
        if(age == 5) {
            return new Kid(child);
        } else if (age == 12) {
            return new Teen(child);
        } else {
            return child;
        }
    }
}
