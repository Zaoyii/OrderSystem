package com.zcyi.ordersystem.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * @author ZaoYi
 */
@Data
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Setter
public class Order {

    private long orderId;
    private String orderFoodId;
    private String orderTime;
    private String orderState;
    private String orderEvaluation;
    private long orderStar;
    private double orderTotalPrice;
    private long orderUserId;
    private ArrayList<FoodInfo> foodInfos;


}
