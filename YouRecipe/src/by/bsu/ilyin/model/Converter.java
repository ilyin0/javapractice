package by.bsu.ilyin.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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
