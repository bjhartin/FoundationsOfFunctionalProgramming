package fp.java.exampleapp.domain;

import fp.java.exampleapp.forms.OrderForm;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    public OrderSummary processOrders(File orderFile) throws IOException, ParseException {
        List<String> lines = readLines(orderFile);
        List<OrderForm> orderForms = parseOrderForms(lines);
        List<OrderForm> invalidOrders = new ArrayList<>();
        List<Order> savedOrders = new ArrayList<>();

        for(OrderForm form : orderForms) {
            if(form.isValid()){
                Order validOrder = Order.fromForm(form);
                validOrder.save();
                savedOrders.add(validOrder);
            } else {
                invalidOrders.add(form);
            }
        }
        return new OrderSummary(invalidOrders, savedOrders);
    }

    List<String> readLines(File file) throws IOException {
        return Files.readAllLines(file.toPath(), Charset.defaultCharset());
    }

    List<OrderForm> parseOrderForms(List<String> lines) throws IOException {
        List<OrderForm> forms = new ArrayList<>();
        for(String line : lines) {
            forms.add(OrderForm.fromJson(line));
        }
        return forms;
    }
}
