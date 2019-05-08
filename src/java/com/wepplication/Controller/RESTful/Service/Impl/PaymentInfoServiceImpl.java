package com.wepplication.Controller.RESTful.Service.Impl;

import com.wepplication.Controller.RESTful.Service.PaymentInfoService;
import com.wepplication.Domain.PaymentInfo;
import com.wepplication.Repository.PaymentInfoDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("paymentInfoService")
public class PaymentInfoServiceImpl implements PaymentInfoService {
    // DAO
    @Resource(name = "paymentInfoDAO")
    PaymentInfoDAO paymentInfoDAO;

    public List<PaymentInfo> findPaymentInfoAll() throws Exception {
        return paymentInfoDAO.findAll();
    }

    public PaymentInfo findPaymentInfoByPno(Integer pno) throws Exception {
        return paymentInfoDAO.findOne(pno);
    }
}
