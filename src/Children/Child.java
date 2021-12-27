package Children;

import Database.GiftDatabase;
import Gifts.Gift;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public abstract class Child {
    protected int id;
    protected String lastName;
    protected String firstName;
    protected String city;
    protected int age;
    protected ArrayList<String> giftsPreferences = new ArrayList<>();
    protected double averageScore;
    protected ArrayList<Double> niceScoreHistory = new ArrayList<>();
    protected double assignedBudget;
    protected ArrayList<Gift> receivedGifts = new ArrayList<>();

    public Child(){}

    public Child(JsonNode node) {
        id = node.get("id").asInt();
        lastName = node.get("lastName").asText();
        firstName = node.get("firstName").asText();
        age = node.get("age").asInt();
        city = node.get("city").asText();
        niceScoreHistory.add(node.get("niceScore").asDouble());
        for(JsonNode gift : node.get("giftsPreferences")) {
            giftsPreferences.add(gift.asText());
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

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public String getCity() {
        return city;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void update(UpdateChild updateChild) {
        if(updateChild.niceScore != -1) {
            niceScoreHistory.add(updateChild.niceScore);
        }
        for(int i = updateChild.getGiftPreference().size() - 1; i >=0; i--) {
            giftsPreferences.remove(updateChild.getGiftPreference().get(i));
            giftsPreferences.add(0, updateChild.getGiftPreference().get(i));
        }
        averageScore = calculateNiceScore();

    }

    public void receiveGifts(GiftDatabase giftDatabase) {
        double copy = assignedBudget;
        receivedGifts.clear();
        for(String category : giftsPreferences) {
            Gift pending = null;
            double lowestPrice = Double.MAX_VALUE;
            for(Gift gift : giftDatabase.getGifts()) {
                if(category.equals(gift.getCategory())
                        && lowestPrice > gift.getPrice()
                        && assignedBudget > gift.getPrice()) {
                    lowestPrice = gift.getPrice();
                    pending = gift;
                }
            }
            if(pending != null) {
                receivedGifts.add(pending);
                assignedBudget -= pending.getPrice();
            }
        }
        assignedBudget = copy;
    }

    public abstract String what();
    public abstract double calculateNiceScore();

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
                ", giftPreference=" + giftsPreferences +
                '}';
    }
}
