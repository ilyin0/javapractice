package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class UserController extends AbstractController<User, Integer> {

    public UserController() {
        this.mapper=new ObjectMapper();
        this.database = new Database("C:\\Users\\ilyin\\Study\\EPAM\\Valeriya\\Labs\\Yourecipe\\javapractice\\YouRecipe\\user.json");
        this.converter=new UserConverter();
    }

    @Override
    public User[] getAll() throws IOException {
        return this.getAllAsList().toArray(new User[0]);
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
        return updateDb(list.toArray(new User[0]));
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
