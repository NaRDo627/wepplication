package com.wepplication.RESTful.Controller;

import com.wepplication.RESTful.Controller.Service.PaymentInfoService;
import com.wepplication.RESTful.Controller.Service.UsersService;
import com.wepplication.RESTful.Domain.PaymentInfo;
import com.wepplication.RESTful.Domain.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment_info")
public class PaymentInfoController {

    @Resource(name = "paymentInfoService")
    private PaymentInfoService paymentInfoService;

    @RequestMapping(value = {"/{pno}"}, method = RequestMethod.GET)
    public PaymentInfo paymentInfoGet(@PathVariable(value="pno") Integer pno) {
        System.out.println(pno);
        try{
            return paymentInfoService.findPaymentInfoByPno(pno);
        } catch (Exception e) {
            return null;
        }
    }
}
