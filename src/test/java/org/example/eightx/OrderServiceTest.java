package org.example.eightx;

import org.example.eightx.entity.Order;
import org.example.eightx.repository.OrderRepository;
import org.example.eightx.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {

    @Mock
    private DynamoDbClient dynamoDbClient;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchAndStoreOrders_whenNewOrder_shouldSaveOrder() {
        // Arrange
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("OrderDate", AttributeValue.builder().s("2021-02-28").build());

        List<Map<String, AttributeValue>> items = Collections.singletonList(item);

        ScanResponse scanResponse = ScanResponse.builder().items(items).build();
        when(dynamoDbClient.scan(any(ScanRequest.class))).thenReturn(scanResponse);
        when(orderRepository.findByOrderDate("2021-02-28")).thenReturn(Optional.empty());

        // Act
        orderService.fetchAndStoreOrders();

        // Assert
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void fetchAndStoreOrders_whenExistingOrder_shouldNotSaveOrder() {
        // Arrange
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("OrderDate", AttributeValue.builder().s("2021-02-28").build());

        List<Map<String, AttributeValue>> items = Collections.singletonList(item);

        ScanResponse scanResponse = ScanResponse.builder().items(items).build();
        when(dynamoDbClient.scan(any(ScanRequest.class))).thenReturn(scanResponse);
        when(orderRepository.findByOrderDate("2021-02-28")).thenReturn(Optional.of(new Order()));

        // Act
        orderService.fetchAndStoreOrders();

        // Assert
        verify(orderRepository, times(0)).save(any(Order.class));
    }
}
