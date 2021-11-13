package io.pragra.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

/*
    SingleTon Pattern
 */
public class Config {
    private static Config config;
    private Properties properties;

    private Config(){
        /* load the file and get the keys */
        properties = new Properties();
        try (InputStream stream = new FileInputStream(Paths.get("framwork.properties").toFile())) {
            properties.load(stream);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static String getProperty(String key){
        if(config == null) {
            synchronized (Config.class){
                config = new Config();
            }
        }
        return config.properties.getProperty(key);
    }

}
