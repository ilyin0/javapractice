package by.bsu.ilyin.UI;

import by.bsu.ilyin.entities.IdEntity;
import by.bsu.ilyin.service.MenuUnitService;
import by.bsu.ilyin.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UI {
    PrintStream outStream;
    InputStream inStream;
    Scanner scanner;
    Service menuUnitService;
    Service entityService;
    Logger logger = LogManager.getLogger();

    public UI(){
        menuUnitService = new MenuUnitService();
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
                outStream.print(field.getGenericType());
                field.set(obj,fillCollectionsField((ParameterizedType) field.getGenericType()));
                continue;
            }
            if(!(field.toString().contains("java.lang")||field.toString().contains("java.util")||field.getType().isPrimitive())) {
                field.set(obj,constructEntity(field.getType().getName()));
                continue;
            }
            buf=scanner.nextLine();
            if (field.getType().equals(Integer.class)){
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
        if(c.getSuperclass().equals(IdEntity.class)){
            try {
                Method idSetter = c.getSuperclass().getMethod("setId", Integer.class);
                outStream.print("Id: ");
                idSetter.invoke(obj, Integer.valueOf(scanner.nextLine()));
            }
            catch(Exception e){
                logger.debug(e.getMessage());
            }
        }
        return obj;
    }

    public ArrayList fillCollectionsField(ParameterizedType type) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException {
        outStream.print("\n\n"+type.getActualTypeArguments()[0].toString()+"\n");
        Class c = Class.forName(type.getActualTypeArguments()[0].toString().replace("class ",""));
        Object obj = c.newInstance();
        ArrayList arrayList= new ArrayList(Arrays.asList());
        do{
            arrayList.add(constructEntity(c.getName()));
            outStream.print("Input \"0\" to end adding, \"1\" to continue");
            if(scanner.nextLine().equals("0")) break;
        }
        while(true);
        return arrayList;
    }

}
