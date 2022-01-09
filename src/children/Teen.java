package children;

import com.fasterxml.jackson.databind.JsonNode;

public final class Teen extends Child {
    public Teen(final JsonNode node) {
        super(node);
    }

    @Override
    public double calculateNiceScore() {
        double num1 = 0;
        for (int i = 0; i < niceScoreHistory.size(); i++) {
            num1 += niceScoreHistory.get(i) * (i + 1);
        }
        double num2 = (niceScoreHistory.size() * (niceScoreHistory.size() + 1)) / 2.0;
        return  num1 / num2;
    }

    /**
     * Constructor used when a kid grows up to be a teen.
     * @param kid
     */
    public Teen(final Child kid) {
        id = kid.id;
        lastName = kid.lastName;
        firstName = kid.firstName;
        age = kid.age;
        city = kid.city;
        niceScoreHistory.addAll(kid.niceScoreHistory);
        giftsPreferences.addAll(kid.giftsPreferences);
        averageScore = calculateNiceScore();
    }
}
