package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    //private static final String SPACE = " ";
    private static final String NEW_LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        writeReport(fromFileName, toFileName);
    }

    private String readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFile, e);
        }
    }

    private String getReport(String fromFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] dataArray = readFile(fromFileName).split(" ");
        for (String data : dataArray) {
            int index = data.indexOf(",");
            String string = data.substring(0,index);
            int dataSum = Integer.parseInt(data.substring(index + 1));
            if (string.equals(SUPPLY)) {
                sumSupply = sumSupply + dataSum;
            } else {
                sumBuy = sumBuy + dataSum;
            }
        }
        int result = sumSupply - sumBuy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(sumSupply).append(NEW_LINE)
                .append(BUY).append(COMMA).append(sumBuy).append(NEW_LINE)
                .append(RESULT).append(COMMA).append(result);
        return builder.toString();
    }

    private void writeReport(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(getReport(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file" + toFileName, e);
        }
    }
}
