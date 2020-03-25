package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserController extends AbstractController<User, Integer> {

    ObjectMapper mapper=new ObjectMapper();
    Database database = new Database("C:\\Users\\ilyin\\Study\\java\\Practice\\file_write_and_read_demo\\user.json");
    UserConverter converter = new UserConverter();

    @Override
    public User[] getAll() throws IOException {
        return this.getAllAsList().toArray(new User[0]);
    }

    public List<User> getAllAsList() throws IOException {
        return this.converter.fromJSONFileToList(this.database.getDb());
    }

    @Override
    public User getEntityById(Integer id) throws Exception {
        User[]users=this.getAll();
        for (User user : users) {
            if(user.getId()==id) return user;
        }
        throw new Exception("User having this id doesn't exist!");
    }

    @Override
    public User update(User entity) throws IOException {
        int index = findUser(entity);
        User[] users = this.getAll();
        if(index>=0)
        {
            if(users[index].equals(entity)) return users[index];
            users[index].setName(entity.getName());
            users[index].setEmail(entity.getEmail());
            updateDb(users);
        }
        return users[index];
    }

    @Override
    public boolean delete(Integer id) throws IOException {
        int index = findUser(id);
        if(index>=0) {
            List<User> list = this.getAllAsList();
            list.remove(index);
            updateDb(list);
            return true;
        }
        return false;
    }

    @Override
    public boolean create(User entity) throws IOException {
        for(User user : this.getAll())
        {
            if(user.getId()==entity.getId()||user.getEmail().equals(entity.getEmail())) return false;
            //generate log record or some kind of error
        }
        try {
            List<User>list = this.getAllAsList();
            list.add(entity);
            updateDb(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int findUser(User user) throws IOException {
        int index = -1;
        for(int i=0;i<this.getAll().length;++i){
            if(this.getAll()[i].getId()==user.getId()) { index = i; }
        }
        return index;
    }

    public int findUser(Integer id) throws IOException {
        int index = -1;
        for(int i=0;i<this.getAll().length;++i){
            if(this.getAll()[i].getId()==id.intValue()) { index = i; }
        }
        return index;
    }

    public boolean updateDb(List<User>list)  {
        try {
            mapper.writeValue(database.getDb(), list.toArray(new User[0]));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDb(User[]users)  {
        try {
            mapper.writeValue(database.getDb(), users);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
