package nl.hu.bep.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private static List<Customer> allCustomers = new ArrayList<>();
    private static int numCustomers = 0;

    private Customer(String nm){
        name = nm;
        id = ++numCustomers;
    }

    public Customer(int id, String nm){
        this.id = id;
        this.name = nm;
    }


    public static List<Customer> getAllCustomers() {
        return Collections.unmodifiableList(allCustomers);
    }

    public static void setAllCustomers(List<Customer> allCustomers) {
        Customer.allCustomers = allCustomers;
    }

    public static Customer getCustomer(int id){
        return allCustomers.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public static Customer createCustomer(String name){
        if (allCustomers.stream().noneMatch(e->e.getName().equals(name))) {
            Customer newCustomer = new Customer(name);
            allCustomers.add(newCustomer);
            return newCustomer;
        }
        else return null;
    }

    public static Customer updateCustomerName(int id, String name){
        Customer found = Customer.getCustomer(id);
        if(found!=null){
            found.setName(name!=null ? name : "");
        }
        return found;
    }

    public static Customer updateCustomer(Customer newCust){
        return allCustomers.set(allCustomers.indexOf(Customer.getCustomer(newCust.getId())), newCust);
    }

    public static boolean removeCustomer(int id){
        if (id>0) return allCustomers.remove(allCustomers.indexOf(Customer.getCustomer(id))) != null;
        return false;
    }
    public int getId(){
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name=name;
    }



    public static Customer getCustomerById(int id) {
        return allCustomers.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
