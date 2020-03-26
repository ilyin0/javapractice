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
    @Override
    public List<User> fromJSONToList(String JSONString) throws JsonProcessingException {
        return objectMapper.readValue(JSONString, new TypeReference<List<User>>() {});
    }
}
