package com.example.yummy.model.member;

/**
 * 会员当前所处的状态
 */
public enum MemberState {
    UNVERIFIED,  //注册后未完成验证的
    VALID,       //会员账户有效的
    CANCELLED,   //已经注销的
}
