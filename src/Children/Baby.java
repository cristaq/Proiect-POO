package Children;

import com.fasterxml.jackson.databind.JsonNode;

public class Baby extends Child{
    public Baby(JsonNode node) {
        super(node);
    }

    @Override
    public String what() {
        return "baby";
    }

    @Override
    public Double calculateNiceScore() {
        return 10.0;
    }
}
