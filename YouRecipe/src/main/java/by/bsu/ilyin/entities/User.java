package by.bsu.ilyin.entities;

import lombok.Data;

import java.util.Objects;
import java.util.StringJoiner;

@Data
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
