package com.tipsontech.creditcard.domain;

import jakarta.persistence.*;

public class CreditCardJPACallback {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard){
        System.out.println("Before update was called...");
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard){
        System.out.println("Post Load was called...");
    }
}
