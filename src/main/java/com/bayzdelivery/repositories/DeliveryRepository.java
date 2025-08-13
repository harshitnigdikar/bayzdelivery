package com.bayzdelivery.repositories;

import com.bayzdelivery.dto.DeliveryManCommissionDTO;
import com.bayzdelivery.model.Delivery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.query.Param;

@RestResource(exported = false)
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    @Query("""
           SELECT new com.bayzdelivery.dto.DeliveryManCommissionDTO(
               d.deliveryMan.id,
               d.deliveryMan.name,
               SUM(d.commission)
           )
           FROM Delivery d
           WHERE d.startTime >= :start AND d.endTime <= :end
           GROUP BY d.deliveryMan.id, d.deliveryMan.name
           ORDER BY SUM(d.commission) DESC
           """)
    List<DeliveryManCommissionDTO> findTopDeliveryMenByCommission(
            @Param("start") Instant start,
            @Param("end") Instant end
    );

    List<Delivery> findByEndTimeAfter(Instant time);
}
