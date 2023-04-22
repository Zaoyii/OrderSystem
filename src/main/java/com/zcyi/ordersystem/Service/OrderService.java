package com.zcyi.ordersystem.Service;

import com.zcyi.ordersystem.Entity.Order;

import java.util.ArrayList;

/**
 * @author ZaoYi
 */
public interface OrderService {

    /**
     * add
     *
     * @param order add
     * @return add
     */
    int addOrder(Order order);

    /**
     * allOrder
     *
     * @return list
     */
    ArrayList<Order> selectOrder();

    /**
     * check
     *
     * @param userid id
     * @return count 1/0
     */
    Order checkUserOrder(Long userid);

    /**
     * 更新评星和评论
     *
     * @param star       星 1-10
     * @param evaluation 评论
     * @return 1/0
     */
    public int EvaluateOrder(Long userId, Long star, String evaluation);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    int deleteOrderInfo(int orderId);
}
