package children;

import com.fasterxml.jackson.databind.JsonNode;

public final class Baby extends Child {
    public Baby(final JsonNode node) {
        super(node);
    }

    @Override
    public double calculateNiceScore() {
        return 10.0;
    }
}
