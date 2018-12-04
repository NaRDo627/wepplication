package com.wepplication.RESTful.Controller.Service.Impl;

import com.wepplication.RESTful.Controller.Service.PaymentInfoService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.PaymentInfo;
import com.wepplication.RESTful.Domain.Users;
import com.wepplication.RESTful.Repository.PaymentInfoDAO;
import com.wepplication.RESTful.Repository.UsersDAO;
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
