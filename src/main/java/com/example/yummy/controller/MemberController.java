package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.statistics.MemberStatistics;
import com.example.yummy.service.member.MemberAccountService;
import com.example.yummy.service.member.MemberService;
import com.example.yummy.service.member.MemberStatisticsService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    private MemberAccountService memberAccountService = ServiceFactory.getMemberAccountService();
    private MemberService memberService = ServiceFactory.getMemberService();
    private MemberStatisticsService memberStatisticsService = ServiceFactory.getMemberStatisticsService();

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public boolean cancelMember(@RequestParam(value = "memberId") String memberId) {
        return memberAccountService.cancel(memberId);
    }

    @RequestMapping(value = "/modifyInfo", method = RequestMethod.GET)
    public boolean modifyInfo(@RequestParam(value = "member") Member member) {
        return memberAccountService.modifyInfo(member);
    }

    @RequestMapping(value = "/getMember", method = RequestMethod.GET)
    public Member getMember(@RequestParam(value = "memberId") String memberId) {
        return memberService.get(memberId);
    }

    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public MemberStatistics getStatistics(@RequestParam(value = "memberId") String memberId) {
        return memberStatisticsService.get();
    }
}
