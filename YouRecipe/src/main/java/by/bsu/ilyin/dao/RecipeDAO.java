package by.bsu.ilyin.dao;

import by.bsu.ilyin.entities.Recipe;
import by.bsu.ilyin.entities.recipe.Product;
import by.bsu.ilyin.entities.recipe.Step;
import by.bsu.ilyin.entities.recipe.UnitOfRecipe;
import by.bsu.ilyin.exceptions.ControllerException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeDAO extends DAO<Recipe, Integer> {

    public RecipeDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public Recipe[] getAll()  {
        return this.getAllAsList().toArray(new Recipe[0]);
    }


    @SneakyThrows
    @Override
    public List<Recipe> getAllAsList() {

        Connection connection = dbc.getConnection();

        connection.setAutoCommit(false);

        Savepoint savepoint = connection.setSavepoint("Savepoint");

        try {
            Statement statement = connection.createStatement();

            String queryRecipe = "SELECT * FROM \"Recipe\"";

            ResultSet recipeResultSet = statement.executeQuery(queryRecipe);

            List<Recipe>recipes = new ArrayList<>();

            Recipe recipe;

            while(recipeResultSet.next()) {
                recipe = new Recipe();
                recipe.setName(recipeResultSet.getString("name"));
                recipe.setUserId(recipeResultSet.getInt("userId"));
                recipe.setId(recipeResultSet.getInt("RID"));

                List<UnitOfRecipe> unitOfRecipeList = new ArrayList<>();

                String queryUnitsOfRecipe = "select * from \"UnitOfRecipe\" inner join \"Product\" on \"productId\" = \"PID\" where \"recipeId\" ='" + recipeResultSet.getInt("RID")+"'";
                ResultSet resultSetOfUnitsOfRecipe = dbc.getConnection().createStatement().executeQuery(queryUnitsOfRecipe);
                while (resultSetOfUnitsOfRecipe.next()) {
                    Product product = new Product();
                    product.setName(resultSetOfUnitsOfRecipe.getString("name"));
                    product.setType(resultSetOfUnitsOfRecipe.getString("type"));
                    product.setCalorificValue(resultSetOfUnitsOfRecipe.getInt("calorificValue"));
                    product.setProteins(resultSetOfUnitsOfRecipe.getInt("proteins"));
                    product.setFats(resultSetOfUnitsOfRecipe.getInt("fats"));
                    product.setCarbohydrates(resultSetOfUnitsOfRecipe.getInt("carbohydrates"));

                    UnitOfRecipe unitOfRecipe = new UnitOfRecipe();

                    unitOfRecipe.setId(resultSetOfUnitsOfRecipe.getInt("UORID"));
                    unitOfRecipe.setProduct(product);
                    unitOfRecipe.setAmount(resultSetOfUnitsOfRecipe.getInt("amount"));
                    unitOfRecipe.setMeasure(resultSetOfUnitsOfRecipe.getString("measure"));
                    unitOfRecipe.setRecipeId(recipeResultSet.getInt("RID"));

                    unitOfRecipeList.add(unitOfRecipe);
                }
                recipe.setUnitsOfRecipe(unitOfRecipeList);

                String querySteps = "Select * From \"Step\" Where \"recipeId\" = '"+recipeResultSet.getInt("RID") + "' ORDER BY \"stepNumber\"";

                ResultSet resultSetOfSteps = dbc.getConnection().createStatement().executeQuery(querySteps);

                List<Step>stepList = new ArrayList<>();

                while(resultSetOfSteps.next()){
                    Step step = new Step();
                    step.setRecipeId(recipeResultSet.getInt("RID"));
                    step.setId(resultSetOfSteps.getInt("SID"));
                    step.setDescribe(resultSetOfSteps.getString("describe"));
                    step.setImage(resultSetOfSteps.getString("describe"));
                    step.setStepNumber(resultSetOfSteps.getInt("stepNumber"));

                    stepList.add(step);
                }

                recipe.setSteps(stepList);

                recipes.add(recipe);
            }

            return recipes;
        }
        catch (SQLException e){
            logger.error(e.getMessage());
            connection.rollback(savepoint);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public Recipe getById(Integer id) {
        Connection connection = dbc.getConnection();

        connection.setAutoCommit(false);

        Savepoint savepoint = connection.setSavepoint("Savepoint");

        try {
            Statement statement = connection.createStatement();

            String queryRecipe = "SELECT * FROM \"Recipe\" WHERE 'id'='" + id+"'";

            ResultSet recipeResultSet = statement.executeQuery(queryRecipe);
            Recipe recipe = new Recipe();
            List<UnitOfRecipe> unitOfRecipeList = new ArrayList<>();
            while (recipeResultSet.next()) {
                recipe.setId(recipeResultSet.getInt("RID"));
                recipe.setName(recipeResultSet.getString("name"));
                recipe.setUserId(recipeResultSet.getInt("userId"));
            }

            String queryUnitsOfRecipe = "select * from \"UnitOfRecipe\" inner join \"Product\" on \"productId\" = \"PID\" where \"recipeId\" ='" + recipeResultSet.getInt("RID")+"'";
            ResultSet resultSetOfUnitsOfRecipe = dbc.getConnection().createStatement().executeQuery(queryUnitsOfRecipe);
            while (resultSetOfUnitsOfRecipe.next()) {
                Product product = new Product();
                product.setName(resultSetOfUnitsOfRecipe.getString("name"));
                product.setType(resultSetOfUnitsOfRecipe.getString("type"));
                product.setCalorificValue(resultSetOfUnitsOfRecipe.getInt("calorificValue"));
                product.setProteins(resultSetOfUnitsOfRecipe.getInt("proteins"));
                product.setFats(resultSetOfUnitsOfRecipe.getInt("fats"));
                product.setCarbohydrates(resultSetOfUnitsOfRecipe.getInt("carbohydrates"));

                UnitOfRecipe unitOfRecipe = new UnitOfRecipe();

                unitOfRecipe.setId(resultSetOfUnitsOfRecipe.getInt("UORID"));
                unitOfRecipe.setProduct(product);
                unitOfRecipe.setAmount(resultSetOfUnitsOfRecipe.getInt("amount"));
                unitOfRecipe.setMeasure(resultSetOfUnitsOfRecipe.getString("measure"));
                unitOfRecipe.setRecipeId(recipeResultSet.getInt("RID"));

                unitOfRecipeList.add(unitOfRecipe);
            }

            recipe.setUnitsOfRecipe(unitOfRecipeList);

            String querySteps = "Select * From \"Step\" Where \"recipeId\" = '"+recipeResultSet.getInt("RID") + "' ORDER BY \"stepNumber\"";

            ResultSet resultSetOfSteps = dbc.getConnection().createStatement().executeQuery(querySteps);

            List<Step>stepList = new ArrayList<>();

            while(resultSetOfSteps.next()){
                Step step = new Step();
                step.setRecipeId(recipeResultSet.getInt("RID"));
                step.setId(resultSetOfSteps.getInt("SID"));
                step.setDescribe(resultSetOfSteps.getString("describe"));
                step.setImage(resultSetOfSteps.getString("describe"));
                step.setStepNumber(resultSetOfSteps.getInt("stepNumber"));

                stepList.add(step);
            }

            recipe.setSteps(stepList);

            return recipe;
        }
        catch (SQLException e){
            logger.error(e.getMessage());
            connection.rollback(savepoint);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public boolean create(Recipe entity) {
        Connection connection = dbc.getConnection();

        connection.setAutoCommit(false);

        Savepoint savepoint = connection.setSavepoint("Savepoint");

        int executeCheck;

        try {

            //add recipe into the database
            connection.createStatement().executeUpdate("INSERT INTO \"Recipe\" (\"userId\", \"name\") VALUES (\'1\', \'" + entity.getName() + "\')");

            //add product into database
            StringBuilder queryProduct;
            Product currentProduct;
            for (int i = 0; i < entity.getUnitsOfRecipe().size(); ++i) {
                currentProduct = entity.getUnitsOfRecipe().get(i).getProduct();
                queryProduct = new StringBuilder("INSERT INTO \"Product\" (\"name\", \"type\", \"calorificValue\", \"proteins\", \"fats\", \"carbohydrates\") VALUES (\'");
                queryProduct
                        .append(currentProduct.getName()).append("\', \'")
                        .append(currentProduct.getType()).append("\', \'")
                        .append(currentProduct.getCalorificValue()).append("\', \'")
                        .append(currentProduct.getProteins()).append("\', \'")
                        .append(currentProduct.getFats()).append("\', \'")
                        .append(currentProduct.getCarbohydrates()).append("\')");
                executeCheck = connection.createStatement().executeUpdate(queryProduct.toString());
                if (executeCheck < 1) {
                    throw new ControllerException("Product wasn't added!");
                }
            }

            //getting id values of products accordingly order in list of units of current recipe
            //getting id value of current recipe
            int idOfRecipe = getIdOfRecipeByName(entity.getName());
            int[] idsOfProducts = new int[entity.getUnitsOfRecipe().size()];
            for (int i = 0; i < entity.getUnitsOfRecipe().size(); ++i) {
                idsOfProducts[i] = getIdOfProductByName(entity.getUnitsOfRecipe().get(i).getProduct().getName());
            }

            StringBuilder queryUnitOfRecipe;
            UnitOfRecipe currentUnitOfRecipe;
            for (int i = 0; i < entity.getUnitsOfRecipe().size(); ++i) {
                currentUnitOfRecipe = entity.getUnitsOfRecipe().get(i);
                queryUnitOfRecipe = new StringBuilder("INSERT INTO \"UnitOfRecipe\" (\"recipeId\", \"productId\", \"amount\", \"measure\") VALUES (\'");
                queryUnitOfRecipe
                        .append(idOfRecipe).append("\', \'")
                        .append(idsOfProducts[i]).append("\', \'")
                        .append(currentUnitOfRecipe.getAmount()).append("\', \'")
                        .append(currentUnitOfRecipe.getMeasure()).append("\')");
                executeCheck = connection.createStatement().executeUpdate(queryUnitOfRecipe.toString());
                if (executeCheck < 1) {
                    throw new ControllerException("unitOfRecipe wasn't added!");
                }
            }

            StringBuilder queryStep;
            for (int i = 0; i < entity.getSteps().size(); ++i) {
                queryStep = new StringBuilder("INSERT INTO \"Step\" (\"image\", \"describe\", \"stepNumber\", \"recipeId\") VALUES (\'");
                queryStep
                        .append(entity.getSteps().get(i).getImage()).append("\', \'")
                        .append(entity.getSteps().get(i).getDescribe()).append("\', \'")
                        .append(i).append("\', \'")
                        .append(idOfRecipe).append("\')");
                executeCheck = connection.createStatement().executeUpdate(queryStep.toString());
                if (executeCheck < 1) {
                    throw new ControllerException("Step wasn't added!");
                }
            }

            connection.commit();
            return true;
        }
        catch (SQLException e){
            logger.error(e.getMessage());
            connection.rollback(savepoint);
        }

        return false;
    }

    //TODO: fill this method by necessary code
    @SneakyThrows
    @Override
    public boolean update(Recipe entity) {
        Connection connection = dbc.getConnection();

        connection.setAutoCommit(false);

        Savepoint savepoint = connection.setSavepoint("Savepoint");

        try {
            String recipeQuery = "UPDATE \"Recipe\" SET \"name\" = '" + entity.getName() + "', \"userId\" = '" + entity.getUserId() + "' WHERE \"RID\" = '" + entity.getId()+"'";

            connection.createStatement().executeUpdate(recipeQuery);

            for (Step step : entity.getSteps()) {
                String stepQuery = "UPDATE \"Step\" SET \"image\" = '" + step.getImage() + "', \"describe\" = '" + step.getDescribe() + "', \"stepNumber\" = '" + step.getStepNumber() + "' WHERE \"SID\" = '" + step.getId()+"'";
                connection.createStatement().executeUpdate(stepQuery);
            }

            for (UnitOfRecipe unit : entity.getUnitsOfRecipe()) {
                String unitsOfRecipeQuery = "UPDATE \"UnitOfRecipe\" SET \"amount\" = '" + unit.getAmount() + "', \"measure\" = '" + unit.getMeasure() + "' WHERE \"UORID\" = '" + unit.getId() + "'";
                String productQuery = "UPDATE (SELECT * FROM \"Step\" INNER JOIN \"UnitOfRecipe\" ON \"PID\" = \"productId\" WHERE \"UORID\" = '" + unit.getId() + "') SET \"name\" = '" + unit.getProduct().getName() + "', \"type\" = '" + unit.getProduct().getType() + "', \"calorificValue\" = '" + unit.getProduct().getCalorificValue() + "', \"proteins\" = '" + unit.getProduct().getProteins() + "', \"fats\" = '" + unit.getProduct().getFats() + "', \"carbohydrates\" = '" + unit.getProduct().getCarbohydrates() + "'";
                connection.createStatement().executeUpdate(unitsOfRecipeQuery);
                connection.createStatement().executeUpdate(productQuery);
            }

            connection.commit();
            return true;
        }
        catch (SQLException e){
            logger.error(e.getMessage());
            connection.rollback(savepoint);
        }
        return false;
    }

    @SneakyThrows
    @Override
    public boolean delete(Integer id) {
        Connection connection = dbc.getConnection();

        connection.setAutoCommit(false);

        Savepoint savepoint = connection.setSavepoint("savepoint");

        String queryRecipe = "DELETE FROM \"Recipe\" WHERE \"RID\" = \'" + id + "\'";
        String queryStep = "DELETE FROM \"Step\" WHERE \"recipeId\" = \'" + id + "\'";
        String queryUnitOfRecipe = "DELETE FROM \"UnitOfRecipe\" WHERE \"recipeId\" = \'" + id + "\'";
        try {
            dbc.getConnection().createStatement().executeUpdate(queryRecipe);
            dbc.getConnection().createStatement().executeUpdate(queryStep);
            dbc.getConnection().createStatement().executeUpdate(queryUnitOfRecipe);
            connection.commit();
        }
        catch (SQLException e){
            logger.error(e.getMessage());
            connection.rollback(savepoint);
            return false;
        }
        logger.info("Recipe with id = '"+id+"' was deleted!");
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

    @SneakyThrows
    private int getIdOfRecipeByName(String name)  {
        String query = "SELECT \"RID\" FROM \"Recipe\" WHERE \"name\" = \'" + name + "\'";
        ResultSet resultSet =  dbc.getConnection().createStatement().executeQuery(query);
        resultSet.next();
        return resultSet.getInt("RID");
    }

    @SneakyThrows
    private int getIdOfProductByName(String name)  {
        String query = "SELECT \"PID\" FROM \"Product\" WHERE \"name\" = \'" + name + "\'";
        ResultSet resultSet =  dbc.getConnection().createStatement().executeQuery(query);
        resultSet.next();
        return resultSet.getInt("PID");
    }

}
