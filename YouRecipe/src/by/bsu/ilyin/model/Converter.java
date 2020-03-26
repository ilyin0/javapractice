package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public abstract class Converter<Type> {

    ObjectMapper objectMapper = new ObjectMapper();

    public abstract List<Type> fromJSONToList(String JSONString) throws JsonProcessingException;

    public  List<Type> fromJSONFileToList(File JSONFile) throws IOException{
        return fromJSONToList(fromJSONFileToJSONString(JSONFile));
    };

    public String fromJSONFileToJSONString(File JSONFile) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(JSONFile));
        byte[] dataInBytes = new byte[dataInputStream.available()];
        dataInputStream.readFully(dataInBytes);
        dataInputStream.close();
        return new String(dataInBytes, 0, dataInBytes.length);
    }
}
