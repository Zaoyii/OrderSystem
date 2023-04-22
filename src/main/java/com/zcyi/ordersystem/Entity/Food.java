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
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Food {

    private long foodId;
    private String foodName;
    private String foodContent;
    private String foodTypeId;
    private double foodPrice;
    private String foodSales;
    private String foodCreateTime;
    private long foodWeight;
    private long foodState;
    private long foodDiscount;
    private String foodImgSrc;

}
