package com.bayzdelivery.jobs;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.repositories.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class DelayedDeliveryNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedDeliveryNotifier.class);

    private final DeliveryRepository deliveryRepository;

    public DelayedDeliveryNotifier(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    /**
     * Runs every 30 seconds to check for deliveries that took more than 45 mins.
     */
    @Scheduled(fixedDelay = 30000)
    public void checkDelayedDeliveries() {
        // Only check deliveries completed in the last 24 hours
        Instant since = Instant.now().minus(Duration.ofHours(24));
        List<Delivery> recentDeliveries = deliveryRepository.findByEndTimeAfter(since);

        for (Delivery delivery : recentDeliveries) {
            long minutesTaken = Duration.between(delivery.getStartTime(), delivery.getEndTime()).toMinutes();
            if (minutesTaken > 45) {
                LOG.warn("Delivery ID {} took {} minutes! (Delayed)", delivery.getId(), minutesTaken);
                notifyCustomerSupport(delivery);
            }
        }
    }

    /**
     * Notify customer support asynchronously
     */
    @Async
    public void notifyCustomerSupport(Delivery delivery) {
        LOG.info("Customer support notified: Delivery ID {} delayed!", delivery.getId());

    }
}