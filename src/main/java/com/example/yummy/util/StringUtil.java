package com.example.yummy.util;

import java.util.UUID;

public class StringUtil {
    /**
     * 生成激活码
     * @return 激活码
     */
    public static String generateUniqueCode() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateMemberId() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7);
    }
}
