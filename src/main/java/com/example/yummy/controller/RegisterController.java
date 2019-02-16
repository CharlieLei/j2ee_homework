package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.service.member.MemberAccountService;
import com.example.yummy.service.restaurant.RestaurantAccountService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public void memberRegister(@RequestParam(value = "memberId") String memberId,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "phone") String phone) {

        Member member = new Member(memberId, password, email, name, phone);

        MemberAccountService memberAccountService = ServiceFactory.getMemberAccountService();
        memberAccountService.register(member);
    }

    @RequestMapping(value = "/activateMember", method = RequestMethod.GET)
    public void activateMember(@RequestParam(value = "memberId") String memberId,
                               @RequestParam(value = "code") String code) {

        MemberAccountService memberAccountService = ServiceFactory.getMemberAccountService();
        memberAccountService.activateMember(memberId, code);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public String restaurantRegister(@RequestParam(value = "password") String password,
                                   @RequestParam(value = "name") String name) {
        Restaurant restaurant = new Restaurant(password, name);

        RestaurantAccountService restaurantAccountService = ServiceFactory.getRestaurantAccountService();
        return restaurantAccountService.register(restaurant);
    }
}
