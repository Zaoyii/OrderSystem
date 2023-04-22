package com.zcyi.ordersystem.Dao;

import com.zcyi.ordersystem.Entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ZaoYi
 */
@Mapper
@Repository
public interface OrderDao {

    /**
     * add
     *
     * @param order add
     * @return add
     */
    @Insert("insert into t_order (order_food_id,order_time,order_state,order_user_id,order_total_price)value " +
            "(#{orderFoodId},#{orderTime},#{orderState},#{orderUserId},#{orderTotalPrice})")
    int addOrder(Order order);


    /**
     * 查询所有订单
     *
     * @return 订单列表
     */
    @Select("select * from t_order")
    @Results({
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderUserId", column = "order_user_id"),
            @Result(property = "orderFoodId", column = "order_food_id"),
            @Result(property = "orderTime", column = "order_time"),
            @Result(property = "orderState", column = "order_state"),
            @Result(property = "orderEvaluation", column = "order_evaluation"),
            @Result(property = "orderStar", column = "order_star"),
            @Result(property = "orderTotalPrice", column = "order_total_price"),
    })
    ArrayList<Order> selectOrder();

    /**
     * check
     *
     * @param userid id
     * @return order
     */
    @Select("select * from t_order where order_state = 1 and order_user_id = #{userid}")
    @Results({
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderUserId", column = "order_user_id"),
    })
    Order checkUserOrder(Long userid);

    /**
     * 更新评星和评论
     *
     * @param star       星 1-10
     * @param evaluation 评论
     * @param userId     userid
     * @return 1/0
     */
    @Update("update t_order set order_evaluation=#{evaluation},order_star=#{star} where order_user_id= #{userId} and order_state = 1")
    int updateStarEvaluation(Long userId, Long star, String evaluation);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @Delete("delete from t_order where order_id = #{orderId}")
    int deleteOrderInfo(int orderId);
}
