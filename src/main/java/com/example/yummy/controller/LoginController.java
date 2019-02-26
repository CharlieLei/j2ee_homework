package com.example.yummy.controller;

import com.example.yummy.service.manager.ManagerAccountService;
import com.example.yummy.service.member.MemberAccountService;
import com.example.yummy.service.restaurant.RestaurantAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MemberAccountService memberAccountService;
    @Autowired
    private RestaurantAccountService restaurantAccountService;
    @Autowired
    private ManagerAccountService managerAccountService ;

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public boolean memberLogin(@RequestParam(value = "id") String memberId,
                               @RequestParam(value = "password") String password) {

        return memberAccountService.login(memberId, password);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public boolean restaurantLogin(@RequestParam(value = "id") String restaurantId,
                                   @RequestParam(value = "password") String password) {

        return restaurantAccountService.login(restaurantId, password);
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public boolean managerLogin(@RequestParam(value = "id") String managerId,
                                @RequestParam(value = "password") String password) {

        return managerAccountService.login(managerId, password);
    }
}
