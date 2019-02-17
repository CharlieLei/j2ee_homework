package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.statistics.MemberStatistics;
import com.example.yummy.service.member.MemberInfoService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public boolean cancelMember(@RequestParam(value = "memberId") String memberId) {
        return false;
    }

    @RequestMapping(value = "/modifyInfo", method = RequestMethod.GET)
    public boolean modifyInfo(@RequestParam(value = "modifyInfo") Member member) {
        return false;
    }

    @RequestMapping(value = "/getMember", method = RequestMethod.GET)
    public Member getMember(@RequestParam(value = "memberId") String memberId) {
        MemberInfoService memberInfoService = ServiceFactory.getMemberInfoService();
        return memberInfoService.getMember(memberId);
    }

    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public MemberStatistics getStatistics(@RequestParam(value = "memberId") String memberId) {
        return null;
    }
}
