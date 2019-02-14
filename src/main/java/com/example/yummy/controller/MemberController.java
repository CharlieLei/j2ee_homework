package com.example.yummy.controller;

import com.example.yummy.service.member.MemberAccountImpl;
import com.example.yummy.service.member.MemberAccountService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class MemberController {

    @RequestMapping(value = "/registerMember", method = RequestMethod.GET)
    public void registerMember(@RequestParam(value = "memberId") String memberId,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "email") String email) {
        MemberAccountService memberAccountService = new MemberAccountImpl();
        memberAccountService.register(memberId, password, email);
    }

    @RequestMapping(value = "/activateMember", method = RequestMethod.GET)
    public void activateMember(@RequestParam(value = "code") String code) {
        MemberAccountService memberAccountService = new MemberAccountImpl();
        memberAccountService.activateMember(code);
    }


    public void cancelMember(String memberId) {

    }
}
