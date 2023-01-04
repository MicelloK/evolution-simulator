package agh.oop.proj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class StatisticsWriter {
    private static String SETTINGS_FILE;
    private static final String CSV_SPLIT_BY = ",";

    public void save(Statistics statistics) throws IOException {
        String[] stats = statisticsParser(statistics);

        FileWriter writer = new FileWriter(SETTINGS_FILE, true);

        String line = String.join(CSV_SPLIT_BY, stats);
        line += "\n";
        writer.write(line);

        writer.flush();
        writer.close();
    }

    private String[] statisticsParser(Statistics statistics) {
        String[] parsed = new String[9];

        parsed[0] = String.valueOf(statistics.getWorldDays());
        parsed[1] = String.valueOf(statistics.getNumberAnimals());
        parsed[2] = String.valueOf(statistics.getNumberGrass());
        parsed[3] = String.valueOf(statistics.getNumberDeadAnimals());
        parsed[4] = String.valueOf(statistics.getAvgChildren());
        parsed[5] = String.valueOf(statistics.getAvgLife());
        parsed[6] = String.valueOf(statistics.getAvgEnergy());
        parsed[7] = String.valueOf(statistics.getFreePositionQuantity());
        parsed[8] = String.valueOf(statistics.getDominantGenotype());

        return parsed;
    }

    public void setSettingsFile(String name) throws Exception {
        String date = "";
        Calendar calendar = Calendar.getInstance();
        date += calendar.get(Calendar.DAY_OF_MONTH);
        date += calendar.get(Calendar.MONTH);
        date += calendar.get(Calendar.YEAR);
        date += calendar.get(Calendar.HOUR_OF_DAY);
        date += calendar.get(Calendar.MINUTE);
        date += calendar.get(Calendar.SECOND);

        SETTINGS_FILE = "src/main/statistics/" + name + "_" + date;
        if (new File(SETTINGS_FILE).exists()) {
            throw new Exception("this simulation already exist");
        }

        FileWriter writer = new FileWriter(SETTINGS_FILE, true);
        String headers = "day,animals quantity,grass quantity,dead animals quantity,avg children quantity,avg life length,avg energy,free position quantity,domain genotype\n";
        writer.write(headers);
        writer.flush();
        writer.close();
    }
}
