package com.bakerboyle.mes.service;

import com.bakerboyle.mes.model.SalesOrder;
import com.bakerboyle.mes.repository.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesOrderServiceImpl implements SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;

    @Override
    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderRepository.findAll();
    }

    @Override
    public List<String> getAllSalesOrderIds() {
        return salesOrderRepository.getAllSalesOrdersIdOnly();
    }

    @Override
    public String createSalesOrder(SalesOrder salesOrder) {
        return salesOrderRepository
                .save(salesOrder).getSalesOrderId();
    }

    @Override
    public Optional<SalesOrder> findSalesOrder(String salesOrderId) {
        return salesOrderRepository
                .findById(salesOrderId);
    }

    @Override
    public SalesOrder updateSalesOrder(String salesOrderId, SalesOrder salesOrder) {
        SalesOrder theSalesOrder = salesOrderRepository.findById(salesOrderId)
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException(
                                String.format("The order with id %s does not exist",
                                        salesOrder.getSalesOrderId())));
        System.out.println("TEST: " + salesOrder.getSalesOrderId());
        theSalesOrder.setStatus(salesOrder.getStatus());

        return salesOrderRepository
                .save(theSalesOrder);
    }

    @Override
    public void deleteSalesOrder(String salesOrderId) {
        SalesOrder theSalesOrder = salesOrderRepository.findById(salesOrderId).get();
        if (theSalesOrder == null) {
            throw new RuntimeException(
                    String.format("The order with id %s does not exist", salesOrderId));
        }
        salesOrderRepository.delete(theSalesOrder);
    }
}