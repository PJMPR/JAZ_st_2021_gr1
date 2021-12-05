package com.example.demo.data.projections.Customer;

import java.math.BigDecimal;

public interface CustomerBySpentMoney {
    CustomerInfo getCustomer();
    BigDecimal getSpent();
}
