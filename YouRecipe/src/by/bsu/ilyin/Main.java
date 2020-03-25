package by.bsu.ilyin;

import by.bsu.ilyin.entities.User;
import by.bsu.ilyin.model.Database;
import by.bsu.ilyin.model.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        List<User>list=userController.getAllAsList();
        userController.update(new User(16,"lll","qwerty@lib.by"));
        System.out.print(userController.getAllAsList().toString());

    }
}
