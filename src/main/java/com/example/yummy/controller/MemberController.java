package com.example.yummy.controller;

import com.example.yummy.model.Address;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberInfo;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.MemberStatistics;
import com.example.yummy.service.member.MemberAccountService;
import com.example.yummy.service.member.MemberService;
import com.example.yummy.service.member.MemberStatisticsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberAccountService memberAccountService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberStatisticsService memberStatisticsService;

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
    public MemberStatistics getStatistics(@RequestParam(value = "memberId") String memberId,
                                          @RequestParam(value = "startTime") Timestamp startTime,
                                          @RequestParam(value = "endTime") Timestamp endTime,
                                          @RequestParam(value = "lowerAmount") double lowerAmount,
                                          @RequestParam(value = "upperAmount") double upperAmount,
                                          @RequestParam(value = "restaurantType") String restaurantTypeStr) {
        RestaurantType restaurantType = RestaurantType.getEnum(restaurantTypeStr);
        return memberStatisticsService.get(memberId, startTime, endTime, lowerAmount, upperAmount, restaurantType);
    }
}
