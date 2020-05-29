package by.bsu.ilyin.controller;

import by.bsu.ilyin.dao.UserDAO;
import by.bsu.ilyin.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    UserDAO userDAO = new UserDAO();

    public UserController() throws SQLException, ClassNotFoundException {
        super();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User>getById(@RequestBody int id){
        User user = userDAO.getById(id);
        if(user!=null){
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User>create(@RequestBody User user){
        boolean wasCreated = userDAO.create(user);
        if(wasCreated){
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User>update(@RequestBody User user){
        boolean wasUpdated = userDAO.update(user);
        if(wasUpdated){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void>delete(@PathVariable int id){
        boolean wasDeleted = userDAO.delete(id);
        HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseStatus);
    }
}
