package Children;

import Database.ChildrenDatabase;

import java.util.ArrayList;

public class ArrayChildren {
    public ArrayList<Child> children;

    public ArrayChildren(ChildrenDatabase cdb) {
        children = new ArrayList<>(cdb.getChildren().values());
    }
}
