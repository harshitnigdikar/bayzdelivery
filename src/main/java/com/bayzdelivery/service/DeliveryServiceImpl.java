package com.bayzdelivery.service;

import java.time.Instant;
import java.util.Optional;

import com.bayzdelivery.dto.DeliveryManCommissionDTO;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

  @Autowired
  DeliveryRepository deliveryRepository;

  public Delivery save(Delivery delivery) {
    return deliveryRepository.save(delivery);
  }

  public Delivery findById(Long deliveryId) {
    Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
    if (optionalDelivery.isPresent()) {
      return optionalDelivery.get();
    }else return null;
  }

  public List<DeliveryManCommissionDTO> getTop3DeliveryMen(Instant start, Instant end) {
    List<DeliveryManCommissionDTO> results = deliveryRepository.findTopDeliveryMenByCommission(start, end);
    return results.stream().limit(3).toList();
  }

  public Long calculateCommission(Delivery delivery){
    // `Commission = OrderPrice * 0.05 + Distance * 0.5`
    return (long) ((0.05 * delivery.getPrice()) + (0.5 * delivery.getDistance()));

  }
}
