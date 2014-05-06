package fp.java.exampleapp.domain;

import fp.java.exampleapp.forms.OrderForm;

import java.util.List;

public class OrderSummary {
    private List<OrderForm> invalidOrders;
    private List<Order> savedOrders;

    public OrderSummary(List<OrderForm> invalidOrders, List<Order>  validOrders) {
        this.setInvalidOrders(invalidOrders);
        this.setSavedOrders(validOrders);
    }

    public List<OrderForm> getInvalidOrders() {
        return invalidOrders;
    }

    public void setInvalidOrders(List<OrderForm> invalidOrders) {
        this.invalidOrders = invalidOrders;
    }

    public List<Order> getSavedOrders() {
        return savedOrders;
    }

    public void setSavedOrders(List<Order> savedOrders) {
        this.savedOrders = savedOrders;
    }
}
