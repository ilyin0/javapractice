package by.bsu.ilyin.entities;

import java.util.Objects;
import java.util.StringJoiner;

public class User extends IdEntity implements Cloneable{
    private String name;
    private String email;
    private String password;

    public User(){
        super();
    }

    public User(int id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        User user = new User();
        user.setId(this.getId());
        user.setName(this.name);
        user.setEmail(this.email);
        return user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail())||super.equals(user);
    }
}
