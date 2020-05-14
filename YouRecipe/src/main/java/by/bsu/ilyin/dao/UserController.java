package by.bsu.ilyin.dao;

import by.bsu.ilyin.dbc.DBC;
import by.bsu.ilyin.entities.User;
import by.bsu.ilyin.exceptions.ControllerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import oracle.jdbc.pool.OracleDataSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserController extends Controller<User, Integer> {

    public UserController() throws SQLException, ClassNotFoundException {
        this.mapper=new ObjectMapper();
        this.converter=new UserConverter();
        connection = DBC.getConnection();
    }

    public UserController(Connection con){
        this.mapper = new ObjectMapper();
        this.converter = new UserConverter();
        this.connection = con;
    }

    @Override
    public User[] getAll() throws IOException, SQLException, ClassNotFoundException, JSONException {
        return this.getAllAsList().toArray(new User[0]);
    }

    @Override
    public List<User> getAllAsList() throws SQLException, JSONException, ClassNotFoundException, JsonProcessingException {
        return converter.fromJSONToList(getAllAsJSONString());
    }

    private String getAllAsJSONString() throws SQLException, ClassNotFoundException, JSONException {
        ResultSet resultSet = getAllAsResultSet();
        JSONArray jsonArray = new JSONArray();
        while(resultSet.next()){
            JSONObject record = new JSONObject();
            record.put("id", resultSet.getInt("id"));
            record.put("name", resultSet.getString("name"));
            record.put("email", resultSet.getString("email"));
            record.put("password", resultSet.getString("password"));
            jsonArray.put(record);
        }
        return jsonArray.toString();
    }

    private ResultSet getAllAsResultSet() throws SQLException, ClassNotFoundException {
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM \"User\"";
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    @Override
    public boolean updateDb(List<User>list)  {
        return updateDb(list.toArray(new User[0]));
    }

    @Override
    public User getByEmail(String email){
        try {
            User[] users = this.getAll();
            for (User user : users) {
                if (email.equals(user.getEmail())) return user;
            }
            throw new Exception("User having this email doesn't exist");
        }
        catch(Exception exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    @Override
    public boolean create(User entity) throws SQLException, ControllerException {
        Statement statement = connection.createStatement();
        String query = "INSERT INTO \"User\" (id, name, email, password) VALUES (" + entity.getId() + ", " + entity.getName() + ", " + entity.getEmail() + ", " + entity.getPassword() + ")";
        int e = statement.executeUpdate(query);
        if(e>0){
            logger.info("Entity was added into the database");
            return true;
        }
        throw new ControllerException("Entity wasn't added!");
    }

    @Override
    public boolean update(User entity) throws SQLException, ControllerException {
        Statement statement = connection.createStatement();
        String query = "UPDATE \"User\" SET email="+entity.getEmail()+", name="+entity.getName()+", password="+entity.getPassword();
        int e = statement.executeUpdate(query);
        if(e>0){
            logger.info("Entity was updated!");
            return true;
        }
        throw new ControllerException("Entity wasn't updated!");
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ControllerException {
        Statement statement = connection.createStatement();
        String query = "DELETE FROM \"User\" WHERE id="+id;
        int e = statement.executeUpdate(query);
        if(e>0){
            logger.info("User was deleted!");
        }
        throw new ControllerException("Entity wasn't deleted!");
    }
}
