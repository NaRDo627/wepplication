package com.wepplication.Repository;

import com.wepplication.Domain.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("paymentInfoDAO")
public interface PaymentInfoDAO extends JpaRepository<PaymentInfo, Integer> {

}
