package by.bsu.ilyin.controller;

import by.bsu.ilyin.entities.Recipe;
import by.bsu.ilyin.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {
    RecipeService service = new RecipeService();

    public RecipeController() throws SQLException, ClassNotFoundException {
        super();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>>getAll(){
        List<Recipe>recipes = service.getAllAsList();
        if(recipes!=null){
            return ResponseEntity.ok(recipes);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/notAll")
    public ResponseEntity<Recipe> getById(@RequestParam(value = "id") String id){
        Recipe recipe = service.getById(Integer.valueOf(id));
        if(recipe!=null){
            return ResponseEntity.ok(recipe);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Recipe>create(@RequestBody Recipe recipe){
        boolean wasCreated = service.create(recipe);
        if(wasCreated){
            return new ResponseEntity<>(recipe, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping
    public ResponseEntity<Recipe>update(@RequestBody Recipe recipe){
        boolean wasUpdated = service.update(recipe);
        if(wasUpdated){
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void>delete(@RequestParam(value = "id") String id){
        boolean wasDeleted = service.delete(Integer.valueOf(id));
        HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseStatus);
    }
}
