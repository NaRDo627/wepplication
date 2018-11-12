package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.PaymentInfoService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.PaymentInfo;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Repository.PaymentInfoDAO;
import com.wepplication.RESTful.Repository.UsersDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("paymentInfoService")
public class PaymentInfoServiceImpl implements PaymentInfoService {
    // DAO
    @Resource(name = "paymentInfoDAO")
    PaymentInfoDAO paymentInfoDAO;

    public PaymentInfo findPaymentInfoByPno(Integer pno) throws Exception {
        return paymentInfoDAO.findOne(pno);
    }
}
