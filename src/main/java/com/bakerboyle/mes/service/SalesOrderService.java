package com.bakerboyle.mes.service;

import com.bakerboyle.mes.model.SalesOrder;

import java.util.List;
import java.util.Optional;


public interface SalesOrderService {
    List<SalesOrder> getAllSalesOrders();
    List<String> getAllSalesOrderIds();
    String createSalesOrder(SalesOrder salesOrder);
    Optional<SalesOrder> findSalesOrder(String saleId);
    SalesOrder updateSalesOrder(String saleId, SalesOrder salesOrder);
    void deleteSalesOrder(String saleId);
}
