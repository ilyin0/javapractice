package by.bsu.ilyin.dao;

import by.bsu.ilyin.entities.MenuUnit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class MenuUnitConverter extends Converter<MenuUnit> {
    @Override
    public List<MenuUnit> fromJSONToList(String JSONString) {
        try {
            return objectMapper.readValue(JSONString, new TypeReference<List<MenuUnit>>() {});
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
