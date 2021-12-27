package main;

import Database.AnnualChange;
import Database.ChildrenDatabase;
import Database.Database;
import Database.GiftDatabase;
import checker.Checker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import common.Constants;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

        /*File file = new File("tests/test22.json");
        JsonNode jsonNode = objectMapper.readTree
                (new File(file.getAbsolutePath()).getAbsoluteFile());
        list(jsonNode, file.getAbsolutePath());

         */

        for(File file : Objects.requireNonNull(directory.listFiles())) {
            list(file);
        }

        Checker.calculateScore();
    }

    public static void list(File file) throws IOException {
        ObjectMapper map = new ObjectMapper();
        JsonNode root = map.readTree
                (new File(file.getAbsolutePath()).getAbsoluteFile());
        int numberOfYears = root.get("numberOfYears").asInt();

        ChildrenDatabase cdb = new ChildrenDatabase();
        cdb.initChildren(root.get("initialData").get("children"));

        GiftDatabase gdb = new GiftDatabase();
        gdb.initGifts(root.get("initialData").get("santaGiftsList"));

        double initialBudget = root.get("santaBudget").asDouble();
        Database db = new Database(initialBudget, cdb, gdb);

        /*System.out.println(filePath1);
        for(Child child : cdb.getChildren().values()) {
            System.out.println(child.getId() + " " + child.getLastName() + " " + child.getAssignedBudget() + " " + child.getReceivedGifts());
        }
        System.out.println();

         */

        ObjectNode newNode = map.createObjectNode();
        newNode.putArray("annualChildren");
        ((ArrayNode) newNode.get("annualChildren")).add(map.valueToTree(cdb));
        for(JsonNode change : root.get("annualChanges")) {
            AnnualChange annualChange = new AnnualChange(change);

            db.update(annualChange);

            ((ArrayNode) newNode.get("annualChildren")).add(map.valueToTree(cdb));

            /*for(Child child : cdb.getChildren().values()) {
                System.out.println(child.getId() + " " + child.getLastName() + " " + child.getAge() + " " + child.getNiceScoreHistory() + " " + child.getAssignedBudget() + " " + child.getReceivedGifts());
            }
            System.out.println();

             */
            numberOfYears--;
            if(numberOfYears == 0) {
                break;
            }
        }
        String numberOfTask = "";
        int i = file.getAbsolutePath().indexOf("test");
        for(; i < file.getAbsolutePath().length(); i++) {
            char c = file.getAbsolutePath().charAt(i);
            if(Character.isDigit(c)) {
                numberOfTask += c;
            }
        }
        String out = Constants.OUTPUT_PATH + numberOfTask + Constants.FILE_EXTENSION;
        BufferedWriter writer = new BufferedWriter(new FileWriter(out));
        writer.write(newNode.toPrettyString());
        writer.close();
    }
}
