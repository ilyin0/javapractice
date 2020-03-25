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

    public abstract List<Type> fromJSONToList(String JSONString) throws JsonProcessingException;

    public abstract List<Type> fromJSONFileToList(File JSONFile) throws IOException;
}
