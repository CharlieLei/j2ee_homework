package com.example.yummy.controller;

import com.example.yummy.service.pay.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping(value = "/isLoginInfoCorrect", method = RequestMethod.GET)
    public boolean isLoginInfoCorrect(@RequestParam(name = "accountId") String accountId,
                                      @RequestParam(name = "password") String password) {
        return payService.isLoginInfoCorrect(accountId, password);
    }

    @RequestMapping(value = "/payOrder", method = RequestMethod.GET)
    public boolean payOrder(@RequestParam(name = "memberAccountId") String memberAccountId,
                            @RequestParam(value = "orderId") int orderId) {
        return payService.payOrder(memberAccountId, orderId);
    }

    @RequestMapping(value = "/withdrawOrder", method = RequestMethod.GET)
    public boolean withdrawOrder(@RequestParam(value = "orderId") int orderId) {
        return payService.withdrawOrder(orderId);
    }
}
