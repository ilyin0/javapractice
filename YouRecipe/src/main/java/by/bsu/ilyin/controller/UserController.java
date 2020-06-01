package by.bsu.ilyin.controller;

import by.bsu.ilyin.entities.User;
import by.bsu.ilyin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    UserService userService = new UserService();

    public UserController() throws SQLException, ClassNotFoundException {
        super();
    }

    @GetMapping
    public ResponseEntity<List<User>>getAll(){
        List<User>users = userService.getAllAsList();
        if(users!=null){
            return ResponseEntity.ok(users);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") Long id){
        User user = userService.getById(id);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<User> isEntityById(@PathVariable(value = "id") Long id){
        if(userService.getById(id)!=null) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping
    public ResponseEntity<User>create(@RequestBody User user){
        boolean wasCreated = userService.create(user);
        if(wasCreated){
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User>update(@PathVariable(value = "id") Long id, @RequestBody User user){
        user.setUid(id);
        boolean wasUpdated = userService.update(user);
        if(wasUpdated){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable(value = "id") Long id){
        boolean wasDeleted = userService.delete(id);
        HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseStatus);
    }
}
