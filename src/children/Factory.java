package children;

import com.fasterxml.jackson.databind.JsonNode;

public interface Factory {
    /**
     * Creates a certain child depending on age.
     * @param node a JsonNode containing all the information
     *             in Child class
     * @param age the age of the child
     */
     static Child createChild(JsonNode node, int age) {
        if (age < 5) {
            return new Baby(node);
        } else if (age < 12) {
            return new Kid(node);
        } else if (age < 19) {
            return new Teen(node);
        } else {
            return null;
        }
    }

    /**
     * Increments the age of a Child.
     * If the age meets a certain threshold, babies become kids,
     * and kids become teens.
     */
     static Child growUp(Child child) {
        child.growUp();
        int age = child.getAge();
        if (age == 5) {
            return new Kid(child);
        } else if (age == 12) {
            return new Teen(child);
        } else {
            return child;
        }
    }
}
