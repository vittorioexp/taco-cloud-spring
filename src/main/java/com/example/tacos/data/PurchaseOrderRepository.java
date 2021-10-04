package com.example.tacos.data;

import com.example.tacos.model.PurchaseOrder;

public interface PurchaseOrderRepository {
    PurchaseOrder save(PurchaseOrder order);
}
