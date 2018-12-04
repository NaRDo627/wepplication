package com.wepplication.RESTful.Controller.Service;

import com.wepplication.RESTful.Domain.PaymentInfo;
import com.wepplication.RESTful.Domain.Users;

import java.util.List;

public interface PaymentInfoService {
    public List<PaymentInfo> findPaymentInfoAll() throws Exception;
    public PaymentInfo findPaymentInfoByPno(Integer pno) throws Exception;
}
