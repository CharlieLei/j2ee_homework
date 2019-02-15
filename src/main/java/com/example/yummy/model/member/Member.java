package com.example.yummy.model.member;

import com.example.yummy.model.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * 会员
 */

@Entity
@Table(name = "members")
public class Member implements Serializable {
    @Id
    @Column(name = "memberId")
    private String id;
    @Column(name = "password")
    private String password;

    @Column(name = "memberName")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

//    private List<Address> deliveryAddrList;

    @Column(name = "memberLevel")
    private MemberLevel level;
    @Column(name = "balance")
    private double balance;

    @Column(name = "code")
    private String code;//验证码
    @Column(name = "state")
    private MemberState state;

    public Member() {

    }

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

//    public List<Address> getDeliveryAddrList() {
//        return deliveryAddrList;
//    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public void setDeliveryAddrList(List<Address> deliveryAddrList) {
//        this.deliveryAddrList = deliveryAddrList;
//    }

    public void setLevel(MemberLevel level) {
        this.level = level;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setState(MemberState state) {
        this.state = state;
    }
}
