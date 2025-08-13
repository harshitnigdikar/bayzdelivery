package com.bayzdelivery.controller;

import com.bayzdelivery.dto.DeliveryManCommissionDTO;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.service.DeliveryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bayzdelivery.service.DeliveryService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DeliveryController {

  @Autowired
  DeliveryService deliveryService;

  @PostMapping(path ="/delivery")
  public ResponseEntity<Delivery> createNewDelivery(@RequestBody Delivery delivery) {
    delivery.setCommission(deliveryService.calculateCommission(delivery));
    System.out.println(delivery.toString());
    return ResponseEntity.ok(deliveryService.save(delivery));
  }

  @GetMapping(path = "/delivery/{delivery-id}")
  public ResponseEntity<Delivery> getDeliveryById(@PathVariable(name="delivery-id",required=true)Long deliveryId){
    Delivery delivery = deliveryService.findById(deliveryId);
    if (delivery !=null)
      return ResponseEntity.ok(delivery);
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/top-delivery-men")
  public ResponseEntity<List<DeliveryManCommissionDTO>> getTopDeliveryMen(
          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {

    return ResponseEntity.ok(deliveryService.getTop3DeliveryMen(start, end));
  }
}
