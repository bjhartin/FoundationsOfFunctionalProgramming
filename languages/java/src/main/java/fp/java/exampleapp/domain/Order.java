package fp.java.exampleapp.domain;

import fp.java.exampleapp.forms.OrderForm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private Customer cust;
    private String item;
    private Integer quantity;
    private Date date;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");


    public Order(Customer cust, String item, Integer quantity, Date date) {
        this.cust = cust;
        this.item = item;
        this.quantity = quantity;
        this.date = date;
    }

    public static Order fromForm(OrderForm form) throws ParseException {
        Address address = new Address(form.getAddress(), form.getCity(), form.getState(), form.getZip());
        Customer customer = new Customer(form.getEmail(), address);
        return new Order(customer, form.getSku(), Integer.valueOf(form.getQuantity()), dateFormat.parse(form.getDate()));
    }

    void save() {
        try {
            System.out.println("saving");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
