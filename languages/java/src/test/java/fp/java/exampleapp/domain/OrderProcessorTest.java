package fp.java.exampleapp.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.text.ParseException;

public class OrderProcessorTest {
    File file;
    OrderProcessor orderProcessor;

    @Before
    public void setup() {
        file = new File(System.getProperty("java.io.tmpdir") +
                    File.separator + "orders.json");
        orderProcessor = new OrderProcessor();
    }

    @After
    public void teardown() {
        if(file.exists()) {
            assertTrue("Could not delete file!", file.delete());
        }
    }

    @Test
    public void processValidOrders() throws IOException, ParseException {
        createTestOrdersFile(file, 5, 0);
        OrderSummary orderSummary = orderProcessor.processOrders(file);
        assertEquals(5, orderSummary.getSavedOrders().size());
        assertEquals(0, orderSummary.getInvalidOrders().size());
    }

    @Test
    public void processInvalidOrders() throws IOException, ParseException {
        createTestOrdersFile(file, 5, 3);
        OrderSummary orderSummary = orderProcessor.processOrders(file);
        assertEquals(5, orderSummary.getSavedOrders().size());
        assertEquals(3, orderSummary.getInvalidOrders().size());
    }

    String orderJson(int i) {
        return "{\"email\":         \"customer$i@domain.com\"," +
               "\"address\":       \"1 Main St\"," +
                "\"city\":          \"Des Moines\"," +
                "\"state\":         \"IA\"," +
                "\"zip\":           \"50131\"," +
                "\"date\":          \"2012-04-23T18:25Z\"," +
                "\"sku\":           \"6897433574\"," +
                "\"quantity\":      \"23\"}".replaceAll("[\n\r]", "").replaceAll("([\\{,:]) *", "$1 ");
    }

    String invalidOrderJson(int i) {
        return orderJson(i).replace("IA","");
    }

    void createTestOrdersFile(File file, int validOrders, int invalidOrders) throws IOException {
        // Uses Java 7 automatic resource management
        try (Writer writer = new PrintWriter(file)) {
            for (int i = 0; i < validOrders; i++) {
                writer.write(orderJson(i) + "\n");
            }

            for (int i = 0; i < invalidOrders; i++) {
                writer.write(invalidOrderJson(i) + "\n");
            }
        }
    }
}
