package nl.hu.bep.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyUser implements Principal {
    public static List<MyUser> allMyUsers = new ArrayList<>();
    private String username, plainpassword, role, firstname, secondname;
    private int id;
    private static int numCustomers = 0;

    public MyUser(String uname, String pwd, String first, String second){
        id = ++numCustomers;
        username = uname;
        plainpassword = pwd;
        role="user";
        firstname = first;
        secondname = second;
        allMyUsers.add(this);
    }

    public static List<MyUser> getAllUsers() {
        return allMyUsers;
    }


    public static MyUser createUser(String uname, String pwd, String first, String second){
        if (allMyUsers.stream().noneMatch(e->e.getName().equals(uname))) {
            MyUser newCustomer = new MyUser(uname, pwd, first, second);
            return newCustomer;
        }
        else return null;
    }

    public static boolean removeCustomer(int id){
        if (id>0) return allMyUsers.remove(allMyUsers.indexOf(MyUser.getUserById(id))) != null;
        return false;
   }

    public static MyUser getUserById(int id) {
        return allMyUsers.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

  //  public static int getCurrentId(){
 //       System.out.println("System is workin'");
 //       int number = id;
 //       return number;
 //   }

    public static String Userdetails(String name) {
        System.out.println("getter is working");
        //this.username = user.getName();
return null;
    }



    public void setAdmin(){
        role="admin";
    }

//    public String toString() {
   //     return "Student: " + username + ", " + plainpassword;
   // }

    @Override

    public String toString() {
        return ""+ id;
    }

    public String getName() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public int getId(){
        return id;
    }

    public String getPassword() {
        return plainpassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }



    public static MyUser getUserByName(String uname){
        return allMyUsers.stream()
                .filter(e->e.username.equals(uname))
                .findFirst()
                .orElse(null);
    }

    public static String getUserByNameIDRETURN(String uname){
        return "" + allMyUsers.stream().filter(e->e.username.equals(uname)).findFirst().orElse(null);
    }

    public static String validateLogin(String uname, String pwd){
        MyUser found = getUserByName(uname);
        if(found!=null) return pwd.equals(found.plainpassword) ? found.getRole(): null;
        return null;
    }
}