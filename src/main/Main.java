package main;

import Children.Child;
import Database.AnnualChange;
import Database.ChildrenDatabase;
import Database.Database;
import Database.GiftDatabase;
import Gifts.Gift;
import checker.Checker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException, ParseException {
        File directory = new File("tests");
        ObjectMapper objectMapper = new ObjectMapper();

        for(File file : Objects.requireNonNull(directory.listFiles())) {
            JsonNode jsonNode = objectMapper.readTree
                    (new File(file.getAbsolutePath()).getAbsoluteFile());
            list(jsonNode, file.getAbsolutePath());
        }


        //Checker.calculateScore();
    }

    public static void list(JsonNode root, String filePath1) throws IOException {
        ChildrenDatabase cdb = new ChildrenDatabase();
        cdb.initChildren(root.get("initialData").get("children"));

        GiftDatabase gdb = new GiftDatabase();
        gdb.initGifts(root.get("initialData").get("santaGiftsList"));

        double initialBudget = root.get("santaBudget").asDouble();
        Database db = new Database(initialBudget, cdb, gdb);
        //print for year 0
        System.out.println(filePath1);
        for(Child child : cdb.getChildren().values()) {
            System.out.println(child.getId() + " " + child.getLastName() + " " + child.what() + " " + child.getAssignedBudget());
        }
        System.out.println();

        for(JsonNode change : root.get("annualChanges")) {
            AnnualChange annualChange = new AnnualChange(change);

            //process changes
            db.update(annualChange);
            for(Child child : cdb.getChildren().values()) {
                System.out.println(child.getId() + " " + child.getLastName() + " " + child.what() + " " + child.getAssignedBudget());
            }
            System.out.println();

            //print for year i
        }
    }
}
