package Children;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public abstract class Child {
    protected int id;
    protected String lastName;
    protected String firstName;
    protected String city;
    protected int age;
    protected ArrayList<String> giftPreference = new ArrayList<>();
    protected double averageScore;
    protected ArrayList<Double> niceScoreHistory = new ArrayList<>();
    protected double assignedBudget;

    public Child(){}

    public Child(JsonNode node) {
        id = node.get("id").asInt();
        lastName = node.get("lastName").asText();
        firstName = node.get("firstName").asText();
        age = node.get("age").asInt();
        city = node.get("city").asText();
        niceScoreHistory.add(node.get("niceScore").asDouble());
        for(JsonNode gift : node.get("giftsPreferences")) {
            giftPreference.add(gift.asText());
        }
        averageScore = calculateNiceScore();
    }

    public void growUp() {
        age++;
    }

    public int getId() {
        return id;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public ArrayList<String> getGiftPreference() {
        return giftPreference;
    }

    public void update(UpdateChild updateChild) {
        if(updateChild.niceScore != -1) {
            niceScoreHistory.add(updateChild.niceScore);
        }
        for(String gift : updateChild.getGiftPreference()) {
            giftPreference.remove(gift);
            giftPreference.add(0, gift);
        }
        averageScore = calculateNiceScore();

    }

    public abstract String what();
    public abstract Double calculateNiceScore();

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", niceScore=" + niceScoreHistory +
                ", giftPreference=" + giftPreference +
                '}';
    }
}
