package by.bsu.ilyin.dao;

import by.bsu.ilyin.entities.User;
import by.bsu.ilyin.exceptions.ControllerException;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO extends DAO<User, Integer> {

    public UserDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public User[] getAll() {
        return this.getAllAsList().toArray(new User[0]);
    }

    @SneakyThrows
    @Override
    public List<User> getAllAsList()  {
        Connection connection = dbc.getConnection();
        ResultSet resultSet = getAllAsResultSet();
        List<User>users = new ArrayList<>();
        User user;
        while(resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("UID"));
            user.setEmail(resultSet.getString("email"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));

            users.add(user);
        }
        return users;
    }


    @SneakyThrows
    @Override
    public User getById(Integer id) {
        String query = "SELECT * FROM \"User\" WHERE \"UID\" = '" +id+"'";
        ResultSet resultSet = dbc.getConnection().createStatement().executeQuery(query);
        User user = new User();
        resultSet.next();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));

        return user;
    }

    @SneakyThrows
    private ResultSet getAllAsResultSet() {
        String query = "SELECT * FROM \"User\"";
        return dbc.getConnection().createStatement().executeQuery(query);
    }

    @Override
    public User getByEmail(String email){
        try {
            List<User> users = this.getAllAsList();
            for (User user : users) {
                if (email.equals(user.getEmail())) return user;
            }
            throw new ControllerException("User having this email doesn't exist");
        }
        catch(ControllerException exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    @Override
    public boolean create(User entity)  {
        String query = "INSERT INTO \"User\" (\"name\", \"email\", \"password\") VALUES (\'" + entity.getName() + "\', \'" + entity.getEmail() + "\', \'" + entity.getPassword() + "\')";
        try {
            int e = dbc.getConnection().createStatement().executeUpdate(query);
            if (e > 0) {
                logger.info("Entity was added into the database");
                return true;
            }
            else throw new ControllerException("Entity wasn't created!");
        }
        catch (ControllerException | SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @SneakyThrows
    @Override
    public boolean update(User entity)  {
        //Statement statement = connection.createStatement();
        String query = "UPDATE \"User\" SET \"name\"=\'"+entity.getName()+"\', \"password\"= \'"+entity.getPassword() + "\' WHERE \"email\" = \'" + entity.getEmail() + "\'";
        try {
            int e = dbc.getConnection().createStatement().executeUpdate(query);
            if (e > 0) {
                logger.info("Entity was updated!");
                return true;
            }
            else throw new ControllerException("Entity wasn't updated!");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Integer id)  {
        //Statement statement = connection.createStatement();
        String query = "DELETE FROM \"User\" WHERE \"UID\"=\'"+id + "\'";
        try{
            dbc.getConnection().createStatement().executeUpdate(query);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        logger.info("User with id = "+id+" was deleted!");
        return true;
    }
}
