package com.example.yummy.model.member;

import com.example.yummy.model.Address;

import java.io.Serializable;
import java.util.List;

/**
 * 会员
 */
public class Member implements Serializable {
    private String id;
    private String password;

    private String name;
    private String email;
    private String phone;
    private List<Address> deliveryAddrList;

    private MemberLevel level;
    private double balance;

    private String code;//验证码
    private MemberState state;

    public Member(String id, String password, String email, String name, String phone) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;

        this.level = MemberLevel.COPPER;
        this.state = MemberState.UNVERIFIED;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Address> getDeliveryAddrList() {
        return deliveryAddrList;
    }

    public MemberLevel getLevel() {
        return level;
    }

    public double getBalance() {
        return balance;
    }

    public String getCode() {
        return code;
    }

    public MemberState getState() {
        return state;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
