package by.bsu.ilyin.UI;

import by.bsu.ilyin.entities.MenuUnit;
import by.bsu.ilyin.service.MenuUnitService;
import by.bsu.ilyin.service.RecipeService;
import by.bsu.ilyin.service.Service;
import by.bsu.ilyin.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class UI {
    PrintStream outStream;
    InputStream inStream;
    Scanner scanner;
    Service menuUnitService = new MenuUnitService();
    Service entityService;
    Logger logger = LogManager.getLogger();

    public UI(){
        super();
    }

    public Object constructEntity(String entityName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        logger.info("Creating of new instance "+entityName);
        scanner=new Scanner(inStream);
        String buf;
        Class c = Class.forName(entityName);
        Object obj = c.newInstance();
        Field[]fields=c.getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            outStream.print(field.toString()+": ");
            if(field.toString().contains("List")){
                field.set(obj,fillCollectionsField((ParameterizedType) field.getGenericType()));
                continue;
            }
            if(!(field.toString().contains("java.lang")||field.toString().contains("java.util")||field.getType().isPrimitive())) {
                field.set(obj,constructEntity(field.getType().getName()));
                continue;
            }
            buf=scanner.nextLine();
            if (field.getType().equals(Integer.class)||field.getType().equals(int.class)){
                while(true){
                    if(buf.matches("[0-9]{1,4}")){
                        field.set(obj, Integer.valueOf(buf));
                        break;
                    }
                    outStream.print("It's not number, try again: ");
                    buf=scanner.nextLine();
                }

            }
            else field.set(obj,buf);
          }
          return obj;
    }

    public ArrayList fillCollectionsField(ParameterizedType type) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException {
        outStream.print("\n\n"+type.getActualTypeArguments()[0].toString()+"\n");
        Class c = Class.forName(type.getActualTypeArguments()[0].toString().replace("class ",""));
        Object obj = c.newInstance();
        ArrayList arrayList= new ArrayList();
        do{
            arrayList.add(constructEntity(c.getName()));
            outStream.print("Input \"0\" to end adding, anything else to continue");
            if(scanner.nextLine().equals("0")) break;
        }
        while(true);
        return arrayList;
    }

    public Integer start() throws Exception {
        scanner=new Scanner(inStream);
        List<MenuUnit> menuList=menuUnitService.getAllAsList();
        String name;
        MenuUnit unit;
        while(true){
            outStream.print(menuList.get(menuList.size()-1)+"\n\tprevStep\n\texit\n");
            name = scanner.nextLine();
            unit = select(menuList,name);
            if(Objects.isNull(unit)) break;
            if(!(Objects.isNull(unit.getId()))&&unit.getId().equals(-1)) {
                if(menuList.size()==1) break;
                menuList.remove(menuList.size()-1);
                continue;
            }
            if(Objects.isNull(unit.getActions())) {
                menuList.add(new MenuUnit(name));
                action(menuList);
                menuList.remove(menuList.size()-1);
                continue;
            }
            menuList.add(unit);
        }
        outStream.print("\nGood bye!\n");
        return 0;
    }

    public MenuUnit select(List<MenuUnit>menuList,String select) throws Exception {
        if(menuList.isEmpty()) return null;
        MenuUnit buf = menuList.get(menuList.size()-1);
        if(Objects.isNull(buf.getActions())) {
            action(menuList);
            return buf;
        }
        if(select.equals("exit")) return null;
        if(select.equals("prevStep")) return new MenuUnit(-1,null,null);
        for(MenuUnit unit : buf.getActions()){
            if(select.toLowerCase().equals(unit.getName().toLowerCase())) return unit;
        }
        outStream.print("Not correct select, try again\n");
        return new MenuUnit();
    }

    public boolean action(List<MenuUnit>menuList) throws Exception {
        String action = menuList.get(menuList.size()-1).getName();
        outStream.print(action+"\n");
        String entityName = menuList.get(menuList.size()-2).getName();
        outStream.print(entityName+"\n");
        if(entityName.toLowerCase().equals("Recipe".toLowerCase())) entityService=new RecipeService();
        else if(entityName.toLowerCase().equals("User".toLowerCase())) entityService=new UserService();
        else return false;
        entityName="by.bsu.ilyin.entities."+firstUpperCase(entityName);
        switch(action){
            case "create": return entityService.create(constructEntity(entityName));
            case "delete": {
                outStream.print("Id of being deleted entity: ");
                return entityService.delete(Integer.valueOf(scanner.nextLine()));
            }
            case "getById": {
                outStream.print("Id of being getting entity: ");
                Object obj = entityService.getById(Integer.valueOf(scanner.nextLine()));
                if(Objects.isNull(obj)) return false;
                outStream.print(obj+"\n");
                return true;
            }
            case "getByName":{
                outStream.print("Name of being getting entity: ");
                Object obj = entityService.getByName(scanner.nextLine());
                if(Objects.isNull(obj)) return false;
                outStream.print(obj+"\n");
                return true;
            }
            case "getByEmail":{
                outStream.print("Email of being getting entity: ");
                Object obj = entityService.getByEmail(scanner.nextLine());
                if(Objects.isNull(obj)) return false;
                outStream.print(obj+"\n");
                return true;
            }
            case "update":{
                outStream.print("Input new entity: ");
                return entityService.update(constructEntity(entityName));
            }
            default: return false;
        }
    }

    public String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return ""; //или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
