package com.example.yummy.factory;

import com.example.yummy.service.member.*;

public class ServiceFactory {
    public static MemberAccountService getMemberAccountService() {
        return new MemberAccountImpl();
    }

    public static MemberInfoService getMemberInfoService() {
        return new MemberInfoImpl();
    }
}
