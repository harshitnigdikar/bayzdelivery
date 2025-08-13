package com.bayzdelivery.dto;

public class DeliveryManCommissionDTO {
    private Long deliveryManId;
    private String deliveryManName;
    private Long totalCommission;

    public DeliveryManCommissionDTO(Long deliveryManId, String deliveryManName, Long totalCommission) {
        this.deliveryManId = deliveryManId;
        this.deliveryManName = deliveryManName;
        this.totalCommission = totalCommission;
    }

    public Long getDeliveryManId() {
        return deliveryManId;
    }

    public void setDeliveryManId(Long deliveryManId) {
        this.deliveryManId = deliveryManId;
    }

    public String getDeliveryManName() {
        return deliveryManName;
    }

    public void setDeliveryManName(String deliveryManName) {
        this.deliveryManName = deliveryManName;
    }

    public Long getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(Long totalCommission) {
        this.totalCommission = totalCommission;
    }
}
