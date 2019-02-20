package com.example.yummy.model.member;

import com.example.yummy.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
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

    @Embedded
    private MemberInfo memberInfo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private List<MemberDeliveryAddress> deliveryAddrList;

    @Column(name = "state")
    private MemberState state;
    @Column(name = "code")
    private String code;//验证码

    public Member() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }

    public List<MemberDeliveryAddress> getDeliveryAddrList() {
        return deliveryAddrList;
    }

    public void setDeliveryAddrList(List<MemberDeliveryAddress> deliveryAddrList) {
        this.deliveryAddrList = deliveryAddrList;
    }

    public MemberState getState() {
        return state;
    }

    public void setState(MemberState state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
