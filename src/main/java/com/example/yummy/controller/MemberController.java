package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.Address;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberInfo;
import com.example.yummy.model.statistics.MemberStatistics;
import com.example.yummy.service.member.MemberAccountService;
import com.example.yummy.service.member.MemberService;
import com.example.yummy.service.member.MemberStatisticsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    public boolean modifyInfo(@RequestParam(value = "memberId") String memberId,
                              @RequestParam(value = "memberInfo") String memberInfoJson) {

        Gson gson = new GsonBuilder().create();
        MemberInfo memberInfo = gson.fromJson(memberInfoJson, MemberInfo.class);
        return memberAccountService.modifyInfo(memberId, memberInfo);
    }

    @RequestMapping(value = "/addDeliveryAddr", method = RequestMethod.GET)
    public boolean addDeliveryAddr(@RequestParam(value = "memberId") String memberId,
                                   @RequestParam(value = "address") String addressStr) {

        Gson gson = new GsonBuilder().create();
        Address address = gson.fromJson(addressStr, Address.class);
        return memberAccountService.addDeliveryAddr(memberId, address);
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
