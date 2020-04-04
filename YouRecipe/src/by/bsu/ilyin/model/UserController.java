package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class UserController extends Controller<User, Integer> {

    public UserController() {
        this.mapper=new ObjectMapper();
        this.database = new Database("C:\\Users\\ilyin\\Study\\EPAM\\Valeriya\\Labs\\Yourecipe\\javapractice\\YouRecipe\\db\\user.json");
        this.converter=new UserConverter();
    }

    @Override
    public User[] getAll() throws IOException {
        return this.getAllAsList().toArray(new User[0]);
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
