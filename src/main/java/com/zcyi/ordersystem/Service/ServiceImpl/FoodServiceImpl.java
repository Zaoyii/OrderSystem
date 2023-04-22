package com.zcyi.ordersystem.Service.ServiceImpl;

import com.zcyi.ordersystem.Dao.FoodDao;
import com.zcyi.ordersystem.Entity.Food;
import com.zcyi.ordersystem.Entity.FoodType;
import com.zcyi.ordersystem.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ZaoYi
 */

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodDao foodDao;

    @Override
    public ArrayList<Food> selectFoodByType(int type) {
        ArrayList<Food> foods = foodDao.selectFoodByType(type);
        if (foods.size() > 0) {
            return foods;
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<Food> selectAllFood() {
        return foodDao.selectAllFood();
    }

    @Override
    public ArrayList<FoodType> selectAllFoodType() {
        return foodDao.selectAllFoodType();
    }

    @Override
    public int addFood(Food food) {
        return foodDao.addFood(food);
    }

    @Override
    public Food selectFoodByName(String name) {
        return foodDao.selectFoodByName(name);
    }

    @Override
    public int deleteFoodInfo(int foodId) {
        return foodDao.deleteFoodInfo(foodId);
    }

    @Override
    public int updateFoodInfo(Food food) {
        return foodDao.updateFoodInfo(food);
    }
}
