package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.service.member.MemberAccountImpl;
import com.example.yummy.service.member.MemberAccountService;
import com.example.yummy.service.member.MemberInfoService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    @RequestMapping(value = "/getMember", method = RequestMethod.GET)
    public Member getMember(@RequestParam(value = "memberId") String memberId) {
        MemberInfoService memberInfoService = ServiceFactory.getMemberInfoService();
        return memberInfoService.getMember(memberId);
    }
}
