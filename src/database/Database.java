package database;
import children.Child;

/**
 * Class that contains databases with children and gifts.
 * Also keeps track of budgets.
 */
public final class Database {
    private double santaBudget;
    private double unitBudget;
    private ChildrenDatabase childrenDatabase;
    private GiftDatabase giftDatabase;

    public Database(final double santaBudget, final ChildrenDatabase childrenDatabase,
                    final GiftDatabase giftDatabase) {
        this.santaBudget = santaBudget;
        this.childrenDatabase = childrenDatabase;
        this.giftDatabase = giftDatabase;
        calculateUnitBudget();
        allocBudget();
        giveGifts();
    }

    /**
     * Calls all methods that update databases.
     * Calculates new budget.
     */
    public void update(final AnnualChange annualChange) {
        santaBudget = annualChange.getNewSantaBudget();
        childrenDatabase.update(annualChange);
        giftDatabase.update(annualChange);
        calculateUnitBudget();
        allocBudget();
        giveGifts();
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

    public void giveGifts() {
        for (Child child : childrenDatabase.getChildren().values()) {
            child.receiveGifts(giftDatabase);
        }
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public double getUnitBudget() {
        return unitBudget;
    }
}
