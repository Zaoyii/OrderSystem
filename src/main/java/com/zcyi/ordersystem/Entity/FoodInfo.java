package com.zcyi.ordersystem.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * @author ZaoYi
 */
@Setter
@Data
@Getter
public class FoodInfo {
    String foodName;
    String foodCount;
    String foodPrice;
    String foodTotalPrice;

    public FoodInfo(String foodName, String foodCount, String foodPrice, String foodTotalPrice) {
        this.foodName = foodName;
        this.foodCount = foodCount;
        this.foodPrice = foodPrice;
        this.foodTotalPrice = foodTotalPrice;
    }
}
