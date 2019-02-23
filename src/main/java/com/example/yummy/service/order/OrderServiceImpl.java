package com.example.yummy.service.order;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.dao.OrderDao;
import com.example.yummy.dao.ProductDao;
import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.Address;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderItem;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.order.OrderVO;
import com.example.yummy.model.product.Product;
import com.example.yummy.model.product.ProductVO;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.util.OrderTimerUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = DaoFactory.getOrderDao();
    private ProductDao productDao = DaoFactory.getProductDao();
    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();
    private MemberDao memberDao = DaoFactory.getMemberDao();

    @Override
    public OrderVO get(int orderId) {
        return this.toOrderVo(orderDao.get(orderId));
    }

    @Override
    public boolean place(String memberId, String restaurantId,
                         Address senderAddr, Address receiverAddr,
                         OrderItem[] orderItemList, double totalAmount) {

        Order order = new Order();
        order.setMemberId(memberId);
        order.setRestaurantId(restaurantId);
        order.setSenderAddr(senderAddr);
        order.setReceiverAddr(receiverAddr);

        List<OrderItem> list = new ArrayList<>(Arrays.asList(orderItemList));
        order.setOrderItemList(list);

        order.setPlacingOrderTime(new Timestamp(System.currentTimeMillis()));

        order.setTotalAmount(totalAmount);
        order.setState(OrderState.PAYING);


        boolean result = orderDao.add(order);
        new Thread(new OrderTimerUtil(order.getId())).start();

        return result;
    }

    @Override
    public boolean cancel(int orderId) {
        Order order = orderDao.get(orderId);
        order.setState(OrderState.CANCELLED);
        return orderDao.modify(order);
    }

    @Override
    public boolean complete(int orderId) {
        Order order = orderDao.get(orderId);
        order.setFulfillingOrderTime(new Timestamp(System.currentTimeMillis()));
        order.setState(OrderState.COMPLETED);
        return orderDao.modify(order);
    }

    @Override
    public List<OrderVO> getAllOrders(String memberId, OrderState orderState) {
        List<Order> orderList = orderDao.getAllOrder(memberId, orderState);
        return toOrderVoList(orderList);
    }

    @Override
    public List<OrderVO> getAllOrdersOfThisRestaurant(String restaurantId, OrderState orderState) {
        List<Order> orderList = orderDao.getAllOrdersOfThisRestaurant(restaurantId, orderState);
        return toOrderVoList(orderList);
    }

    private List<OrderVO> toOrderVoList(List<Order> orderList) {
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order: orderList) {
            OrderVO orderVO = this.toOrderVo(order);
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }

    private OrderVO toOrderVo(Order order) {
        OrderVO vo = new OrderVO();

        vo.setId(order.getId());
        vo.setMemberId(order.getMemberId());
        vo.setRestaurantId(order.getRestaurantId());

        vo.setPlacingOrderTime(order.getPlacingOrderTime());
        vo.setPayDeadline(order.getPayDeadline());
        vo.setFulfillingOrderTime(order.getFulfillingOrderTime());

        vo.setSenderAddr(order.getSenderAddr());
        vo.setReceiverAddr(order.getReceiverAddr());

        vo.setTotalAmount(order.getTotalAmount());

        Member member = memberDao.get(order.getMemberId());
        vo.setMemberName(member.getMemberInfo().getName());

        Restaurant restaurant = restaurantDao.get(order.getRestaurantId());
        vo.setRestaurantName(restaurant.getRestaurantInfo().getName());

        List<ProductVO> productVOList = new ArrayList<>();
        for (OrderItem orderItem: order.getOrderItemList()) {
            Product product = productDao.get(orderItem.getProductId());

            ProductVO productVO = new ProductVO();
            productVO.setName(product.getName());

            productVO.setPrice(orderItem.getItemPrice());
            productVO.setQuantity(orderItem.getItemAmount());

            productVOList.add(productVO);
        }
        vo.setProductList(productVOList);

        return vo;
    }
}
