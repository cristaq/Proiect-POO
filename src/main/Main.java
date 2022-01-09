package main;

import database.AnnualChange;
import database.ChildrenDatabase;
import database.Database;
import database.GiftDatabase;
import checker.Checker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import common.Constants;

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
    public static void main(final String[] args) throws IOException {
        File directory = new File("tests");
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            StringBuilder numberOfTask = new StringBuilder();
            for (int i = 0; i < file.getName().length(); i++) {
                if (Character.isDigit(file.getName().charAt(i))) {
                    numberOfTask.append(file.getName().charAt(i));
                }
            }
            String outputFile = Constants.OUTPUT_PATH + numberOfTask.toString()
                    + Constants.FILE_EXTENSION;
            list(file, outputFile);
        }

        Checker.calculateScore();
    }

    public static void list(final File file,
                            final String outputFile) throws IOException {
        ObjectMapper map = new ObjectMapper();
        JsonNode root = map.readTree(new File(file.getAbsolutePath()).getAbsoluteFile());
        int numberOfYears = root.get("numberOfYears").asInt();

        ChildrenDatabase cdb = new ChildrenDatabase();
        cdb.initChildren(root.get("initialData").get("children"));

        GiftDatabase gdb = new GiftDatabase();
        gdb.initGifts(root.get("initialData").get("santaGiftsList"));

        double initialBudget = root.get("santaBudget").asDouble();
        Database db = new Database(initialBudget, cdb, gdb);

        ObjectNode newNode = map.createObjectNode();
        newNode.putArray("annualChildren");
        ((ArrayNode) newNode.get("annualChildren")).add(map.valueToTree(cdb));

        for (JsonNode change : root.get("annualChanges")) {
            AnnualChange annualChange = new AnnualChange(change);

            db.update(annualChange);

            ((ArrayNode) newNode.get("annualChildren")).add(map.valueToTree(cdb));

            numberOfYears--;
            if (numberOfYears == 0) {
                break;
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(newNode.toPrettyString());
        writer.close();
    }
}
