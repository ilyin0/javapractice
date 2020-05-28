package by.bsu.ilyin.service;

import by.bsu.ilyin.controller.UserController;
import by.bsu.ilyin.entities.User;

import java.sql.SQLException;

public class UserService extends Service<User,Integer> {

    public UserService() throws SQLException, ClassNotFoundException {
        controller=new UserController();
    }

    public User getByEmail(String email){
        return controller.getByEmail(email);
    }
}
