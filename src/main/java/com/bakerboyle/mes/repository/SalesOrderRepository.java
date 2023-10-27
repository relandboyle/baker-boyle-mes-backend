package com.bakerboyle.mes.repository;

import com.bakerboyle.mes.model.Customer;
import com.bakerboyle.mes.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, String> {

    @Query(value = "SELECT sale_id FROM sales_orders", nativeQuery = true)
    List<String> getAllSalesOrdersIdOnly();
}
