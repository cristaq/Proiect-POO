package children;

import com.fasterxml.jackson.annotation.JsonIgnore;
import database.GiftDatabase;
import gifts.Gift;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * Abstract class implemented by baby, kid and teen.
 * Has all the necessary information for a child that will be
 * added to the database.
 */
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

    public Child() {

    }

    public Child(final JsonNode node) {
        id = node.get("id").asInt();
        lastName = node.get("lastName").asText();
        firstName = node.get("firstName").asText();
        age = node.get("age").asInt();
        city = node.get("city").asText();
        niceScoreHistory.add(node.get("niceScore").asDouble());
        for (JsonNode gift : node.get("giftsPreferences")) {
            giftsPreferences.add(gift.asText());
        }
        averageScore = calculateNiceScore();
    }

    public final void growUp() {
        age++;
    }

    /**
     * Method that updates fields of a child: niceScoreHistory,
     * giftsPreferences and average score
     * @param updateChild class that contains updates
     */
    public final void update(final UpdateChild updateChild) {
        if (updateChild.getNiceScore() != -1) {
            niceScoreHistory.add(updateChild.getNiceScore());
        }
        for (int i = updateChild.getGiftPreference().size() - 1; i >= 0; i--) {
            giftsPreferences.remove(updateChild.getGiftPreference().get(i));
            giftsPreferences.add(0, updateChild.getGiftPreference().get(i));
        }
        averageScore = calculateNiceScore();

    }

    /**
     * Assigns gifts to a child depending on their niceScore,
     * available gifts and allocated budget
     */
    public final void receiveGifts(final GiftDatabase giftDatabase) {
        double copy = assignedBudget;
        receivedGifts.clear();
        for (String category : giftsPreferences) {
            Gift pending = null;
            double lowestPrice = Double.MAX_VALUE;
            for (Gift gift : giftDatabase.getGifts()) {
                if (category.equals(gift.getCategory())
                        && lowestPrice > gift.getPrice()
                        && assignedBudget > gift.getPrice()) {
                    lowestPrice = gift.getPrice();
                    pending = gift;
                }
            }
            if (pending != null) {
                receivedGifts.add(pending);
                assignedBudget -= pending.getPrice();
            }
        }
        assignedBudget = copy;
    }

    /**
     * Implemented in Baby, Kid and Teen as strategies.
     * Depending on the type of child, the nice score
     * is calculated differently.
     * @return nice score
     */
    public abstract double calculateNiceScore();

    public final int getId() {
        return id;
    }

    public final double getAverageScore() {
        return averageScore;
    }

    public final double getAssignedBudget() {
        return assignedBudget;
    }

    public final void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public final String getLastName() {
        return lastName;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public final ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public final String getCity() {
        return city;
    }

    public final ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public final int getAge() {
        return age;
    }
}
