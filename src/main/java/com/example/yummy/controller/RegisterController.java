package com.example.yummy.controller;

import com.example.yummy.model.member.Member;
import com.example.yummy.service.member.MemberAccountImpl;
import com.example.yummy.service.member.MemberAccountService;
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

        MemberAccountService memberAccountService = new MemberAccountImpl();
        memberAccountService.register(member);
    }

    @RequestMapping(value = "/activateMember", method = RequestMethod.GET)
    public void activateMember(@RequestParam(value = "memberId") String memberId,
                               @RequestParam(value = "code") String code) {

        MemberAccountService memberAccountService = new MemberAccountImpl();
        memberAccountService.activateMember(memberId, code);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public long restaurantRegister(@RequestParam(value = "password") String password) {
        return 0;
    }
}
