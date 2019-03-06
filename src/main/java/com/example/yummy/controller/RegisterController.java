package com.example.yummy.controller;

import com.example.yummy.model.member.MemberInfo;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfo;
import com.example.yummy.service.member.MemberAccountService;
import com.example.yummy.service.restaurant.RestaurantAccountService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private MemberAccountService memberAccountService;
    @Autowired
    private  RestaurantAccountService restaurantAccountService;

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public boolean memberRegister(@RequestParam(value = "memberId") String memberId,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "memberInfo") String memberInfoJson) {

        Gson gson = new GsonBuilder().create();
        MemberInfo memberInfo = gson.fromJson(memberInfoJson, MemberInfo.class);
        return memberAccountService.register(memberId, password, memberInfo);
    }

    @RequestMapping(value = "/activateMember", method = RequestMethod.GET)
    public boolean activateMember(@RequestParam(value = "memberId") String memberId,
                               @RequestParam(value = "code") String code) {

        return memberAccountService.activate(memberId, code);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public boolean restaurantRegister(@RequestParam(value = "restaurant") String restaurantJson) {

        Gson gson = new GsonBuilder().create();
        Restaurant restaurant = gson.fromJson(restaurantJson, Restaurant.class);
        return restaurantAccountService.register(restaurant);
    }
}
