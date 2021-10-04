package com.example.tacos.data;

import com.example.tacos.model.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findByDeliveryZip(String deliveryZip);

    List<PurchaseOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate);

    List<PurchaseOrder> findByDeliveryNameAndDeliveryCity(
            String deliveryTo, String deliveryCity);

    List<PurchaseOrder> findByDeliveryCityOrderByDeliveryName(String city);

}
