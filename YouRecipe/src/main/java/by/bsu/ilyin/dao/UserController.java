package by.bsu.ilyin.dao;

import by.bsu.ilyin.dbc.DBC;
import by.bsu.ilyin.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserController extends Controller<User, Integer> {

    public UserController() {
        this.mapper=new ObjectMapper();
        this.database = new Database("/Users/ilyin/Documents/Study/university/epam/javapractice/YouRecipe/db/user.json");
        this.converter=new UserConverter();
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
            record.put("name", resultSet.getString("email"));
            record.put("email", resultSet.getString("email"));
            record.put("password", resultSet.getString("password"));
            jsonArray.put(record);
        }
        return jsonArray.toString();
    }

    private ResultSet getAllAsResultSet() throws SQLException, ClassNotFoundException {
        Connection connection = DBC.getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM User";
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
}
