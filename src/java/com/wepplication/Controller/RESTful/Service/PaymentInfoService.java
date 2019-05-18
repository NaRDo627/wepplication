package com.wepplication.Controller.RESTful.Service;

import com.wepplication.Domain.PaymentInfo;

import java.util.List;

public interface PaymentInfoService {
    public List<PaymentInfo> findPaymentInfoAll() throws Exception;
    public PaymentInfo findPaymentInfoByPno(Integer pno) throws Exception;
}
