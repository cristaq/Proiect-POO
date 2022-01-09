package children;

import com.fasterxml.jackson.databind.JsonNode;

public final class Kid extends Child {

    public Kid(final JsonNode node) {
        super(node);
    }

    @Override
    public double calculateNiceScore() {
        double finalScore = 0.0;
        for (double score : niceScoreHistory) {
            finalScore += score;
        }
        return finalScore / niceScoreHistory.size();
    }

    /**
     * Constructor used when a baby grows up to be a kid.
     * @param baby
     */
    public Kid(final Child baby) {
        id = baby.id;
        lastName = baby.lastName;
        firstName = baby.firstName;
        age = baby.age;
        city = baby.city;
        niceScoreHistory.addAll(baby.niceScoreHistory);
        giftsPreferences.addAll(baby.giftsPreferences);
        averageScore = calculateNiceScore();
    }
}
