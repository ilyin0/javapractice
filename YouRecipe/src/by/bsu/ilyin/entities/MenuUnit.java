package by.bsu.ilyin.entities;

import java.util.Arrays;
import java.util.Objects;

public class MenuUnit extends IdEntity implements Cloneable {
    private String name;
    private MenuUnit[]actions;

    public MenuUnit(){
        super();
    }

    public MenuUnit(int id, String name, MenuUnit[] actions) {
        super(id);
        this.name = name;
        this.actions = actions;
    }

    public MenuUnit(int i) {
        super(i);
    }

    @Override
    public String toString() {
        String result = new String(name);
        for(MenuUnit menuUnit : actions){
            result=result+"\n\t"+menuUnit.getName();
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public MenuUnit setName(String name) {
        this.name = name;
        return this;
    }

    public MenuUnit[] getActions() {
        return actions;
    }

    public MenuUnit setActions(MenuUnit[] actions) {
        this.actions = actions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuUnit)) return false;
        if (!super.equals(o)) return false;
        MenuUnit menuUnit = (MenuUnit) o;
        return getName().equals(menuUnit.getName()) &&
                Arrays.equals(getActions(), menuUnit.getActions());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), getName());
        result = 31 * result + Arrays.hashCode(getActions());
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        MenuUnit menuUnit = new MenuUnit();
        menuUnit.setId(this.getId());
        menuUnit.setName(this.name);
        menuUnit.setActions(this.actions);
        return menuUnit;
    }

    public MenuUnit getActionByName(String name){
        for(MenuUnit menuUnit : actions){
            if(name.equals(menuUnit.name)) return menuUnit;
        }
        try {
            throw new Exception("Action with this name doesn't exists");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
