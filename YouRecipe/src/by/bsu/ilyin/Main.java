package by.bsu.ilyin;

import by.bsu.ilyin.entities.User;
import by.bsu.ilyin.model.UserController;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        List<User>list=userController.getAllAsList();
        userController.create(new User(77,"lll","qwerty@lib.by"));
        userController.create(new User(77,"antoxa","rrr@jaf.ff"));
        userController.create(new User(78,"antoxa","rrr@jaf.ff"));
        System.out.print(userController.getAllAsList().toString()+"\n");
        System.out.print(userController.getAllAsList().toString());
    }
}
