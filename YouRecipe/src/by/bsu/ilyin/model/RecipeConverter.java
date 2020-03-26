package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.User;
import by.bsu.ilyin.entities.recipe.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class RecipeConverter extends Converter<Recipe> {
    @Override
    public List<Recipe> fromJSONToList(String JSONString) throws JsonProcessingException {
        return objectMapper.readValue(JSONString, new TypeReference<List<Recipe>>() {});
    }
}
