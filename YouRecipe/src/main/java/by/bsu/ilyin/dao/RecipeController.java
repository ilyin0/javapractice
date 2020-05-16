package by.bsu.ilyin.dao;

import by.bsu.ilyin.entities.Recipe;
import by.bsu.ilyin.entities.recipe.Product;
import by.bsu.ilyin.entities.recipe.UnitOfRecipe;
import by.bsu.ilyin.exceptions.ControllerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecipeController extends Controller<Recipe, Integer> {

    public RecipeController() throws SQLException, ClassNotFoundException {
        super();
        this.mapper=new ObjectMapper();
        this.database = new Database("/Users/ilyin/Documents/Study/university/epam/javapractice/YouRecipe/db/recipe.json");
        this.converter=new RecipeConverter();
    }

    @Override
    public Recipe[] getAll() throws IOException, SQLException, JSONException, ClassNotFoundException {
        return this.getAllAsList().toArray(new Recipe[0]);
    }

    @Override
    public boolean updateDb(List<Recipe> list) {
        return updateDb(list.toArray(new Recipe[0]));
    }

    @Override
    public List<Recipe> getAllAsList() throws SQLException, ClassNotFoundException, JSONException, JsonProcessingException {
        return converter.fromJSONToList(getAllAsJSONString());
    }


    //TODO: separate to different methods (one query execution - one method)
    private String getAllAsJSONString() throws SQLException, ClassNotFoundException, JSONException {
        String queryRecipe = "SELECT * FROM \"Recipe\"";
        String queryUnitsOfRecipe;
        String querySteps;

        ResultSet resultSetOfRecipes = dbc.getConnection().createStatement().executeQuery(queryRecipe);
        JSONObject recordRecipe;

        JSONArray jsonArrayOfRecipes = new JSONArray();
        int idOfCurrentRecipe;

        JSONArray jsonArrayOfUnitsOfRecipe;
        ResultSet resultSetOfUnitsOfRecipe;
        JSONObject recordUnitOfRecipe;

        JSONObject recordProduct;

        JSONArray jsonArrayOfSteps;
        ResultSet resultSetOfSteps;
        JSONObject recordStep;

        while(resultSetOfRecipes.next()){
            recordRecipe = new JSONObject();

            idOfCurrentRecipe = resultSetOfRecipes.getInt("RID");

            recordRecipe.put("id", idOfCurrentRecipe);
            recordRecipe.put("name", resultSetOfRecipes.getString("name"));
            recordRecipe.put("userId", resultSetOfRecipes.getInt("userId"));

            queryUnitsOfRecipe = "select * from \"UnitOfRecipe\" inner join \"Product\" on \"productId\" = \"PID\" where \"recipeId\" =" + idOfCurrentRecipe;
            resultSetOfUnitsOfRecipe = dbc.getConnection().createStatement().executeQuery(queryUnitsOfRecipe);
            jsonArrayOfUnitsOfRecipe = new JSONArray();
            while(resultSetOfUnitsOfRecipe.next()){
                recordProduct = new JSONObject();
                recordProduct.put("name", resultSetOfUnitsOfRecipe.getString("name"));
                recordProduct.put("type", resultSetOfUnitsOfRecipe.getString("type"));
                recordProduct.put("calorificValue", resultSetOfUnitsOfRecipe.getInt("calorificValue"));
                recordProduct.put("proteins", resultSetOfUnitsOfRecipe.getInt("proteins"));
                recordProduct.put("fats", resultSetOfUnitsOfRecipe.getInt("fats"));
                recordProduct.put("carbohydrates", resultSetOfUnitsOfRecipe.getInt("carbohydrates"));

                recordUnitOfRecipe = new JSONObject();
                recordUnitOfRecipe.put("id", resultSetOfUnitsOfRecipe.getInt("UORID"));
                recordUnitOfRecipe.put("product", recordProduct);
                recordUnitOfRecipe.put("amount", resultSetOfUnitsOfRecipe.getInt("amount"));
                recordUnitOfRecipe.put("measure", resultSetOfUnitsOfRecipe.getString("measure"));
                recordUnitOfRecipe.put("recipeId", idOfCurrentRecipe);

                jsonArrayOfUnitsOfRecipe.put(recordUnitOfRecipe);
            }

            recordRecipe.put("unitsOfRecipe", jsonArrayOfUnitsOfRecipe);

            querySteps = "SELECT * FROM \"Step\" WHERE \"recipeId\" = " + idOfCurrentRecipe + " ORDER BY \"stepNumber\"";
            resultSetOfSteps = dbc.getConnection().createStatement().executeQuery(querySteps);
            jsonArrayOfSteps = new JSONArray();
            while(resultSetOfSteps.next()){
                recordStep = new JSONObject();
                recordStep.put("id", resultSetOfSteps.getInt("SID"));
                recordStep.put("image", resultSetOfSteps.getString("image"));
                recordStep.put("describe", resultSetOfSteps.getString("describe"));
                recordStep.put("stepNumber", resultSetOfSteps.getInt("stepNumber"));
                recordStep.put("recipeId", idOfCurrentRecipe);

                jsonArrayOfSteps.put(recordStep);
            }

            recordRecipe.put("steps", jsonArrayOfSteps);

            jsonArrayOfRecipes.put(recordRecipe);
        }
        return jsonArrayOfRecipes.toString();
    }

    @Override
    public boolean create(Recipe entity) throws SQLException, ControllerException, ClassNotFoundException {
        //INSERT INTO \"User\" (\"name\", \"email\", \"password\") VALUES (\'" + entity.getName() + "\', \'" + entity.getEmail() + "\', \'" + entity.getPassword() + "\')

        int executeCheck;

        //add recipe into the database
        dbc.getConnection().createStatement().executeUpdate("INSERT INTO \"Recipe\" (\"userId\", \"name\") VALUES (\'1\', \'" + entity.getName() + "\')");

        //add product into database
        StringBuilder queryProduct;
        Product currentProduct;
        for(int i=0;i<entity.getUnitsOfRecipe().size();++i){
            currentProduct = entity.getUnitsOfRecipe().get(i).getProduct();
            queryProduct = new StringBuilder("INSERT INTO \"Product\" (\"name\", \"type\", \"calorificValue\", \"proteins\", \"fats\", \"carbohydrates\") VALUES (\'");
            queryProduct
                    .append(currentProduct.getName()).append("\', \'")
                    .append(currentProduct.getType()).append("\', \'")
                    .append(currentProduct.getCalorificValue()).append("\', \'")
                    .append(currentProduct.getProteins()).append("\', \'")
                    .append(currentProduct.getFats()).append("\', \'")
                    .append(currentProduct.getCarbohydrates()).append("\')");
            executeCheck = dbc.getConnection().createStatement().executeUpdate(queryProduct.toString());
            if(executeCheck<1) {
                throw new ControllerException("Product wasn't added!");
            }
        }

        //getting id values of products accordingly order in list of units of current recipe
        //getting id value of current recipe
        int idOfRecipe = getIdOfRecipeByName(entity.getName());
        int[]idsOfProducts = new int[entity.getUnitsOfRecipe().size()];
        for(int i=0;i<entity.getUnitsOfRecipe().size();++i){
            idsOfProducts[i] = getIdOfProductByName(entity.getUnitsOfRecipe().get(i).getProduct().getName());
        }

        StringBuilder queryUnitOfRecipe;
        UnitOfRecipe currentUnitOfRecipe;
        for(int i=0;i<entity.getUnitsOfRecipe().size();++i){
            currentUnitOfRecipe = entity.getUnitsOfRecipe().get(i);
            queryUnitOfRecipe = new StringBuilder("INSERT INTO \"UnitOfRecipe\" (\"recipeId\", \"productId\", \"amount\", \"measure\") VALUES (\'");
            queryUnitOfRecipe
                    .append(idOfRecipe).append("\', \'")
                    .append(idsOfProducts[i]).append("\', \'")
                    .append(currentUnitOfRecipe.getAmount()).append("\', \'")
                    .append(currentUnitOfRecipe.getMeasure()).append("\')");
            executeCheck = dbc.getConnection().createStatement().executeUpdate(queryUnitOfRecipe.toString());
            if(executeCheck<1) {
                throw new ControllerException("unitOfRecipe wasn't added!");
            }
        }

        StringBuilder queryStep;
        for(int i=0;i<entity.getSteps().size();++i){
            queryStep = new StringBuilder("INSERT INTO \"Step\" (\"image\", \"describe\", \"stepNumber\", \"recipeId\") VALUES (\'");
            queryStep
                    .append(entity.getSteps().get(i).getImage()).append("\', \'")
                    .append(entity.getSteps().get(i).getDescribe()).append("\', \'")
                    .append(i).append("\', \'")
                    .append(idOfRecipe).append("\')");
            executeCheck = dbc.getConnection().createStatement().executeUpdate(queryStep.toString());
            if(executeCheck<1) {
                throw new ControllerException("Step wasn't added!");
            }
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) throws IOException, SQLException, JSONException, ClassNotFoundException, ControllerException {
        String queryRecipe = "DELETE FROM \"Recipe\" WHERE \"RID\" = \'" + id + "\'";
        String queryStep = "DELETE FROM \"Step\" WHERE \"recipeId\" = \'" + id + "\'";
        String queryUnitOfRecipe = "DELETE FROM \"UnitOfRecipe\" WHERE \"recipeId\" = \'" + id + "\'";
        try {
            dbc.getConnection().createStatement().executeUpdate(queryRecipe);
            dbc.getConnection().createStatement().executeUpdate(queryStep);
            dbc.getConnection().createStatement().executeUpdate(queryUnitOfRecipe);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        logger.info("Recipe with id = "+id+" was deleted!");
        return true;
    }

    @Override
    public Recipe getByName(String name){
        try {
            Recipe[] recipes = this.getAll();
            for (Recipe recipe : recipes) {
                if (name.equals(recipe.getName())) return recipe;
            }
            throw new Exception("Recipe having this name doesn't exist");
        }
        catch(Exception exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    private int getIdOfRecipeByName(String name) throws SQLException, ClassNotFoundException {
        String query = "SELECT \"RID\" FROM \"Recipe\" WHERE \"name\" = \'" + name + "\'";
        ResultSet resultSet =  dbc.getConnection().createStatement().executeQuery(query);
        resultSet.next();
        return resultSet.getInt("RID");
    }

    private int getIdOfProductByName(String name) throws SQLException, ClassNotFoundException {
        String query = "SELECT \"PID\" FROM \"Product\" WHERE \"name\" = \'" + name + "\'";
        ResultSet resultSet =  dbc.getConnection().createStatement().executeQuery(query);
        resultSet.next();
        return resultSet.getInt("PID");
    }




}
