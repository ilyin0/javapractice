package by.bsu.ilyin.service;

import by.bsu.ilyin.dao.UserDAO;
import by.bsu.ilyin.hibernate.User;

import java.sql.SQLException;

public class UserService extends Service<User,Integer> {

    public UserService() throws SQLException, ClassNotFoundException {
        DAO = new UserDAO();
    }

    public User getByEmail(String email){
        return DAO.getByEmail(email);
    }
}
