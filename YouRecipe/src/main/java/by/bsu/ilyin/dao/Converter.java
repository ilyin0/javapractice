package by.bsu.ilyin.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.internal.xjc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public abstract class Converter<Type> {

    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LogManager.getLogger();

    public abstract List<Type> fromJSONToList(String JSONString) throws JsonProcessingException;

    public  List<Type> fromJSONFileToList(File JSONFile) throws IOException{
        return fromJSONToList(fromJSONFileToJSONString(JSONFile));
    };

    public String fromJSONFileToJSONString(File JSONFile) throws IOException {
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(JSONFile));
            byte[] dataInBytes = new byte[dataInputStream.available()];
            dataInputStream.readFully(dataInBytes);
            dataInputStream.close();
            return new String(dataInBytes, 0, dataInBytes.length);
        }
        catch(IOException e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
