package org.example.eightx.service;

import org.example.eightx.entity.Order;
import org.example.eightx.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(cron = "0 * * * * *")  // Run every min
    public void fetchAndStoreOrders() {
        System.out.println("Fetching and storing orders...");
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName("keep-it-wild-az")
                .build();

        ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);
        List<Order> orders = convertToOrders(scanResponse.items());
        orderRepository.saveAll(orders);
    }

    private List<Order> convertToOrders(List<Map<String, AttributeValue>> items) {
        // Convert DynamoDB items to Order objects
        List<Order> orders = new ArrayList<>();

        for (Map<String, AttributeValue> item : items) {
            Order order = new Order();

            // Convert the 'OrderDate' attribute
            if (item.containsKey("OrderDate")) {
                order.setOrderDate(item.get("OrderDate").s());
            }

//            // Convert the 'NetSales' attribute
//            if (item.containsKey("NetSales")) {
//                order.setNetSales(Double.parseDouble(item.get("NetSales").n()));
//            }
//
//            // Convert the 'TotalSales' attribute
//            if (item.containsKey("TotalSales")) {
//                order.setTotalSales(Double.parseDouble(item.get("TotalSales").n()));
//            }
//
//            // Convert the 'GrossSales' attribute
//            if (item.containsKey("GrossSales")) {
//                order.setGrossSales(Double.parseDouble(item.get("GrossSales").n()));
//            }

            // Add other attributes as needed
            // For example:
            // if (item.containsKey("AnotherField")) {
            //     order.setAnotherField(item.get("AnotherField").s());
            // }

            orders.add(order);
        }

        return orders;
    }
}
