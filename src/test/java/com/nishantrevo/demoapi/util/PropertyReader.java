package com.nishantrevo.demoapi.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertyReader {
    
    private static final Properties properties = new Properties();
    
    public void clear(){
        log.info("Clearing all properties");
        properties.clear();
    }
    
    public static String get(String key){
        String value = properties.getProperty(key);
        log.info("Returning: {} = {}", key, value);
        return value;
    }
    
    public static void set(String key, String value){
        log.info("Setting: {} = {}", key, value);
        properties.setProperty(key, value);
    }
    
    public static void init(String ... propertyFiles) throws IOException {
        for(String file: propertyFiles){
            File pFile = new File(file);
            if (pFile.isFile()){
                addPropertiesFromFile(file);
            }
            else if(pFile.isDirectory()){
                for(File fileInDir: pFile.listFiles()){
                    if(fileInDir.getName().endsWith(".properties"))
                        addPropertiesFromFile(fileInDir.getAbsolutePath());
                }
            }
        }
    }
    
    public static void addPropertiesFromFile(String propertyFile) throws IOException {
        log.info("Loading from file: {}", propertyFile);
        File file = new File(propertyFile);
        FileReader reader = new FileReader(file);
        properties.load(reader);
    }
}
