package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.PaymentInfo;
import com.wepplication.RESTful.Domain.Users;

public interface PaymentInfoService {
    PaymentInfo findPaymentInfoByPno(Integer pno) throws Exception;
}
