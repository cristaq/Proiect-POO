package Children;

import com.fasterxml.jackson.databind.JsonNode;

public class Teen extends Child{
    public Teen(JsonNode node) {
        super(node);
    }

    @Override
    public String what() {
        return "teen";
    }

    @Override
    public Double calculateNiceScore() {
        double num1 = 0;
        for(int i = 0; i < niceScoreHistory.size(); i++) {
            num1 += niceScoreHistory.get(i) * (i + 1);
        }
        double num2 = (niceScoreHistory.size() * (niceScoreHistory.size() + 1)) / 2.0;
        return  num1 / num2;
    }

    public Teen(Child kid) {
        id = kid.id;
        lastName = kid.lastName;
        firstName = kid.firstName;
        age = kid.age;
        city = kid.city;
        niceScoreHistory.addAll(kid.niceScoreHistory);
        giftPreference.addAll(kid.giftPreference);
    }
}
