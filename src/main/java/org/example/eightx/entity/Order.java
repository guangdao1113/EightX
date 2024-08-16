package org.example.eightx.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
//    @Id
//    private String id;
    private String orderDate;
//    private double netSales;
//    private double grossSales;
//    private double totalSales;

//    public double getGrossSales() {
//        return grossSales;
//    }
//
//    public void setGrossSales(double grossSales) {
//        this.grossSales = grossSales;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

//    public double getNetSales() {
//        return netSales;
//    }
//
//    public void setNetSales(double netSales) {
//        this.netSales = netSales;
//    }
//
//    public double getTotalSales() {
//        return totalSales;
//    }
//
//    public void setTotalSales(double totalSales) {
//        this.totalSales = totalSales;
//    }
}
