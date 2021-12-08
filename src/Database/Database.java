package Database;

public class Database {
    private ChildrenDatabase childrenDatabase;
    private GiftDatabase giftDatabase;

    public Database(ChildrenDatabase childrenDatabase, GiftDatabase giftDatabase) {
        this.childrenDatabase = childrenDatabase;
        this.giftDatabase = giftDatabase;
    }
}
