package com.zcyi.ordersystem.Controller;

import com.zcyi.ordersystem.Base.ApiResult;
import com.zcyi.ordersystem.Entity.Food;
import com.zcyi.ordersystem.Entity.FoodType;
import com.zcyi.ordersystem.Service.ServiceImpl.FoodServiceImpl;
import com.zcyi.ordersystem.Util.Constant;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ZaoYi
 */

@RestController
@ResponseBody
@RequestMapping("/Food")
public class FoodController {

    private final FoodServiceImpl foodService;

    public FoodController(FoodServiceImpl foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/getFoodByType")
    public ApiResult<ArrayList<Food>> getFoodByType(int type) {
        ArrayList<Food> foods = foodService.selectFoodByType(type);
        if (foods != null && foods.size() > 0) {
            return ApiResult.success(foods);
        } else {
            return ApiResult.failed("该分类暂未上架菜品");
        }
    }

    @GetMapping("/getAllFood")
    public ApiResult<ArrayList<Food>> getAllFood() {
        ArrayList<Food> foods = foodService.selectAllFood();
        if (foods != null && foods.size() > 0) {
            return ApiResult.success(foods);
        } else {
            return ApiResult.failed("没菜吃咯");
        }
    }

    @GetMapping("/getAllFoodType")
    public ApiResult<ArrayList<FoodType>> getAllFoodType() {
        ArrayList<FoodType> foodTypes = foodService.selectAllFoodType();
        if (foodTypes.size() > 0) {
            return ApiResult.success(foodTypes);
        } else {
            return ApiResult.failed("暂未添加分类");
        }
    }

    @PostMapping("/addFood")
    public ApiResult<String> addFood(Food food, MultipartFile foodImg) {
        if (food != null && foodImg != null) {
            if (foodService.selectFoodByName(food.getFoodName()) == null) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                food.setFoodCreateTime(format.format(date));
                System.out.println(food + "------" + foodImg.getOriginalFilename());
                String imgUrl = food.getFoodName() + "/" + foodImg.getOriginalFilename();
                String path = Constant.FOOD_IMG_URL + imgUrl;
                File file = new File(path);
                if (!file.exists()) {
                    file.getParentFile().mkdir();
                    try {
                        //创建文件
                        file.createNewFile();
                        foodImg.transferTo(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return ApiResult.failed("操作失败");
                    }
                }
                food.setFoodImgSrc(imgUrl);
                if (foodService.addFood(food) == 1) {
                    return ApiResult.success("操作成功");
                } else {
                    return ApiResult.failed("操作失败");
                }
            } else {
                return ApiResult.failed("菜品名已存在");
            }
        }
        return ApiResult.failed("参数有误");
    }

    @RequestMapping("/DeleteFoodInfo")
    @ResponseBody
    public ApiResult<String> deleteFoodInfo(int foodId) {
        if (foodService.deleteFoodInfo(foodId) == 1) {
            return ApiResult.success("删除成功");
        } else {
            return ApiResult.success("删除失败");
        }
    }


    @RequestMapping("/UpdateFoodInfo")
    @ResponseBody
    public ApiResult<String> updateFoodInfo(@RequestBody Food food) {
        if (foodService.updateFoodInfo(food) == 1) {
            return ApiResult.success("更新成功");
        } else {
            return ApiResult.success("更新失败");
        }

    }
}
