package nl.hu.bep.model;

import lombok.Getter;
import lombok.Setter;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User implements Principal {
    private static List<User> users = new ArrayList<>();
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String secondname;
    private static int numCustomers = 0;
    private String role;

    public User(String username, String password, String firstname, String secondname){
        id = ++numCustomers;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
        role="user";
        users.add(this);
    }

    public static List<User> getAll(){
        return users;
    }

    //createUser lets you create a user with unique username
    public static User createUser(String username, String password, String firstname, String secondname){
        if (users.stream().noneMatch(e->e.getName().equals(username))) {
            return new User(username, password, firstname, secondname);
        }
        return null;
    }

    public static boolean removeCustomer(int id){
        if (id>0) return users.remove(users.indexOf(User.getUserById(id))) != null;
        return false;
   }

    public static User getUserById(int id) {
        return users.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public static User getUserByName(String uname){
        return users.stream()
                .filter(e->e.username.equals(uname))
                .findFirst()
                .orElse(null);
    }

    public void setAdmin(){
        role="admin";
    }

    public static String validateLogin(String username, String password){
        User found = getUserByName(username);
        if(found!=null)
            return password.equals(found.password) ? found.getRole(): null;
        return null;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}