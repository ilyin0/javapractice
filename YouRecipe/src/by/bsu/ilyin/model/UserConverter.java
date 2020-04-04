package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class UserConverter extends Converter<User>
{
    @Override
    public List<User> fromJSONToList(String JSONString){
        try {
            return objectMapper.readValue(JSONString, new TypeReference<List<User>>() {});
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
