package com.example.tacos.controller;

import com.example.tacos.data.PurchaseOrderRepository;
import com.example.tacos.model.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("purchaseOrder")
public class PurchaseOrderController {

    private PurchaseOrderRepository orderRepo;

    public PurchaseOrderController(PurchaseOrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    /*
     Return a purchaseOrder as a ModelAttribute
     */
    @ModelAttribute("purchaseOrder")
    public PurchaseOrder getPurchaseOrder() {
        return new PurchaseOrder();
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute PurchaseOrder order) {

        log.info("orderForm()");

        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }

        log.info("returning orderForm");

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid PurchaseOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}