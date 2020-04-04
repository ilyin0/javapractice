package by.bsu.ilyin.service;

import by.bsu.ilyin.entities.User;
import by.bsu.ilyin.model.UserController;

import java.io.IOException;
import java.util.List;

public class UserService extends Service<User,Integer> {

    public UserService() {
        controller=new UserController();
    }

    @Override
    public User[] getAll() {
        try {
            return getAllAsList().toArray(new User[0]);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new User[0];
    }

    @Override
    public boolean updateDb(List<User>list) {
        return updateDb(list.toArray(new User[0]));
    }

}
