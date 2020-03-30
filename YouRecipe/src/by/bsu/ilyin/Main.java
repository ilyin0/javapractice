package by.bsu.ilyin;

import by.bsu.ilyin.entities.User;
import by.bsu.ilyin.model.UserController;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        List<User>list=userController.getAllAsList();
        userController.update(new User(77,"lll","qwerty@lib.by"));
        userController.create(new User(77,"antoxa","rrr@jaf.ff"));
        System.out.print(userController.getAllAsList().toString()+"\n");
//        RecipeController recipeController=new RecipeController();
//        System.out.print(recipeController.getAllAsList().toString()+"\n");
        userController.delete(77);
        System.out.print(userController.getAllAsList().toString());
    }
}
