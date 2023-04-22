package com.zcyi.ordersystem.Controller;

import com.zcyi.ordersystem.Base.ApiResult;
import com.zcyi.ordersystem.Entity.Order;
import com.zcyi.ordersystem.Service.ServiceImpl.OrderServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ZaoYi
 */
@RestController
@ResponseBody
@RequestMapping("/Order")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addOrder")
    @ResponseBody
    public ApiResult<String> addOrder(Order order, Long userid) {
        if (order != null) {
            if (orderService.checkUserOrder(userid) != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                order.setOrderTime(format.format(date));
                order.setOrderState("1");
                int i = orderService.addOrder(order);
                if (i == 1) {
                    return ApiResult.success("操作成功");
                } else {
                    return ApiResult.failed("操作失败");
                }
            } else {
                return ApiResult.failed("有订单进行中");
            }
        }
        return ApiResult.failed("参数有误");
    }

    @PostMapping("/getAllOrder")
    @ResponseBody
    public ApiResult<ArrayList<Order>> getAllOrder() {
        ArrayList<Order> orders = orderService.selectOrder();
        if (orders != null) {
            return ApiResult.success(orders);
        } else {
            return ApiResult.failed("暂无订单");
        }
    }

    @PostMapping("/EvaluateOrder")
    @ResponseBody
    public ApiResult<String> EvaluateOrder(Order order) {
        if (order != null && !order.getOrderEvaluation().isEmpty() && order.getOrderStar() >= 0) {
            Order result = orderService.checkUserOrder(order.getOrderId());
            if (result != null) {
                if (orderService.EvaluateOrder(order.getOrderUserId(), order.getOrderStar(), order.getOrderEvaluation()) == 1) {
                    return ApiResult.success("操作成功");
                } else {
                    return ApiResult.failed("操作失败");
                }
            }
        }
        return ApiResult.failed("订单有误");
    }

    @RequestMapping("/DeleteOrderInfo")
    @ResponseBody
    public ApiResult<String> deleteUserInfo(int orderId) {
        if (orderService.deleteOrderInfo(orderId) == 1) {
            return ApiResult.success("删除成功");
        } else {
            return ApiResult.success("删除失败");
        }
    }
}