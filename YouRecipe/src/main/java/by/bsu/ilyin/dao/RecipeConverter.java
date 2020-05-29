package by.bsu.ilyin.dao;

import by.bsu.ilyin.entities.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class RecipeConverter extends Converter<Recipe> {
    @Override
    public List<Recipe> fromJSONToList(String JSONString) {
        try {
            return objectMapper.readValue(JSONString, new TypeReference<List<Recipe>>() {});
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
