package by.bsu.ilyin.service;

import by.bsu.ilyin.dao.UserController;
import by.bsu.ilyin.entities.User;

public class UserService extends Service<User,Integer> {

    public UserService() {
        controller=new UserController();
    }

    public User getByEmail(String email){
        return controller.getByEmail(email);
    }
}
