package com.wepplication.RESTful.Repository;

import com.wepplication.RESTful.Domain.PaymentInfo;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("paymentInfoDAO")
public interface PaymentInfoDAO extends JpaRepository<PaymentInfo, Integer> {

}
