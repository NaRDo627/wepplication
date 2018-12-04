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
import java.util.List;

@RestController
@RequestMapping("/payment_info")
public class PaymentInfoController {

    @Resource(name = "paymentInfoService")
    private PaymentInfoService paymentInfoService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<PaymentInfo> paymentInfoGet() {
        System.out.println("select payment_info *");
        try{
            return paymentInfoService.findPaymentInfoAll();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = {"/{pno}"}, method = RequestMethod.GET)
    public PaymentInfo paymentInfoGet(@PathVariable(value="pno") Integer pno) {
        System.out.println("select payment_info where pno=" + pno);
        try{
            return paymentInfoService.findPaymentInfoByPno(pno);
        } catch (Exception e) {
            return null;
        }
    }
}
