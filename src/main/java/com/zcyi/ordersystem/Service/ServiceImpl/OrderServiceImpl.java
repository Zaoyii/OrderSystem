package com.zcyi.ordersystem.Service.ServiceImpl;

import com.zcyi.ordersystem.Dao.FoodDao;
import com.zcyi.ordersystem.Dao.OrderDao;
import com.zcyi.ordersystem.Entity.Food;
import com.zcyi.ordersystem.Entity.FoodInfo;
import com.zcyi.ordersystem.Entity.Order;
import com.zcyi.ordersystem.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ZaoYi
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    FoodDao foodDao;

    @Override
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public ArrayList<Order> selectOrder() {
        ArrayList<Order> orders = orderDao.selectOrder();
        if (orders.size() == 0) {
            return null;
        }
        orders.forEach(item -> {
            ArrayList<FoodInfo> orderList = getOrderList(item.getOrderFoodId());
            if (orderList.size() > 0) {
                item.setFoodInfos(orderList);
            }
        });
        return orders;
    }

    @Override
    public Order checkUserOrder(Long userid) {
        return orderDao.checkUserOrder(userid);
    }

    @Override
    public int EvaluateOrder(Long userId, Long star, String evaluation) {
        return orderDao.updateStarEvaluation(userId,star, evaluation);
    }

    @Override
    public int deleteOrderInfo(int orderId) {
        return orderDao.deleteOrderInfo(orderId);
    }
    public ArrayList<FoodInfo> getOrderList(String foodIds) {
        ArrayList<FoodInfo> list = new ArrayList<>();
        String[] idAndCounts = foodIds.split(",");
        for (int i = 0; i < idAndCounts.length; i++) {
            String[] split = idAndCounts[i].split("\\*");
            Food food = foodDao.selectFoodById(Integer.parseInt(split[0]));
            list.add(new FoodInfo(food.getFoodName(), split[1], food.getFoodPrice() + "", food.getFoodPrice() * Integer.parseInt(split[1]) + ""));
        }

        return list;
    }
}
