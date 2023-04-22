package com.zcyi.ordersystem.Dao;

import com.zcyi.ordersystem.Entity.Food;
import com.zcyi.ordersystem.Entity.FoodType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ZaoYi
 */
@Mapper
@Repository
public interface FoodDao {

    /**
     * 根据type查询菜品
     *
     * @param type 菜品类型
     * @return 用户列表
     */
    @Select("select * from t_food where food_state = 1 and food_type_id = #{type}")
    @Results({
            @Result(property = "foodId", column = "food_id"),
            @Result(property = "foodName", column = "food_name"),
            @Result(property = "foodContent", column = "food_content"),
            @Result(property = "foodPrice", column = "food_price"),
            @Result(property = "foodSales", column = "food_sales"),
            @Result(property = "foodWeight", column = "food_weight"),
            @Result(property = "foodDiscount", column = "food_discount"),
            @Result(property = "foodImgSrc", column = "food_img_src"),

    })
    ArrayList<Food> selectFoodByType(int type);


    /**
     * @param id
     * @return
     */
    @Select("select * from t_food where food_state = 1 and food_id = #{id}")
    @Results({
            @Result(property = "foodId", column = "food_id"),
            @Result(property = "foodName", column = "food_name"),
            @Result(property = "foodContent", column = "food_content"),
            @Result(property = "foodPrice", column = "food_price"),
            @Result(property = "foodSales", column = "food_sales"),
            @Result(property = "foodWeight", column = "food_weight"),
            @Result(property = "foodDiscount", column = "food_discount"),
            @Result(property = "foodImgSrc", column = "food_img_src"),

    })
    Food selectFoodById(int id);

    /**
     * all Food
     *
     * @return all Food
     */
    @Select("select * from t_food")
    @Results({
            @Result(property = "foodId", column = "food_id"),
            @Result(property = "foodName", column = "food_name"),
            @Result(property = "foodContent", column = "food_content"),
            @Result(property = "foodPrice", column = "food_price"),
            @Result(property = "foodSales", column = "food_sales"),
            @Result(property = "foodWeight", column = "food_weight"),
            @Result(property = "foodState", column = "food_state"),
            @Result(property = "foodDiscount", column = "food_discount"),
            @Result(property = "foodImgSrc", column = "food_img_src"),

    })
    ArrayList<Food> selectAllFood();

    /**
     * 查询所有菜品类型
     *
     * @return 用户列表
     */
    @Select("select * from t_food_type")
    @Results({
            @Result(property = "foodTypeId", column = "food_type_id"),
            @Result(property = "foodTypeName", column = "food_type_name"),

    })
    ArrayList<FoodType> selectAllFoodType();

    /**
     * 添加菜品
     *
     * @param food 参数
     * @return 1/0
     */
    @Insert("insert into t_food (food_name," +
            "food_content,food_type_id,food_price," +
            "food_sales,food_create_time," +
            "food_weight," +
            "food_state,food_discount,food_img_src)" +
            " value " +
            "(#{foodName},#{foodContent},#{foodTypeId}," +
            "#{foodPrice},#{foodSales}," +
            "#{foodCreateTime}," +
            "#{foodWeight},#{foodState}," +
            "#{foodDiscount},#{foodImgSrc})")
    int addFood(Food food);

    /**
     * 查询菜品
     *
     * @param name 参数
     * @return　菜品
     */
    @Select("select * from t_food where food_name=#{name}")
    @Results({
            @Result(property = "foodId", column = "food_id"),

    })
    Food selectFoodByName(String name);

    /**
     * 删除菜品
     *
     * @param foodId
     * @return
     */
    @Delete("delete from t_food where food_id = #{foodId}")
    int deleteFoodInfo(int foodId);

    /**
     * 修改菜品信息
     * @param food
     * @return
     */
    @Update("update t_food set food_name=#{foodName},food_price=#{foodPrice},food_weight=#{foodWeight},food_state=#{foodState},food_discount=#{foodDiscount},food_img_src=#{foodImgSrc} where food_id = #{foodId}")
    int updateFoodInfo(Food food);
}
