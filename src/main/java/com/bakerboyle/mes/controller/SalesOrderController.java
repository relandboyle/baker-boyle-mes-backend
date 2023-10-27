package com.bakerboyle.mes.controller;

import com.bakerboyle.mes.model.Customer;
import com.bakerboyle.mes.model.SalesOrder;
import com.bakerboyle.mes.service.IdentifierService;
import com.bakerboyle.mes.service.SalesOrderService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4209")
@RequestMapping(value = "api/v1/salesOrders")
public class SalesOrderController {

    @Autowired
    IdentifierService identifierService;

    @Autowired
    SalesOrderService salesOrderService;

    @GetMapping(path = "allIds")
    public List<String> getAllSalesOrderIds() throws ServiceException {
        return salesOrderService.getAllSalesOrderIds();
    }

    @PostMapping()
    public ResponseEntity<SalesOrder> createSalesOrder(@RequestBody SalesOrder salesOrder) throws ServiceException {
        System.out.println("SALES ORDER: " + salesOrder);
        ResponseEntity<SalesOrder> response = null;
        List<String> existingIds = getAllSalesOrderIds();

        String newSalesOrderId = identifierService.generateEntityId(existingIds, "SALE-");
        UUID newUUID = identifierService.generateUUIDFromInput(newSalesOrderId);
        System.out.println("ID: " + newSalesOrderId);
        System.out.println("UUID: " + newUUID);
        salesOrder.setSalesOrderId(newSalesOrderId);
        salesOrder.setInternalId(newUUID);

        String confirmation = salesOrderService.createSalesOrder(salesOrder);
        Optional<SalesOrder> newSalesOrder = salesOrderService.findSalesOrder(confirmation);
        if (!confirmation.isEmpty() && newSalesOrder.isPresent()) {
            response = new ResponseEntity<>(newSalesOrder.get(), HttpStatus.OK);
        }
        return response;
    }
}
