package com.example.yummy.model.order;

/**
 * 订单状态
 */
public enum OrderState {
    PAYING,      //待付款
    DELIVERING,  //配送中

    NOT_ACTIVE,
    COMPLETED,   //已完成
    OVERDUE,     //到时间未支付
    CANCELLED,   //退订的
    NOT_ENOUGH_PRODUCT  //因食物不够而自动退订
}
