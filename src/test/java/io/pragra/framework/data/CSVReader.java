package io.pragra.framework.data;

import io.pragra.framework.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/*
    SingleTon
 */
public class CSVReader {
    private static Logger logger = LogManager.getLogger(CSVReader.class);
    private List<Object[]> data = new ArrayList<>();
    private static CSVReader instance;

    private CSVReader(){
        logger.debug("Reading file {} from disk", Config.getProperty("csv.data"));
        try {
            List<String> lines = Files.readAllLines(Paths.get(Config.getProperty("csv.data").trim()));
            lines.forEach(s->data.add(s.split(",")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static List<Object[]> getData(){
        synchronized (CSVReader.class){
            if( instance == null ){
                instance = new CSVReader();
            }
        }
        return instance.data;
    }
}
