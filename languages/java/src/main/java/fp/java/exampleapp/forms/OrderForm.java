package fp.java.exampleapp.forms;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderForm {
    private String email = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    private String date = "";
    private String sku = "";
    private String quantity = "";
    private List<String> validationErrors = new ArrayList<>();
    private static ObjectMapper mapper = new ObjectMapper();
    
    public static OrderForm fromJson(String json) throws IOException {
        return mapper.readValue(json, OrderForm.class);
    }

    public void validate()  {
        validationErrors.clear();
        if(isBlank(email) || isBlank(address) || isBlank(city) ||
                isBlank(state) || isBlank(zip) || isBlank(date) || isBlank(sku) ||
                isBlank(quantity)){
            validationErrors.add("All fields are required");
        }
    }

    public Boolean isValid() {
        validate();
        return validationErrors.isEmpty();
    }

    private Boolean isBlank(String s) {return s == null || s.trim().equals("");}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
