package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.service.member.MemberAccountService;
import com.example.yummy.service.restaurant.RestaurantAccountService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public boolean memberLogin(@RequestParam(value = "id") String memberId,
                               @RequestParam(value = "password") String password) {

        MemberAccountService memberAccountService = ServiceFactory.getMemberAccountService();
        return memberAccountService.login(memberId, password);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public boolean restaurantLogin(@RequestParam(value = "id") long restaurantId,
                                   @RequestParam(value = "password") String password) {

        RestaurantAccountService restaurantAccountService = ServiceFactory.getRestaurantAccountService();
        return restaurantAccountService.login(restaurantId, password);
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public boolean managerLogin(@RequestParam(value = "id") String managerId,
                                @RequestParam(value = "password") String password) {

        return false;
    }
}
