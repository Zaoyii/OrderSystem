package com.zcyi.ordersystem.Service;

import com.zcyi.ordersystem.Entity.Food;
import com.zcyi.ordersystem.Entity.FoodType;

import java.util.ArrayList;

/**
 * @author ZaoYi
 */
public interface FoodService {

    /**
     * 根据type查询菜品
     *
     * @param type 菜品类型
     * @return 列表
     */
    ArrayList<Food> selectFoodByType(int type);

    /**
     * 查询ALl菜品
     *
     * @return 列表
     */
    ArrayList<Food> selectAllFood();

    /**
     * 查询所有菜品类型
     *
     * @return 菜品类型列表
     */
    ArrayList<FoodType> selectAllFoodType();

    /**
     * 添加菜品
     *
     * @param food 参数
     * @return 1/0
     */
    int addFood(Food food);

    /**
     * 查询菜品
     *
     * @param name cs
     * @return f
     */
    Food selectFoodByName(String name);

    /**
     * 删除菜品
     * @param foodId
     * @return
     */
    int deleteFoodInfo(int foodId);


    /**
     * 修改菜品信息
     * @param food
     * @return
     */
    int updateFoodInfo(Food food);
}
