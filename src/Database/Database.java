package Database;

import Children.Child;

import java.util.Map;

public class Database {
    private double santaBudget;
    private double unitBudget;
    private ChildrenDatabase childrenDatabase;
    private GiftDatabase giftDatabase;

    public Database(double santaBudget, ChildrenDatabase childrenDatabase,
                    GiftDatabase giftDatabase) {
        this.santaBudget = santaBudget;
        this.childrenDatabase = childrenDatabase;
        this.giftDatabase = giftDatabase;
        calculateUnitBudget();
        allocBudget();
    }

    public void update(AnnualChange annualChange) {
        santaBudget = annualChange.getNewSantaBudget();
        childrenDatabase.update(annualChange);
        giftDatabase.update(annualChange);
        calculateUnitBudget();
        allocBudget();
    }

    public void calculateUnitBudget() {
        double sum = 0.0;
        for (Child child : childrenDatabase.getChildren().values()) {
            sum += child.getAverageScore();
        }
        unitBudget = santaBudget / sum;
    }

    public void allocBudget() {
        for (Child child : childrenDatabase.getChildren().values()) {
            child.setAssignedBudget(child.getAverageScore() * unitBudget);
        }
    }
}
