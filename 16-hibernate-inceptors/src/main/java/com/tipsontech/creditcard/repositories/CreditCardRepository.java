package com.tipsontech.creditcard.repositories;

import com.tipsontech.creditcard.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}