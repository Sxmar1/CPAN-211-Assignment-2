package com.example;

public class BakeryOrder {
    private final String customerName;
    private final String phoneNumber;
    private final String cakeType;
    private final String cakeSize;
    private final boolean freeDelivery;
    private final int price;

    public BakeryOrder(String customerName, String phoneNumber, String cakeType, String cakeSize, boolean freeDelivery) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.cakeType = cakeType;
        this.cakeSize = cakeSize;
        this.freeDelivery = freeDelivery;
        this.price = priceCalc(cakeType,cakeSize);
    }

    public static int priceCalc(String cakeType, String cakeSize) {
        boolean isHigherTier = "Coffee".equals(cakeType) 
        || "Opera".equals(cakeType)
        || "Tiramisu".equals(cakeType);

        if ("Small".equals(cakeSize)) {
            return isHigherTier ? 25 : 20;
        }
        if ("Medium".equals(cakeSize)) {
            return isHigherTier ? 35 : 30;
        }
        if ("Large".equals(cakeSize)) {
            return isHigherTier ? 45 : 40;
        }
        return 0;
    }
    
    public String formatOrder() {
        return "Customer Name: " + customerName + " Phone Number: " + phoneNumber + " Cake Type: " + cakeType + " Cake Size: " + cakeSize 
        + " Free Delivery: " + freeDelivery + " Price: $" + price;
    }
}