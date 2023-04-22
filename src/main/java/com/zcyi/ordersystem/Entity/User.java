package com.zcyi.ordersystem.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ZaoYi
 */
@Data
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {

  private long userId;
  private String userName;
  private String userPassword;
  private String userAvatar;
  private String userEmail;
  private String userPhone;
  private String userCreateTime;
  private String userSlogan;
  private String userRole;
  private String userToken;

}
