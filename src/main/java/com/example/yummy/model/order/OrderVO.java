package com.example.yummy.model.order;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.dao.ProductDao;
import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.Address;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.product.Product;
import com.example.yummy.model.product.ProductVO;
import com.example.yummy.model.restaurant.Restaurant;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderVO implements Serializable {
    private int id;
    private String memberId;
    private String memberName;
    private String restaurantId;
    private String restaurantName;

    private Timestamp placingOrderTime;//下订单的时间
    private Timestamp payDeadline;//付款时间
    private Timestamp fulfillingOrderTime;//完成订单的时间

    private Address senderAddr;
    private Address receiverAddr;

    private List<ProductVO> productList;

    private double totalAmount;//总金额

    public OrderVO(Order order) {
        this.id = order.getId();
        this.memberId = order.getMemberId();
        this.restaurantId = order.getRestaurantId();

        this.placingOrderTime = order.getPlacingOrderTime();
        this.payDeadline = order.getPayDeadline();
        this.fulfillingOrderTime = order.getFulfillingOrderTime();

        this.senderAddr = order.getSenderAddr();
        this.receiverAddr = order.getReceiverAddr();

        this.totalAmount = order.getTotalAmount();

        ProductDao productDao = DaoFactory.getProductDao();
        RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();
        MemberDao memberDao = DaoFactory.getMemberDao();

        Member member = memberDao.get(order.getMemberId());
        this.memberName = member.getMemberInfo().getName();

        Restaurant restaurant = restaurantDao.get(order.getRestaurantId());
        this.restaurantName = restaurant.getRestaurantInfo().getName();

        List<ProductVO> productVOList = new ArrayList<>();
        for (OrderItem orderItem: order.getOrderItemList()) {
            Product product = productDao.get(orderItem.getProductId());

            ProductVO productVO = new ProductVO();
            productVO.setName(product.getName());

            productVO.setPrice(orderItem.getItemPrice());
            productVO.setQuantity(orderItem.getItemAmount());

            productVOList.add(productVO);
        }
        this.productList = productVOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Timestamp getPlacingOrderTime() {
        return placingOrderTime;
    }

    public void setPlacingOrderTime(Timestamp placingOrderTime) {
        this.placingOrderTime = placingOrderTime;
    }

    public Timestamp getPayDeadline() {
        return payDeadline;
    }

    public void setPayDeadline(Timestamp payDeadline) {
        this.payDeadline = payDeadline;
    }

    public Timestamp getFulfillingOrderTime() {
        return fulfillingOrderTime;
    }

    public void setFulfillingOrderTime(Timestamp fulfillingOrderTime) {
        this.fulfillingOrderTime = fulfillingOrderTime;
    }

    public Address getSenderAddr() {
        return senderAddr;
    }

    public void setSenderAddr(Address senderAddr) {
        this.senderAddr = senderAddr;
    }

    public Address getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(Address receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public List<ProductVO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductVO> productList) {
        this.productList = productList;
    }


    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
