package com.wepplication.Controller.RESTful;

import com.wepplication.Controller.RESTful.Service.PaymentInfoService;
import com.wepplication.Domain.PaymentInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PaymentInfo>> paymentInfoGet() {
        System.out.println("select payment_info *");
        try{
            List<PaymentInfo> paymentInfoList = paymentInfoService.findPaymentInfoAll();
            if(paymentInfoList == null || paymentInfoList.size() == 0)
                return new ResponseEntity<>(paymentInfoList, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(paymentInfoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/{pno}"}, method = RequestMethod.GET)
    public ResponseEntity<PaymentInfo> paymentInfoGet(@PathVariable(value="pno") Integer pno) {
        System.out.println("select payment_info where pno=" + pno);
        try{
            PaymentInfo paymentInfo = paymentInfoService.findPaymentInfoByPno(pno);
            if(paymentInfo == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(paymentInfo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
