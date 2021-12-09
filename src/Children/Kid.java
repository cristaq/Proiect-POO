package Children;

import com.fasterxml.jackson.databind.JsonNode;

public class Kid extends Child{

    public Kid(JsonNode node) {
        super(node);
    }

    @Override
    public String what() {
        return "kid";
    }

    @Override
    public Double calculateNiceScore() {
        Double finalScore = 0.0;
        for(Double score : niceScoreHistory) {
            finalScore += score;
        }
        return finalScore / niceScoreHistory.size();
    }

    public Kid(Child baby) {
        id = baby.id;
        lastName = baby.lastName;
        firstName = baby.firstName;
        age = baby.age;
        city = baby.city;
        niceScoreHistory.addAll(baby.niceScoreHistory);
        giftPreference.addAll(baby.giftPreference);
    }
}
