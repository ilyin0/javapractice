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

public class UserConverter extends Converter<User>
{
    public List<User> fromJSONToList(String JSONString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(JSONString, new TypeReference<List<User>>() {});
    }

    public List<User> fromJSONFileToList(File JSONFile) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(JSONFile));
        byte[] dataInBytes = new byte[dataInputStream.available()];
        dataInputStream.readFully(dataInBytes);
        dataInputStream.close();
        String content = new String(dataInBytes, 0, dataInBytes.length);
        return fromJSONToList(content);
    }
}
