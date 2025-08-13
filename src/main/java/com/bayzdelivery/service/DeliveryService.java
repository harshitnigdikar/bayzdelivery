package com.bayzdelivery.service;

import com.bayzdelivery.dto.DeliveryManCommissionDTO;
import com.bayzdelivery.model.Delivery;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryService {

  public Delivery save(Delivery delivery);

  public Delivery findById(Long deliveryId);

  public List<DeliveryManCommissionDTO> getTop3DeliveryMen(Instant start, Instant end);

  public Long calculateCommission(Delivery delivery);
}
