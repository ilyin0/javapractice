package by.bsu.ilyin.controller;

import by.bsu.ilyin.hibernate.Recipe;
import by.bsu.ilyin.service.RecipeService;
import org.json.JSONObject;
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

    @GetMapping
    public ResponseEntity<List<Recipe>>getAll(){
        List<Recipe>recipes = service.getAllAsList();
        if(recipes!=null){
            return ResponseEntity.ok(recipes);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable(value = "id") String id){
        Recipe recipe = service.getById(Long.valueOf(id));
        if(recipe!=null){
            return ResponseEntity.ok(recipe);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Recipe>create(@RequestBody Recipe recipe){
        boolean wasCreated = service.create(recipe);
        if(wasCreated){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe>update(@PathVariable(value = "id") Integer id, @RequestBody Recipe recipe){
        //recipe.setId(id);
        boolean wasUpdated = service.update(recipe);
        if(wasUpdated){
            return new ResponseEntity<>(recipe, HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable(value = "id") String id){
        boolean wasDeleted = service.delete(Long.valueOf(id));
        HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseStatus);
    }
}
