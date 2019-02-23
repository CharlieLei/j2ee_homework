//package com.example.yummy.service.order;
//
//import com.example.yummy.dao.MemberDao;
//import com.example.yummy.dao.OrderDao;
//import com.example.yummy.dao.ProductDao;
//import com.example.yummy.dao.YummyDao;
//import com.example.yummy.factory.DaoFactory;
//import com.example.yummy.model.member.Member;
//import com.example.yummy.model.order.Order;
//import com.example.yummy.model.order.OrderItem;
//import com.example.yummy.model.order.OrderState;
//import com.example.yummy.model.product.Product;
//import com.example.yummy.model.yummyBill.YummyBill;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//class OrderPaymentDealer {
//
//    private OrderDao orderDao = DaoFactory.getOrderDao();
//    private MemberDao memberDao = DaoFactory.getMemberDao();
//    private YummyDao yummyDao = DaoFactory.getYummyDao();
//    private ProductDao productDao = DaoFactory.getProductDao();
//
//    synchronized boolean payOrder(int orderId) {
//        Order order = orderDao.get(orderId);
//
//        if (isProductsInOrderEnough(order)) {
//            Member member = memberDao.get(order.getMemberId());
//
//            order.setRefund(0);
//            order.setState(OrderState.DELIVERING);
//            orderDao.modify(order);
//
//            double totalAmount = order.getTotalAmount();
//            member.getMemberInfo().setBalance(member.getMemberInfo().getBalance() - totalAmount);
//            memberDao.modify(member);
//
//            YummyBill yummyBill = new YummyBill();
//            yummyBill.setTradingDate(new Timestamp(System.currentTimeMillis()));
//            yummyBill.setOrderId(order.getId());
//            yummyBill.setSettled(false);
//
//            return yummyDao.add(yummyBill);
//        }else {
//            double totalAmount = order.getTotalAmount();
//            order.setRefund(totalAmount);
//            order.setState(OrderState.NOT_ENOUGH_PRODUCT);
//            orderDao.modify(order);
//
//            return false;
//        }
//    }
//
//    synchronized boolean withdrawOrder(int orderId) {
//        Order order = orderDao.get(orderId);
//        Member member = memberDao.get(order.getMemberId());
//
//        double totalAmount = order.getTotalAmount();
//        order.setRefund(totalAmount);
//        order.setState(OrderState.CANCELLED);
//        orderDao.modify(order);
//
//        member.getMemberInfo().setBalance(member.getMemberInfo().getBalance() + totalAmount);
//        return memberDao.modify(member);
//    }
//
//    private boolean isProductsInOrderEnough(Order order) {
//        List<OrderItem> orderItemList = order.getOrderItemList();
//        for (OrderItem item: orderItemList) {
//            Product product = productDao.get(item.getProductId());
//
//            if (product.getQuantity() < item.getItemAmount()) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
